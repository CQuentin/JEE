package annuaire.web;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import annuaire.model.Group;
import annuaire.model.Person;
import annuaire.services.IDAOGroup;

/**
* GroupController le controller de Group
*
* @author Quentin Cheynet
* Yoann Moisset
*/

@Controller()
@RequestMapping("/group")
public class GroupController {

	@Autowired()
	User user;

	@Autowired
	IDAOGroup daoGroup;

	/**
	 * 
	 * @return le nom du fichier listGroup.jspx qui affiche tous les groupes existant
	 */
	@RequestMapping(value = "/groups.htm")
	public String list() {
		return "listGroup";
	}

	/**
	 * 
	 * @return une collection de Group via le DAO à partir la table MYGROUP de la base de donnée
	 */
	@ModelAttribute("groups")
	public Collection<Group> groups() {
		return daoGroup.findAllGroups();
	}

	/**
	 * 
	 * @param groupname Le nom du Group de la base de donnée que l'on veut renvoyer
	 * @return Group, avec les attributs correspondant au groupe de la base de donnée
	 */
	@ModelAttribute("group")
	public Group newGroup(
			@RequestParam(value = "id", required = false) String groupname) {
		if (groupname != null) {
			return daoGroup.findGroup(groupname);
		}

		Group g = new Group();
		g.setGroupname(null);
		g.setPersons(new HashSet<Person>());
		return g;
	}

	/**
	 * Donne l'accès à la modification d'un groupe si on en a les droits
	 * @param g Le groupe g dont on veut éditer les détails
	 * @return	- le nom du fichier groupForm.jspx si on est un administrateur
	 * 			- une redirection vers la liste des groupes sinon
	 */
	@RequestMapping(value = "/edition.htm", method = RequestMethod.GET)
	public String editGroup(@ModelAttribute Group g) {

		if(g == null || g.getGroupname().isEmpty()) {
			return "redirect:groups.htm";
		}

		if(user.isAdmin()) {
			return "groupForm";
		}
		return "redirect:groups.htm";

	}

	/**
	 * Modifie des détails d'un groupe via le DAO si on en a les droits
	 * @param g Le groupe g dont on veut éditer les détails
	 * @param result
	 * @return	- une redirection vers la liste des groupes
	 *			- le nom du fichier groupForm.jspx si result indique qu'il y a des erreurs ou si le nom du groupe existe déjà
	 */
	@RequestMapping(value = "/edition.htm", method = RequestMethod.POST)
	public ModelAndView saveGroup(@ModelAttribute @Valid Group g, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("groupForm");
		}

		if(g == null || g.getGroupname().isEmpty()) {
			return new ModelAndView("redirect:groups.htm");
		}

		if(user.isAdmin()) {
			if(daoGroup.findGroup(g.getGroupname()) != null) {
				return new ModelAndView("groupForm","error","Ce groupe existe déjà.");
			}
			daoGroup.saveGroup(g);
		}
		return new ModelAndView("redirect:groups.htm");

	}

	/**
	 * Donne l'accès à l'ajout d'un groupe si on en a les droits
	 * @return	- le nom du fichier groupAddition.jspx si on est un administrateur
	 *			- une redirection vers la liste des personnes sinon
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addGroup() {
		if (user.isAdmin()){
			return "groupAddition";
		}
		return "redirect:groups.htm";
	}

	/**
	 * Ajoute une personne via le DAO si on en a les droits 
	 * @param g Le groupe g que l'on veut ajouter
	 * @param result Le résultat de la requête, pour savoir si elle contient des erreur
	 * @return	- une redirection vers la liste des groupes
	 * 			- le nom du fichier groupAddition.jspx si result indique qu'il y a des erreurs ou si le groupe existe déjà
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)
	public ModelAndView saveNewGroup(@ModelAttribute @Valid Group g, BindingResult result) {

		if(result.hasErrors()) {
			return new ModelAndView("groupAddition");
		}

		if (user.isAdmin()){
			if(daoGroup.findGroup(g.getGroupname()) != null) {
				return new ModelAndView("groupAddition","error","Ce groupe existe déjà.");
			}
			daoGroup.addGroup(g);
		}
		return new ModelAndView("redirect:groups.htm");
	}

	/**
	 * Supprimer un groupe via le DAO si on en a les droits 
	 * @param g Le groupe g que l'on veut supprimer
	 * @return Une redirection vers la liste des groupes
	 */
		@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
		public String deleteGroup(@ModelAttribute Group g) {

			if(g == null || g.getGroupname().isEmpty()) {
				return "redirect:groups.htm";
			}

			if (user.isAdmin()){
				daoGroup.deleteGroup(g.getGroupname());
			}
			return "redirect:groups.htm";
		}
		
		/**
		 *  créé et retourne un User
		 * @return Le User
		 */
		@ModelAttribute("user")
		public User newUser() {
			return user;
		}
	}
