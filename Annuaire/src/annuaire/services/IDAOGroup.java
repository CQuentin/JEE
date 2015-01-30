package annuaire.services;

import java.util.Collection;

import annuaire.model.Group;

public interface IDAOGroup {
	// récupérer les personnes
	Collection<Group> findAllGroups();

	// lire une personne
	Group findGroup(String groupname);
	
	// modification ou ajout d'une nouvelle personne
	void saveGroup(Group g);

	void addGroup(Group g);

	void deleteGroup(String groupname);
}
