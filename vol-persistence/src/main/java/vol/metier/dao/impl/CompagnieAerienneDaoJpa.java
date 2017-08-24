package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.CompagnieAerienneDao;
import vol.metier.model.CompagnieAerienne;

@Transactional
@Repository
public class CompagnieAerienneDaoJpa implements CompagnieAerienneDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public CompagnieAerienne find(Long id) {
		return em.find(CompagnieAerienne.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompagnieAerienne> findAll() {
		Query query = em.createQuery("from CompagnieAerienne ca");
		return query.getResultList();
	}

	@Override
	public void create(CompagnieAerienne compagnieAerienne) {
		em.persist(compagnieAerienne);
	}

	@Override
	public CompagnieAerienne update(CompagnieAerienne compagnieAerienne) {
		return em.merge(compagnieAerienne);

	}

	@Override
	public void delete(CompagnieAerienne compagnieAerienne) {
		em.merge(compagnieAerienne);
		em.remove(em.contains(compagnieAerienne) ? compagnieAerienne : em.merge(compagnieAerienne));
		//em.remove(em.merge(compagnieAerienne));

	}
}
