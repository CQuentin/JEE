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

import annuaire.model.Group;
import annuaire.services.IDAOGroup;

@Stateless
@LocalBean()
@Startup
@Repository
public class DAOGroup implements IDAOGroup {

	@PersistenceContext(unitName = "myMySQLBase")
	EntityManager em;

	public DAOGroup() {
	}

	@PostConstruct
	public void init() {
		System.out.println("INIT EJB = " + this);
	}

	@Override
	public Collection<Group> findAllGroups() {
		return em.createQuery("Select g From Group g", Group.class)
				.getResultList();
	}

	@Override
	public Group findGroup(String groupname) {
		try {
			return em.createQuery("Select g From Group g Where g.groupname = :groupname", Group.class)
					.setParameter("groupname", groupname).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveGroup(Group g) {
		em.merge(g);		
	}

	@Override
	public void addGroup(Group g) {
		em.persist(g);
	}

	@Override
	public void deleteGroup(String groupname) {
		em.createQuery("Delete from Group g where g.groupname = :groupname")
		.setParameter("groupname", groupname).executeUpdate();
	}

}
