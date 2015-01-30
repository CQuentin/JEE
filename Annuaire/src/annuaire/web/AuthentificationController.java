package annuaire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
			//return "redirect:login.htm";
		}
		
		user.setConnected(true);
		user.setLogin(login);
		
//		for(Group g : p.getGroups()){
//			Group tmp = new Group();
//			tmp.setGroupname(g.getGroupname());
//			user.getGroups().add(tmp);
//		}
		
		//return "redirect:/person/annuaire.htm";
		return new ModelAndView("redirect:/person/annuaire.htm");
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
