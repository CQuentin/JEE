package annuaire.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
* Group Classe représentant les groupes auxquels peuvent appartenir les personnes
*
* @author Quentin Cheynet
* @author Yoann Moisset
*/

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
	
	/**
	 * Ensemble des personnes qui appartiennet à ceux groupe.
	 * Permet d'assurer l'association ManyToMany en bidirectionnel
	 */
	@ManyToMany(mappedBy="groups")
	private Set<Person> persons;
	
	/**
	 * 
	 * @return l'attribut persons
	 */
	public Set<Person> getPersons() {
		return persons;
	}

	/**
	 * 
	 * @param persons Le set de Person que l'on veut affecter à persons
	 */
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	/**
	 * 
	 * @return Le nom du groupe
	 */
	public String getGroupname() {
		return groupname;
	}

	/**
	 *
	 * @param groupname Le nom du groupe que l'on veut affecter à groupname
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
