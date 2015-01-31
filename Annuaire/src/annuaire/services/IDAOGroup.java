package annuaire.services;

import java.util.Collection;

import annuaire.model.Group;
/**
* IDAOGroup Interface pour la classe DAOGroup, la classe qui permet d'accéder à la table MYGROUP de la base de donnée
*
* @author Quentin Cheynet
* @author Yoann Moisset
*/
public interface IDAOGroup {
	/**
	 * 
	 * @return Tous les groupes de la table MYGROUP
	 */
	Collection<Group> findAllGroups();

	/**
	 * 
	 * @param groupname Le nom du groupe que l'on veut
	 * @return Le groupe correspondant au groupe de la base de donnée
	 */
	Group findGroup(String groupname);
	
	/**
	 * Modifie un groupe dans la base de donnée
	 * @param g Le groupe que l'on veut modifier
	 */
	void saveGroup(Group g);

	/**
	 * Ajoute un groupe dans la base de donnée
	 * @param g Le groupe que l'on veut ajouter
	 */
	void addGroup(Group g);

	/**
	 * Supprime un groupe dans la base de donnée
	 * @param groupname Le nom du groupe que l'on veut supprimer
	 */
	void deleteGroup(String groupname);
}
