package annuaire.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@NotNull(message ="Login obligatoire")
	private String login;
	@NotNull(message ="Nom obligatoire")
	private String lastName;
	@NotNull(message ="Prénom obligatoire")
	@Size (min =1, message ="Prénom obligatoire")
	private String firstName;
	
	@NotNull(message ="adresse email obligatoire.")
	@Pattern(regexp=".+@.+\\.[a-z]+", message="Adresse email invalide.")
	private String mail;
	private String website;
	
	private Date dateOfBirth;
	@NotNull(message ="Mot de passe obligatoire")
	private String password;
	
	@ManyToMany
	@JoinTable(name ="PERSON_GROUP", 
	joinColumns =
		@JoinColumn (name = "login_PERSON"),
	inverseJoinColumns=
		@JoinColumn(name="name_GROUP")
	)
	private Set<Group> groups;
	
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
