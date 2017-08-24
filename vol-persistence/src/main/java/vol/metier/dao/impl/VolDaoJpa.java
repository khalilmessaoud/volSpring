package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.CompagnieAerienneVolDao;
import vol.metier.dao.EscaleDao;
import vol.metier.dao.ReservationDao;
import vol.metier.dao.VolDao;
import vol.metier.model.CompagnieAerienneVol;
import vol.metier.model.Escale;
import vol.metier.model.Reservation;
import vol.metier.model.Vol;

@Transactional
@Repository
public class VolDaoJpa implements VolDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private CompagnieAerienneVolDao compagnieAerienneVolDao;
	@Autowired
	private EscaleDao escaleDao;

	@Override
	@Transactional(readOnly = true)
	public Vol find(Long id) {
		return em.find(Vol.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vol> findAll() {
		Query query = em.createQuery("from Vol v");
		return query.getResultList();
	}

	@Override
	public void create(Vol vol) {
		em.persist(vol);
	}

	@Override
	public Vol update(Vol vol) {
		return em.merge(vol);

	}

	@Override
	public void delete(Vol vol) {
		//em.refresh(vol);
		em.merge(vol);
		System.out.println("etapa1 delete en VolDaoJpa");
		/*for (Reservation resa : vol.getReservations()) {System.out.println("etapa2 delete en VolDaoJpa");
			reservationDao.delete(resa);
		}*/
		for (Escale escale : vol.getEscales()) {System.out.println("etapa3 delete en VolDaoJpa");
			escaleDao.delete(escale);
		}
		
		List<CompagnieAerienneVol> LCAX=compagnieAerienneVolDao.findAll();
		for(int i=0;i<LCAX.size();i++){
			if(LCAX.get(i).getId().getVol().equals(vol)){System.out.println("etapa 4 delete en VolDaoJpa");
				
				compagnieAerienneVolDao.delete(compagnieAerienneVolDao.find(LCAX.get(i).getId()));
			}
		}
		List<Reservation> LR=reservationDao.findAll();
		for(int i=0;i<LR.size();i++){System.out.println("Alternativa etapa2 bucle for i delete en VolDaoJpa");
			if(LR.get(i).getVol().equals(vol)){
				reservationDao.delete(reservationDao.find(LR.get(i).getId()));
			}
		}
		
		for (CompagnieAerienneVol compagnieAerienneVol : vol.getCompagniesAerienneVol()) {System.out.println("etapa5delete en VolDaoJpa");
			compagnieAerienneVolDao.delete(compagnieAerienneVol);
		}
		System.out.println("etapa5  final avant remove delete en VolDaoJpa");
		em.remove(em.contains(vol) ? vol : em.merge(vol));
		System.out.println("etapa6  saliendo bien de delete en VolDaoJpa");
		//em.remove(vol);

	}

}
