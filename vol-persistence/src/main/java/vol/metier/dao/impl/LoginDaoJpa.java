package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.LoginDao;
import vol.metier.model.Login;

@Transactional
@Repository
public class LoginDaoJpa implements LoginDao {

	@PersistenceContext 
	private EntityManager em;

	@Override
	public Login find(Long id) {
		return em.find(Login.class, id);

	}

	@Override
	@Transactional(readOnly = true)
	public Login find(String login) {
		Query query = em.createQuery("from Login l where l.login = :login");
		query.setParameter("login", login);
		List<Login> log = query.getResultList();
		return log.size() > 0 ? log.get(0) : null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Login> findAll() {
		Query query = em.createQuery("from Login l");
		return query.getResultList();
	}

	@Override
	public void create(Login login) {
		em.persist(login);
	}

	@Override
	public Login update(Login login) {
		return em.merge(login);

	}

	@Override
	public void delete(Login login) {
		em.remove(em.merge(login));

	}

	@Override
	@Transactional(readOnly = true)
	public Login checkLogin(String login, String password) {
		TypedQuery<Login> query = em.createQuery(
				"select l from Login as l where l.login = :login AND l.motDePasse=:password ", Login.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		// getSingleResult() throws an exception if no entity is found
		// Login loginEntity = query.getSingleResult();
		List<Login> list = query.getResultList();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
