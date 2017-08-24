package vol.metier.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.LoginDao;
import vol.metier.model.Login;

@Transactional
// @Repository("loginDao")
public class LoginDaoHibernate implements LoginDao {

	// Autowired : recherche d'abord le nombre d'instance de session factory
	// puis prend celui dont le nom est le meme que la propriété
	// private mais Spring utilise couches de bas niveau où la visibilité n'a
	// pas d'importance
	// permet en plus d'empecher que quelqu'un puisse utiliser le sessionFactory
	// ni le valoriser
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional(readOnly = true)
	@Override
	public Login find(Long id) {

		return (Login) getSession().get(Login.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public Login find(String login) {
		Query query = getSession().createQuery("from Login l where l.login = :login");
		query.setParameter("login", login);
		return (Login) query.uniqueResult();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Login> findAll() {
		Query query = getSession().createQuery("from Login l");
		return query.list();
	}

	@Override
	public void create(Login login) {
		getSession().persist(login);
	}

	// un objet récupéré de la base est déjà managé donc les modif se font
	// automatiquement pas besoin d'update
	// on utilise update pour merger objet
	@Override
	public Login update(Login login) {
		return (Login) getSession().merge(login);

	}

	@Override
	public void delete(Login login) {
		getSession().delete(login);

	}

	@Override
	@Transactional(readOnly = true)
	public Login checkLogin(String login, String password) {
		Query query = getSession()
				.createQuery("select l from Login as l where l.login = :login AND l.motDePasse=:password ");
		query.setParameter("login", login);
		query.setParameter("password", password);
		// getSingleResult() throws an exception if no entity is found
		// Login loginEntity = query.getSingleResult();
		List<Login> list = query.list();
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
