package annuaire.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import annuaire.model.Person;
import annuaire.services.IDAOPerson;

@Controller()
@RequestMapping("/person")
public class PersonController {

	@Autowired()
	User user;
	
	@Autowired
    IDAOPerson daoPerson;
	
	@RequestMapping(value = "/annuaire.htm")
    public String list() {
        return "listPerson";
    }

    @ModelAttribute("persons")
    public Collection<Person> persons() {
        return daoPerson.findAllPersons();
    }
    
    @RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
    public String detailPerson(@ModelAttribute Person p) {
    	
    	if(p.getLogin().isEmpty()) {
    		return "redirect:annuaire.htm";
    	}
        return "person";
    }
    
    @ModelAttribute("user")
    public User newUser() {
        return user;
    }
    
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
        return p;
    }
  
    @RequestMapping(value = "/edition.htm", method = RequestMethod.GET)
    public String editPerson(@ModelAttribute Person p) {
    	if(p.getLogin().isEmpty()) {
    		return "redirect:annuaire.htm";
    	}
    	
    	if(!p.getLogin().equals(user.getLogin())) {
    		return "redirect:annuaire.htm";
    	}
    	
    	return "personForm";
    }
    
    @RequestMapping(value = "/edition.htm", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute Person p, BindingResult result) {
    	
    	if(result.hasErrors()) {
    		return "personForm";
    	}
    	
    	if(!p.getLogin().equals(user.getLogin())) {
    		return "redirect:annuaire.htm";
    	}
    	
    	daoPerson.savePerson(p);
    	
    	return "redirect:detail.htm?id=" + p.getLogin();
    }
    
    @RequestMapping(value = "/add.htm")
    public String addPerson() {
    	return "personAddition";
    }
    
    @RequestMapping(value = "/add.htm", method = RequestMethod.POST)
    public String saveNewPerson(@ModelAttribute Person p, BindingResult result) {
    	
    	if(result.hasErrors()) {
    		return "personAddition";
    	}
    	
    	daoPerson.addPerson(p);
    	
    	return "redirect:detail.htm?id=" + p.getLogin();
    }
    
    @RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
    public String deletePerson(@ModelAttribute Person p) {
    	
    	if(p.getLogin().isEmpty()) {
    		return "redirect:annuaire.htm";
    	}
    	
    	daoPerson.deletePerson(p.getLogin());
    	
    	return "redirect:annuaire.htm";
    }
}
