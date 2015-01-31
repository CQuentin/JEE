package annuaire.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MYGROUP")
public class Group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6631528810771530927L;
	@Id
	private int ID;
	
	@Size (min =1, max = 50, message ="Le nom du groupe doit compter entre 1 et 50 caractères.")
	private String groupname;
	
	@ManyToMany(mappedBy="groups")
	private Set<Person> persons;
	
	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
