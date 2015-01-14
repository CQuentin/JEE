package annuaire.services;

import java.util.Collection;

import annuaire.model.Person;

public interface IDAOPerson {
	
	// récupérer les personnes
	Collection<Person> findAllPersons();

	// lire une personne
	Person findPerson(String id);
	
	// modification ou ajout d'une nouvelle personne
	void savePerson(Person p);
}
