package annuaire.services;

import java.util.Collection;

import annuaire.model.Person;
/**
* IDAOPerson Interface pour la classe DAOPerson, la classe qui permet d'accéder à la table PERSON de la base de donnée
*
* @author Quentin Cheynet
* @author Yoann Moisset
*/
public interface IDAOPerson {
	
	/**
	 * 
	 * @return  Toutes les personnes de la table PERSON
	 */
	Collection<Person> findAllPersons();

	/**
	 * 
	 * @param id Le login de la personne que l'on veut
	 * @return La personne correspondante à la personne de la base de donnée
	 */
	Person findPerson(String id);
	
	/**
	 * Modifie une personne dans la base de donnée
	 * @param p La personne que l'on veut modifier
	 */
	void savePerson(Person p);

	/**
	 * Ajouter une personne dans la base de donnée
	 * @param p La personne que l'on veut ajouter
	 */
	void addPerson(Person p);

	/**
	 * Supprime une personne dans la base de donnée
	 * @param id Le login de la personne que l'on veut supprimer
	 */
	void deletePerson(String id);
}
