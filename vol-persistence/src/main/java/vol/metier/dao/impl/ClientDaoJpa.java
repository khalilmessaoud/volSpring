package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.ClientDao;
import vol.metier.dao.ReservationDao;
import vol.metier.model.Client;
import vol.metier.model.Reservation;

@Transactional
@Repository
public class ClientDaoJpa implements ClientDao {

	@PersistenceContext 
	private EntityManager em;

	@Autowired
	private ReservationDao reservationDao;

	@Override
	@Transactional(readOnly=true)
	public Client find(Long id) {
		return em.find(Client.class, id);
	}
	@Transactional(readOnly=true)
	public Client findByTitre(String titre) {
		return em.find(Client.class, titre);
	}
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		Query query = em.createQuery("from Client c left outer join fetch c.login");
		return query.getResultList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAllClientMoral() {
		Query query = em.createQuery("from ClientMoral c left outer join fetch c.login");
		return query.getResultList();
	}
	@Override
	@Transactional(readOnly=true)
	public List<Client> findAllClientPhysique() {
		Query query = em.createQuery("from ClientPhysique c left outer join fetch c.login");
		return query.getResultList();
	}
	@Override
	public void create(Client client) {
		em.persist(client);
	}

	@Override
	public Client update(Client client) {
		return em.merge(client);

	}

	@Override
	public void delete(Client client) {
		em.refresh(client);
		for (Reservation resa : client.getReservations()) {
			reservationDao.delete(resa);
		}
		em.remove(client.getLogin());
		em.remove(client);

	}

}
