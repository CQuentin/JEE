package annuaire.services.tests;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import annuaire.model.Group;
import annuaire.services.IDAOGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class DAOGroupTest {

	@Autowired
	IDAOGroup daoGroup;
	
	private Group g1;
	private Group g2;

	@Before
	public void initialize() {
		
		g1 = new Group();
		g1.setGroupname("FISL");
		
		g2 = new Group();
		g2.setGroupname("ID");
		
		daoGroup.addGroup(g1);
		daoGroup.addGroup(g2);		
	}
	
	@After
	public void tearDown () {
		//rien à faire car un rollback est effectué après chaque test
	}
	
	/**
	 * Recherche d'un groupe existant
	 */
	@Test
	public void findGroupTest() {
		Group actual = daoGroup.findGroup("g1");	
		Assert.assertEquals(g1, actual);
	}
	
	/**
	 * Recherche d'un groupe non existant
	 */
	@Test
	public void findGroupTest2() {
		Group actual = daoGroup.findGroup("g3");	
		Assert.assertNull(actual);
	}
	
	/**
	 * Ajout d'un groupe
	 */
	@Test
	public void addGroupTest() {
		Group expected = new Group();
		expected.setGroupname("g3");
		
		daoGroup.addGroup(expected);
		
		Group actual = daoGroup.findGroup("g3");
		
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Suppression d'un groupe existant
	 */
	@Test
	public void deleteGroupTest() {
		daoGroup.deleteGroup("g2");
		Group actual = daoGroup.findGroup("g2");
		Assert.assertNull(actual);
	}
	
	/**
	 * Suppression d'un groupe inexistant
	 */
	@Test
	public void deleteGroupTest2() {
		daoGroup.deleteGroup("g3");
	}
	
	/**
	 * Sauvegarde d'un groupe déjà existant dans la table
	 * après modification de son nom
	 */
	@Test
	public void saveGroupTest() {
		g2.setGroupname("M2PID");
		daoGroup.saveGroup(g2);
		Group actual = daoGroup.findGroup("g2");
		Assert.assertEquals(g2, actual);
	}
	
	/**
	 * Récupération de l'ensemble des groupes
	 */
	@Test
	public void findAllPersonsTest() {
		Collection<Group> expected = new ArrayList<Group>();
		expected.add(g1);
		expected.add(g2);
		
		Collection<Group> actual = daoGroup.findAllGroups();
		
		Assert.assertEquals(expected, actual);
	}

}
