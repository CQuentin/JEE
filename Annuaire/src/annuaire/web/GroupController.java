package annuaire.web;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import annuaire.model.Group;
import annuaire.services.IDAOGroup;

@Controller()
@RequestMapping("/group")
public class GroupController { //TODO gerer interceptor

	@Autowired()
	User user;
	
	@Autowired
	IDAOGroup daoGroup;
	
	@RequestMapping(value = "/groups.htm")
	public String list() {
		return "listGroup";
	}

	@ModelAttribute("groups")
	public Collection<Group> groups() {
		return daoGroup.findAllGroups();
	}




	@ModelAttribute("group")
	public Group newGroup(
			@RequestParam(value = "id", required = false) String groupname) {

		if (groupname != null) {
			return daoGroup.findGroup(groupname);
		}

		Group g = new Group();
		g.setGroupname("");
		return g;
	}

	@RequestMapping(value = "/edition.htm", method = RequestMethod.GET)
	public String editGroup(@ModelAttribute Group g) {

		if(g == null || g.getGroupname().isEmpty()) {
			return "redirect:groups.htm";
		}

		if(!user.getGroups().contains("ADMINISTRATEUR")) {
			return "redirect:groups.htm";
		}

		return "groupForm";
	}

	@RequestMapping(value = "/edition.htm", method = RequestMethod.POST)
	public String saveGroup(@ModelAttribute @Valid Group g, BindingResult result) {
		if(result.hasErrors()) {
			return "groupForm";
		}
		
		if(g == null || g.getGroupname().isEmpty()) {
			return "redirect:groups.htm";
		}

		if(!user.getGroups().contains("ADMINISTRATEUR")) {
			return "redirect:groups.htm";
		}

		daoGroup.saveGroup(g);

		return "redirect:groups.htm";
	}

	@RequestMapping(value = "/add.htm")
	public String addGroup() {
		return "groupAddition";
	}

	@RequestMapping(value = "/add.htm", method = RequestMethod.POST)
	public String saveNewGroup(@ModelAttribute @Valid Group g, BindingResult result) {

		if(result.hasErrors()) {
			return "groupAddition";
		}

		if(!user.getGroups().contains("ADMINISTRATEUR")) {
			return "redirect:groups.htm";
		}
		
		daoGroup.addGroup(g);

		return "redirect:groups.htm";
	}

	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String deleteGroup(@ModelAttribute Group g) {

		if(g == null || g.getGroupname().isEmpty()) {
			return "redirect:groups.htm";
		}

		daoGroup.deleteGroup(g.getGroupname());

		return "redirect:groups.htm";
	}
	
	@ModelAttribute("user")
	public User newUser() {
		return user;
	}
	
//	@ModelAttribute("groupsList")
//	public Map<String, String> productTypes() {
//		
//		ArrayList<Group> groups = (ArrayList<Group>) daoGroup.findAllGroups();
//		
//	    Map<String, String> types = new LinkedHashMap<>();
//	    
//	    for(Group g: groups){
//	    	types.put(g.getGroupname(), g.getGroupname());
//	    }
//	    return types;
//	}
	
}
