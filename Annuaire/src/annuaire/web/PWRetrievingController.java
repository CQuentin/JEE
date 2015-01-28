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

@Controller
@RequestMapping("auth")
public class PWRetrievingController {

	@Autowired()
	User user;
	
	@Autowired()
    IDAOPerson daoPerson;
	
	@Autowired()
	IEmailSender emailSender;
	
	@RequestMapping(value = "/retrievepw.htm", method = RequestMethod.GET)
	public String retreivePW () {
		return "passwordRetrieve";
	}
	
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
	
	@ModelAttribute("user")
    public User newUser() {
        return user;
    }
}
