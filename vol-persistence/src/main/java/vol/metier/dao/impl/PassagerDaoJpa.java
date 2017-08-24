package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.PassagerDao;
import vol.metier.dao.ReservationDao;
import vol.metier.model.Passager;
import vol.metier.model.Reservation;

@Transactional
@Repository
public class PassagerDaoJpa implements PassagerDao{

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ReservationDao reservationDao;

	
	@Override
	@Transactional(readOnly = true)
	public Passager find(Long id) {
		 return em.find(Passager.class, id);
	
	}
	
	@Override
	@Transactional(readOnly = true)
	public Passager find(String name) {
		Query query = em.createQuery("from Passager p where p.nom = :nom");	
		query.setParameter("nom", name);
	
		try {
			return (Passager) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Passager> findAll() {
		Query query = em.createQuery("from Passager p");		
		return query.getResultList();
	}

	@Override
	public void create(Passager passager) {
		em.persist(passager);
		
	}

	@Override
	public Passager update(Passager passager) {
		return em.merge(passager);
	}

	@Override
	public void delete(Passager passager) {
		em.refresh(passager);
		for(Reservation resa : passager.getReservations()){
			reservationDao.delete(resa);
		}
		em.remove(passager);
		
	}


}
