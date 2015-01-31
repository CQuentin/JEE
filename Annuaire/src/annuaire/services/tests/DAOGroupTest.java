package annuaire.services.tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import annuaire.model.Group;
import annuaire.model.Person;
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
		
		g2 = new Group();
		
		daoGroup.addGroup(g1);
		daoGroup.addGroup(g2);		
	}

}
