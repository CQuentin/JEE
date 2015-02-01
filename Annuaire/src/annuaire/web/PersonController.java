package annuaire.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import annuaire.model.Group;
import annuaire.model.Person;
import annuaire.services.IDAOGroup;
import annuaire.services.IDAOPerson;

/**
* PersonController le controller de Person
*
* @author Quentin Cheynet
* @author Yoann Moisset
*/

@Controller()
@RequestMapping("/person")
public class PersonController {

	/**
	 * L'utilisateur en session.
	 * 
	 * @see User
	 */
	@Autowired()
	User user;

	/**
	 * Service d'accès aux données des Personnes de l'annuaire.
	 * 
	 * @see IDAOPerson
	 */
	@Autowired
	IDAOPerson daoPerson;

	/**
	 * Service d'accès aux données des Groupes.
	 * 
	 * @see IDAOGroup
	 */
	@Autowired
	IDAOGroup daoGroup;

	/**
	 * 
	 * @return le nom du fichier listPerson.jspx qui affiche toutes les personnes de l'annuaire
	 */
	@RequestMapping(value = "/annuaire.htm")
	public String list() {
		return "listPerson";
	}

	/**
	 * 
	 * @return une collection de Person via le DAO à partir la table PERSON de la base de donnée
	 */
	@ModelAttribute("persons")
	public Collection<Person> persons() {
		return daoPerson.findAllPersons();
	}

	/**
	 * 
	 * @param p La personne p dont on veut voir les détails
	 * @return le nom du fichier person.jspx qui affiche les détails de la personne p
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detailPerson(@ModelAttribute Person p) {

		return "person";
	}

	/**
	 *  créé et retourne un User
	 * @return Le User
	 */
	@ModelAttribute("user")
	public User newUser() {
		return user;
	}

	/**
	 * 
	 * @param personLogin Le login de la Person de la base de donnée que l'on veut renvoyer
	 * @return Person, avec les attributs correspondant à la personne de la base de donnée 
	 */
	@ModelAttribute("person")
	public Person newPerson(
			@RequestParam(value = "id", required = false) String personLogin) {

		if (personLogin != null) {
			return daoPerson.findPerson(personLogin);
		}

		Person p = new Person();
		p.setLogin("");
		p.setLastName("");
		p.setFirstName("");
		p.setMail("");
		p.setWebsite("");
		p.setDateOfBirth(null);
		p.setPassword("");
		p.setGroups(new HashSet<Group>());
		return p;
	}

	/**
	 * Donne l'accès à la modification des détails d'une personne si on en a les droits
	 * @param p La personne p dont on veut éditer les détails
	 * @return 	- le nom du fichier personForm.jspx si on est un administrateur ou la personne à modifier
	 *			- une redirection vers la liste des personnes sinon
	 */
	@RequestMapping(value = "/edition.htm", method = RequestMethod.GET)
	public String editPerson(@ModelAttribute Person p) {

		if(p == null || p.getLogin().isEmpty()) {
			return "redirect:annuaire.htm";
		}

		if(p.getLogin().equals(user.getLogin()) || user.isAdmin()) {
			return "personForm";
		}
		return "redirect:annuaire.htm";		
	}

	/**
	 * Modifie des détails d'une personne via le DAO si on en a les droits
	 * @param p La personne p dont on veut éditer les détails
	 * @param result Le résultat de la requête, pour savoir si elle contient des erreur
	 * @return 	- une redirection vers les détails de la personne modifié si on est un administrateur ou la personne modifiée
	 *			- une redirection vers la liste des personnes sinon
	 *			- le nom du fichier personForm.jspx si result indique qu'il y a des erreurs
	 */
	@RequestMapping(value = "/edition.htm", method = RequestMethod.POST)
	public String savePerson(@ModelAttribute @Valid Person p, BindingResult result) {
		
		if(result.hasErrors() && (result.getGlobalErrorCount() > 1 && p.getDateOfBirth() == null)) {
			return "personForm";
		}

		if(p == null || p.getLogin().isEmpty()) {
			return "redirect:annuaire.htm";
		}

		if(p.getLogin().equals(user.getLogin()) || user.isAdmin()) {
			if(p.getGroups() == null 
					|| p.getGroups().isEmpty() 
					|| p.getGroups().iterator().next() == null
					|| p.getGroups().iterator().next().getGroupname() == null 
					|| p.getGroups().iterator().next().getGroupname().isEmpty()) {
				p.setGroups(null);
			}

			daoPerson.savePerson(p);
			return "redirect:detail.htm?id=" + p.getLogin();
		}
		return "redirect:annuaire.htm";
	}

	/**
	 * Donne l'accès à l'ajout d'une personne si on en a les droits
	 * @return 	- le nom du fichier personAddition.jspx si on est un administrateur
	 *			- une redirection vers la liste des personnes sinon
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addPerson() {
		if (user.isAdmin()){
			return "personAddition";
		}
		return "redirect:annuaire.htm";
	}

	/**
	 * Ajoute une personne via le DAO si on en a les droits 
	 * @param p La personne p que l'on veut ajouter
	 * @param result Le résultat de la requête, pour savoir si elle contient des erreur
	 * @return 	- une redirection vers les détails de la personne ajouté si on est un administrateur
	 * 			- une redirection vers la liste des personnes sinon
	 * 			- le nom du fichier personForm.jspx si result indique qu'il y a des erreurs ou si la personne existe déjà
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)
	public ModelAndView saveNewPerson(@ModelAttribute @Valid Person p, BindingResult result) {
		
		if(result.hasErrors() && (result.getGlobalErrorCount() > 1 && p.getDateOfBirth() == null)) {
			return new ModelAndView("personAddition","error","");
		}

		if (user.isAdmin()){
			if(p.getGroups() == null 
					|| p.getGroups().isEmpty() 
					|| p.getGroups().iterator().next() == null
					|| p.getGroups().iterator().next().getGroupname() == null 
					|| p.getGroups().iterator().next().getGroupname().isEmpty()) {
				p.setGroups(null);
			}
			if(daoPerson.findPerson(p.getLogin()) != null) {
				return new ModelAndView("personAddition","error","Login déjà existant.");
			}
			daoPerson.addPerson(p);
			return new ModelAndView("redirect:detail.htm?id=" + p.getLogin());
		}

		return new ModelAndView("redirect:annuaire.htm");
	}

	/**
	 * Supprimer une personne via le DAO si on en a les droits 
	 * @param p La personne p que l'on veut supprimer
	 * @return Une redirection vers la liste des personnes
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String deletePerson(@ModelAttribute Person p) {

		if(p == null || p.getLogin().isEmpty()) {
			return "redirect:annuaire.htm";
		}

		if (user.isAdmin()){
			daoPerson.deletePerson(p.getLogin());
		}

		return "redirect:annuaire.htm";
	}

	/**
	 * Créé une liste de tous les groupes existant via le DAO, et la renvoie
	 * @return la liste de tous les groupes existant
	 */
	@ModelAttribute("groupsList")
	public List<Group> productTypes() {
		return (List<Group>) daoGroup.findAllGroups();
	}

	/**
	 * Permet de récupérer la liste des groupes séléctionnés via un formulaire,
	 * et de la renvoyer sous un type exploitable
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "groups", new CustomCollectionEditor(Set.class)
		{
			@Override
			protected Object convertElement(Object element)
			{
				Set<Group> retour = new HashSet<Group>();
				retour.add(daoGroup.findGroup((String) element));
				return retour;
			}
		});
	}

}
