package annuaire.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import annuaire.model.Person;
import annuaire.services.IDAOPerson;
import annuaire.services.IEmailSender;

/**
 * PWRetrievingController est la classe contrôleur qui va traiter la
 * récupération de mot de passe.
 * 
 * URL "/Annuaire/auth/retrievepw.htm"
 * 
 * @author Quentin Cheynet & Yoann Moisset
 *
 */

@Controller
@RequestMapping("auth")
public class PWRetrievingController {

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
	 * Service d'envoi de mail.
	 * 
	 * @see IEmailSender
	 */
	@Autowired()
	IEmailSender emailSender;
	
	/**
	 * Retourne la vue du formulaire de récupération du mot de passe 
	 * correspondant à l'URL /Annuaire/auth/retrievepw.htm en méthode GET.
	 * 
	 * @return La vue du formulaire d'authentification.
	 */
	@RequestMapping(value = "/retrievepw.htm", method = RequestMethod.GET)
	public String retreivePW () {
		return "passwordRetrieve";
	}
	
	/**
	 * Méthode appelée lors de la validation du formulaire de récupération
	 * du mot de passe.
	 * Elle récupère, dans la base de données, la personne correspondant au
	 * login renseigné dans le formulaire, puis si la personne est présente 
	 * dans l'annuaire, les actions suivantes sont effectuées :
	 * - génération d'un nouveau mot de passe
	 * - envoi, par mail, de ce nouveau mot de passe
	 * - sauvegarde ce nouveau mot de passe en base 
	 * 
	 * @param @see User u L'utilisateur en session.
	 * @param result Le résultat du binding.
	 * @return La vue du formulaire d'authentification en cas d'erreur,
	 * le vue du formulaire de récupération du mot de passe si la personne
	 * n'est pas dans l'annuaire, la vue du formulaire d'authentification
	 * en cas de succès.
	 */
	@RequestMapping(value = "/retrievepw.htm", method = RequestMethod.POST)
	public String sendMail (@ModelAttribute User u, BindingResult result) {

		if(result.hasErrors()) {
    		return "login";
    	}
	
		String login = u.getLogin();
		
		Person p = daoPerson.findPerson(login);
		if (p == null) {
			return "redirect:retrievepw.htm";
		}
		String generatedPW = UUID.randomUUID().toString().substring(0, 8);
		String message = "Voici votre nouveau mot de passe : \n" + generatedPW 
				+ "\n vous pourrez le modifier via l'édition.\n" 
				+ "Si vous n'avez pas demandé une réinitialisation de votre mot de passe, on essaie de vous hacker !!";
		emailSender.send(p.getMail(), message);
		p.setPassword(generatedPW);
		daoPerson.savePerson(p);
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
