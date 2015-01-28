package annuaire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import annuaire.model.Person;
import annuaire.services.IDAOPerson;

@Controller
@RequestMapping("auth")
public class AuthentificationController {
	
	@Autowired()
	User user;
	
	@Autowired()
    IDAOPerson daoPerson;
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login () {
		return "login";
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public String login (@ModelAttribute User u, BindingResult result) {
		
		if(result.hasErrors()) {
    		return "login";
    	}

		String login = u.getLogin();
		String password = u.getPassword();
		
		Person p = daoPerson.findPerson(login);
		if (p == null || !p.getPassword().equals(password)) {
			u.setPassword("");
			return "redirect:login.htm";
		}
		
		user.setConnected(true);
		user.setLogin(login);
		
		return "redirect:/person/annuaire.htm";
	}
	
	@RequestMapping(value = "/logout.htm")
	public String logout () {
		user.flush();
		return "redirect:login.htm";
	}
	
	@ModelAttribute("user")
    public User newUser() {
        return user;
    }
}
