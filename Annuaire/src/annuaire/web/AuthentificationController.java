package annuaire.web;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import annuaire.model.Group;
import annuaire.model.Person;
import annuaire.services.IDAOPerson;

/**
 * AuthentificationController est la classe contrôleur qui va traiter les
 * événements "connexion" et "déconnexion" de l'utilisateur correspondant
 * aux URL "/Annuaire/auth/login.htm" et "/Annuaire/auth/logout.htm".
 * 
 * @author Quentin Cheynet & Yoann Moisset
 *
 */

@Controller
@RequestMapping("auth")
public class AuthentificationController {
	
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
	@Autowired()
    IDAOPerson daoPerson;
	
	/**
	 * Retourne la vue du formulaire d'authentification correspondant à 
	 * l'URL /Annuaire/auth/login.hmt en méthode GET.
	 * 
	 * @return La vue du formulaire d'authentification.
	 */
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login () {
		return "login";
	}
	
	/**
	 * Méthode appelée lors de la validation du formulaire d'authentification.
	 * Vérifie si le login et le mot de passe sont corrects, puis place les
	 * informations de la personne en session via la classe User.
	 * 
	 * @param @see User u L'utilisateur en session.
	 * @param result Le résultat du binding.
	 * @return La vue du formulaire d'authentification avec un message en cas d'erreur(s),
	 * la vue correspondant à la liste des personnes de l'annuaire sinon.
	 */
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView login (@ModelAttribute User u, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("login", "error", "");
    	}

		String login = u.getLogin();
		String password = u.getPassword();
		
		Person p = daoPerson.findPerson(login);
		if (p == null || !p.getPassword().equals(password)) {
			u.setPassword("");
			return new ModelAndView("login", "error", "Login ou mot de passe invalide");
		}
		
		user.setConnected(true);
		user.setLogin(login);
		user.setGroups(new HashSet<Group>());
		
		for(Group g : p.getGroups()){
			Group tmp = new Group();
			tmp.setGroupname(g.getGroupname());
			user.getGroups().add(tmp);
		}
		
		return new ModelAndView("redirect:/person/annuaire.htm");
	}
	
	/**
	 * Vide la session utilisateur puis retourne la vue du formulaire
	 * d'authentification.
	 * 
	 * @return Redirige vers la page d'authentification.
	 */
	@RequestMapping(value = "/logout.htm")
	public String logout () {
		user.flush();
		return "redirect:login.htm";
	}
	
	/**
	 * Permet d'avoir accès à l'utilisateur en session.
	 * 
	 * @return @see User L'utilisateur en session.
	 */
	@ModelAttribute("user")
    public User newUser() {
        return user;
    }
}
