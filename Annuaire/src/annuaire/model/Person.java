package annuaire.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Classe entité représentant la table PERSON et servant à la persitance
 * des données (personnes).
 * 
 * @author Quentin Cheynet & Yoann Moisset
 *
 */

@Entity
@Table(name = "PERSON")
public class Person implements Serializable{

	private static final long serialVersionUID = -4665972864170969800L;
	
	/**
	 * Clé primaire.
	 * Login de la personne.
	 */
	@Id
	@NotNull(message = "Login obligatoire.")
	@Size (min = 1, message = "Login obligatoire.")
	private String login;
	
	/**
	 * Nom de la personne.
	 */
	@NotNull(message = "Nom obligatoire.")
	@Size (min = 1, message = "Nom obligatoire.")
	private String lastName;
	
	/**
	 * Prénom de la personne.
	 */
	@NotNull(message = "Prénom obligatoire.")
	@Size (min = 1, message = "Prénom obligatoire.")
	private String firstName;
	
	/**
	 * Adresse mail de la personne.
	 */
	@NotNull(message = "Adresse email obligatoire.")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Adresse email invalide.")
	private String mail;
	
	/**
	 * Adresse du site web de la personne.
	 */
	private String website;

	/**
	 * Date de naissance de la personne.
	 */
	private Date dateOfBirth;
	
	/**
	 * Mot de passe de la personne.
	 */
	@NotNull(message = "Mot de passe obligatoire.")
	@Size (min = 8, message = "Le mot de passe doit avoir au moins 8 caractères.")
	private String password;
	
	/**
	 * Ensemble des groupes auxquels la personne appartient.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="PERSON_GROUP", 
	joinColumns =
		@JoinColumn (name = "login_PERSON"),
	inverseJoinColumns=
		@JoinColumn(name="name_GROUP")
	)
	private Set<Group> groups;
	
	/**
	 * 
	 * @return L'ensemble des groupes auxquels la personne appartient.
	 */
	public Set<Group> getGroups() {
		return groups;
	}
	
	/**
	 * 
	 * @param groups L'ensemble des groupes auxquels la personne appartient.
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	/**
	 * 
	 * @return Le login de la personne.
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * 
	 * @param login Le login de la personne.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * 
	 * @return Le nom de la personne.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * 
	 * @param lastName Le nom de la personne.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @return Le prénom de la personne.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 
	 * @param firstName Le prénom de la personne.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * 
	 * @return L'adresse mail de la personne.
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * 
	 * @param mail L'adresse mail de la personne.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * 
	 * @return L'adresse du site web de la personne.
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * 
	 * @param website L'adresse du site web de la personne.
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	/**
	 * 
	 * @return La date de naissance de la personne.
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * 
	 * @param dateOfBirth La date de naissance de la personne.
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * 
	 * @return Le mot de passe de la personne.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password Le mot de passe de la personne.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
