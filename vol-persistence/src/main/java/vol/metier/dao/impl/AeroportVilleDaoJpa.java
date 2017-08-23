package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.AeroportVilleDao;
import vol.metier.model.AeroportVille;

@Transactional
@Repository
public class AeroportVilleDaoJpa implements AeroportVilleDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public AeroportVille find(Long id) {
		return em.find(AeroportVille.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<AeroportVille> findAll() {
		Query query = em.createQuery("from AeroportVille av");
		return query.getResultList();
	}

	@Override
	public void create(AeroportVille villeAeroport) {
		em.persist(villeAeroport);

	}

	@Override
	public AeroportVille update(AeroportVille villeAeroport) {
		return em.merge(villeAeroport);
	}

	@Override
	public void delete(AeroportVille villeAeroport) {
		em.remove(em.merge(villeAeroport));

	}

}
