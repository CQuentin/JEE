package annuaire.services.imp;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import annuaire.model.Person;
import annuaire.services.IDAOPerson;

@Stateless
@LocalBean()
@Startup
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
		em.persist(p);
	}

}
