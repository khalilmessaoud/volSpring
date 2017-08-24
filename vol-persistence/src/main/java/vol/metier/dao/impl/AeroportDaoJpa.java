package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.AeroportDao;
import vol.metier.dao.EscaleDao;
import vol.metier.dao.VolDao;
import vol.metier.dao.AeroportVilleDao;
import vol.metier.model.Aeroport;
import vol.metier.model.AeroportVille;
import vol.metier.model.Escale;
import vol.metier.model.Vol;

@Transactional
@Repository
public class AeroportDaoJpa implements AeroportDao {

	@PersistenceContext 
	private EntityManager em;

	@Autowired
	private AeroportVilleDao aeroportVilleDao;

	@Autowired
	private EscaleDao escaleDao;
	@Autowired
	private VolDao volDao;
	

	@Override
	@Transactional(readOnly=true)
	public Aeroport find(Long id) {
		return em.find(Aeroport.class, id);

	}

	@Override
	@Transactional(readOnly=true)
	public Aeroport find(String name) {
		Query query = em.createQuery("from Aeroport a where a.nom = :nom");
		query.setParameter("nom", name);
		List<Aeroport> aeroport = query.getResultList();
		return aeroport.size() > 0 ? aeroport.get(0) : null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Aeroport> findAll() {
		Query query = em.createQuery("from Aeroport a");
		return query.getResultList();
	}

	@Override
	public void create(Aeroport aeroport) {
		em.persist(aeroport);
	}

	@Override
	public Aeroport update(Aeroport aeroport) {
		return em.merge(aeroport);

	}

	@Override
	public void delete(Aeroport aeroport) {
		System.out.println("etapa 1 delete en AeroportJpa");
		em.merge(aeroport);
		
		//
		List<Vol> LV = volDao.findAll();
		for (int i = 0; i < LV.size(); i++) {
			
			if (LV.get(i).getDepart().equals(aeroport) || LV.get(i).getArrivee().equals(aeroport))
			{System.out.println("ETAPA EN XXXXXX");
				volDao.delete(volDao.find(LV.get(i).getId()));
			}
		}
		 //
		if(aeroport.getVilles().size()!=0){System.out.println("aeroport.getVilles().size()!=0 en AeroDaoJpa");
			for (AeroportVille villeAeroport : aeroport.getVilles()) {System.out.println("etapa 2 delete en AeroportJpa");
				aeroportVilleDao.delete(villeAeroport);
			}
		}
		
		for (Escale escale : aeroport.getEscales()) {System.out.println("etapa 3 delete en AeroportJpa");
			escaleDao.delete(escale);
		}
		System.out.println("etapa 4 antes remove en AeroportJpa");
		em.remove(em.contains(aeroport) ? aeroport : em.merge(aeroport));
		System.out.println("saliendo bien de AeroportJpa");
	}


}
