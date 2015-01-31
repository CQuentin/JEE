package annuaire.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MYGROUP")
public class Group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6631528810771530927L;
	@Id
	private int ID;
	
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
