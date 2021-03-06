package annuaire.services.imp;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import annuaire.model.Person;
import annuaire.services.IDAOPerson;

/**
* DAOPerson La classe qui permet d'accéder à la table PERSON de la base de donnée
*
* @author Quentin Cheynet
* @author Yoann Moisset
*/

@Stateless
@LocalBean()
@Startup
@Repository
public class DAOPerson implements IDAOPerson {

	@PersistenceContext(unitName = "myMySQLBase")
    EntityManager em;

    public DAOPerson() {
    }

    @PostConstruct
    public void init() {
        System.out.println("INIT EJB = " + this);
    }
	
	@Override
	public Collection<Person> findAllPersons() {
		return em.createQuery("Select p From Person p", Person.class)
                .getResultList();
	}

	@Override
	public Person findPerson(String id) {
		try {
			return em.createQuery("Select p From Person p Where p.login = :id", Person.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void savePerson(Person p) {
		em.merge(p);
	}

	@Override
	public void addPerson(Person p) {
		em.persist(p);
	}

	@Override
	public void deletePerson(String id) {
		em.createQuery("Delete from Person p where p.login = :id")
		.setParameter("id", id).executeUpdate();
	}

}
