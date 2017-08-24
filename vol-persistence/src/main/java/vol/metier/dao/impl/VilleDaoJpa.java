package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.AeroportDao;
import vol.metier.dao.AeroportVilleDao;
import vol.metier.dao.VilleDao;
import vol.metier.dao.VolDao;
import vol.metier.model.Aeroport;
import vol.metier.model.AeroportVille;
import vol.metier.model.Ville;



@Transactional
@Repository
public class VilleDaoJpa implements VilleDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private AeroportVilleDao aeroportVilleDao;
	@Autowired
	private AeroportDao aeroportDao;
	
	@Override
	@Transactional(readOnly = true)
	public Ville find(Long id) {
		return em.find(Ville.class, id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Ville find(String name) {
		Query query = em.createQuery("from Ville v where v.nom = :nom");	
		query.setParameter("nom", name);
		List<Ville> ville = query.getResultList();
		return ville.size()>0?ville.get(0):null ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ville> findAll() {
		Query query = em.createQuery("from Ville v");		
		return query.getResultList();
	}

	@Override
	public void create(Ville ville) {
		em.persist(ville);
		
	}

	@Override
	public Ville update(Ville ville) {
		return em.merge(ville);
	}

	@Override
	public void delete(Ville ville) {
		em.merge(ville);

		List<AeroportVille> LAV=aeroportVilleDao.findAll();
		List<Aeroport> LA=aeroportDao.findAll();

		/*for (int i = 0; i < LAV.size(); i++) {
			if (LAV.get(i).getVille().equals(ville)) {
				
				aeroportVilleDao.delete(aeroportVilleDao.find(LAV.get(i).getId()));
				
			}
		}*/
		for (int i = 0; i < LA.size(); i++) {
			for(int j=0;j<LA.get(i).getVilles().size();j++){
				if(LA.get(i).getVilles().get(j).getVille().equals(ville)){
					aeroportDao.delete(aeroportDao.find(LA.get(i).getId()));
				}
			}
		}
		
		em.remove(em.contains(ville) ? ville : em.merge(ville));
		
		/*		for(AeroportVille villeAeroport : ville.getAeroports()){
		System.out.println("rah dkhal hnaa a sidiiii");
		aeroportVilleDao.delete(villeAeroport);
	}*/
		//em.remove(ville);
		
	}

}
