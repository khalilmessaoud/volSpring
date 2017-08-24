package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.ReservationDao;
import vol.metier.model.Passager;
import vol.metier.model.Reservation;

@Transactional
@Repository
public class ReservationDaoJpa implements ReservationDao {

	@PersistenceContext 
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Reservation find(Long id) {
		return em.find(Reservation.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public Reservation find(int numero) {
		Query query = em.createQuery("from Reservation r where r.numero = :numero");
		query.setParameter("numero", numero);
		List<Reservation> res = query.getResultList();
		return res.size() > 0 ? res.get(0) : null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reservation> findAll() {
		Query query = em.createQuery("from Reservation r");
		return query.getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Reservation> findAll(Long idVol) {
		Query query = em.createQuery("from Reservation r where r.vol.id=:idVol");
		query.setParameter("idVol", idVol);
		return query.getResultList();
	}
	
	public List<Passager> findAllPassenger(Long idVol) {
		Query query = em.createQuery("passager_id from Reservation r where r.vol.id=:idVol");
		query.setParameter("idPassenger", idVol);
		return query.getResultList();
	}


	@Override
	public void create(Reservation reservation) {
		em.persist(reservation);
	}

	@Override
	public Reservation update(Reservation reservation) {
		return em.merge(reservation);
	}

	@Override
	public void delete(Reservation reservation) {
		em.merge(reservation);
		//em.refresh(reservation);
		reservation.setClient(null);
		reservation.setPassager(null);
		em.remove(em.contains(reservation) ? reservation : em.merge(reservation));

	}

}
