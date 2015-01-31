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

import annuaire.model.Person;
import annuaire.services.IDAOPerson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class DAOPersonTest {
	
	@Autowired
	IDAOPerson daoPerson;
	
	private Person p1;
	private Person p2;

	@Before
	public void initialize() {
		
		p1 = new Person();
		p1.setLogin("p1");
		p1.setFirstName("Alain");
		p1.setLastName("Connu");
		p1.setMail("alain.connu@gmail.com");
		p1.setPassword("12345678");
		
		p2 = new Person();
		p2.setLogin("p2");
		p2.setFirstName("Jean");
		p2.setLastName("Brouille");
		p2.setMail("jean.brouille@gmail.com");
		p2.setPassword("12345678");
		
		daoPerson.addPerson(p1);
		daoPerson.addPerson(p2);		
	}
	
	@After
	public void tearDown () {
		//rien à faire car un rollback est effectué après chaque test
	}
	
	/**
	 * Recherche d'une personne existante
	 */
	@Test
	public void findPersonTest() {
		Person actual = daoPerson.findPerson("p1");	
		Assert.assertEquals(p1, actual);
	}
	
	/**
	 * Recherche d'une personne non existante
	 */
	@Test
	public void findPersonTest2() {
		Person actual = daoPerson.findPerson("p3");	
		Assert.assertNull(actual);
	}
	
	/**
	 * Ajout d'une personne
	 */
	@Test
	public void addPersonTest() {
		Person expected = new Person();
		expected.setLogin("p3");
		expected.setFirstName("Bart");
		expected.setLastName("Abba");
		expected.setMail("bart.abba@gmail.com");
		expected.setPassword("12345678");
		
		daoPerson.addPerson(expected);
		
		Person actual = daoPerson.findPerson("p3");
		
		Assert.assertEquals(expected, actual);
	}

	/**
	 * Suppression d'une personne existante
	 */
	@Test
	public void deletePersonTest() {
		daoPerson.deletePerson("p2");
		Person actual = daoPerson.findPerson("p2");
		Assert.assertNull(actual);
	}
	
	/**
	 * Suppression d'une personne inexistante
	 */
	@Test
	public void deletePersonTest2() {
		daoPerson.deletePerson("p3");
	}
	
	/**
	 * Sauvegarde d'une personne déjà existante dans la table
	 * après modification de son mot de passe
	 */
	@Test
	public void savePersonTest() {
		p2.setPassword("87654321");
		daoPerson.savePerson(p2);
		Person actual = daoPerson.findPerson("p2");
		Assert.assertEquals(p2, actual);
	}
	
	/**
	 * Récupération de l'ensemble des personnes
	 */
	@Test
	public void findAllPersonsTest() {
		Collection<Person> expected = new ArrayList<Person>();
		expected.add(p1);
		expected.add(p2);
		
		Collection<Person> actual = daoPerson.findAllPersons();
		
		Assert.assertEquals(expected, actual);
	}
}
