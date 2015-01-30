package annuaire.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

import annuaire.model.Group;
import annuaire.model.Person;
import annuaire.services.IDAOGroup;
import annuaire.services.IDAOPerson;

@Controller()
@RequestMapping("/person")
public class PersonController {

	@Autowired()
	User user;

	@Autowired
	IDAOPerson daoPerson;

	@Autowired
	IDAOGroup daoGroup;

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
	//	p.setGroups(new HashSet<Group>());
		return p;
	}

	@RequestMapping(value = "/edition.htm", method = RequestMethod.GET)
	public String editPerson(@ModelAttribute Person p) {

		if(p == null || p.getLogin().isEmpty()) {
			return "redirect:annuaire.htm";
		}

		if(!p.getLogin().equals(user.getLogin()) && !p.getGroups().contains("ADMINISTRATEUR")) {
			return "redirect:annuaire.htm";
		}

		return "personForm";
	}

	@RequestMapping(value = "/edition.htm", method = RequestMethod.POST)
	public String savePerson(@ModelAttribute @Valid Person p, BindingResult result) {
		if(result.hasErrors()) {
			return "personForm";
		}

		if(p == null || p.getLogin().isEmpty()) {
			return "redirect:annuaire.htm";
		}

		if(!p.getLogin().equals(user.getLogin()) && !p.getGroups().contains("ADMINISTRATEUR")) {
			return "redirect:annuaire.htm";
		}

		daoPerson.savePerson(p);

		return "redirect:detail.htm?id=" + p.getLogin();
	}

	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addPerson() {

		return "personAddition";
	}

	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)
	public String saveNewPerson(@ModelAttribute @Valid Person p, BindingResult result) {

		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "personAddition";
		}


//		for(Group g : user.getGroups()){
//			if (g.getGroupname().equals("ADMINISTRATEUR")){
				daoPerson.addPerson(p);
				return "redirect:detail.htm?id=" + p.getLogin();
//			}
//		}

		//return "redirect:annuaire.htm";
	}



	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String deletePerson(@ModelAttribute Person p) {

		if(p == null || p.getLogin().isEmpty()) {
			return "redirect:annuaire.htm";
		}

		if(!p.getGroups().contains("ADMINISTRATEUR")) {
			return "redirect:annuaire.htm";
		}

		daoPerson.deletePerson(p.getLogin());

		return "redirect:annuaire.htm";
	}

	//	@ModelAttribute("groupsList")
	//	public Map<Set<Group>, String> productTypes() {
	//		
	//		Collection<Group> groups = daoGroup.findAllGroups();
	//		
	//	    Map<Set<Group>,String> types = new LinkedHashMap<Set<Group>,String>();
	//	    
	//	    for(Group g: groups){
	//	    	Set<Group> tmpSG = new HashSet<Group>();
	//	    	tmpSG.add(g);
	//	    	types.put(tmpSG, g.getGroupname());
	//	    }
	//	    return types;
	//	}

	@ModelAttribute("groupsList")
	public List<Group> productTypes() {

		return (List<Group>) daoGroup.findAllGroups();
	}



	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "groups", new CustomCollectionEditor(Set.class)
		{
			@Override
			protected Object convertElement(Object element)
			{

				System.out.println("-------------"+(String) element);

				Set<Group> retour = new HashSet<Group>();
				retour.add(daoGroup.findGroup((String) element));
				return retour;
			}
		});
	}

}
