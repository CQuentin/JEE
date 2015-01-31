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
		g.setGroupname(null);
		g.setPersons(new HashSet<Person>());
		return g;
	}

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

	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String addGroup() {
		if (user.isAdmin()){
			return "groupAddition";
		}
		return "redirect:groups.htm";
	}

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

		@ModelAttribute("user")
		public User newUser() {
			return user;
		}

	}
