package vol.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vol.metier.dao.LoginDao;
import vol.metier.model.Login;

public class TestJUnit4 {
	
	private static ClassPathXmlApplicationContext context;
	
	@BeforeClass
	// exécuter une fois pour tous les tests
	public static void init() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@AfterClass
	public static void close() {
		context.close();
	}

	@Test
	public void login() {
		LoginDao loginDao = context.getBean(LoginDao.class);
		
		List<Login> logins = loginDao.findAll();
		int sizeStart = logins.size();
		
		Login login = new Login();
		login.setLogin("ajc");
		login.setMotDePasse("Silenus2015");
		login.setAdmin(false);
		
		loginDao.create(login);
		
		Login loginFind = loginDao.find(login.getId());
		
		Assert.assertEquals("ajc", loginFind.getLogin());
		Assert.assertEquals("Silenus2015", loginFind.getMotDePasse());
		Assert.assertEquals(false, loginFind.isAdmin());
		
		loginFind.setLogin("ajcBis");
		loginFind.setMotDePasse("Silenus2017");
		loginFind.setAdmin(true);
		
		loginDao.update(loginFind);
		
		loginFind = loginDao.find(login.getId());
		
		Assert.assertEquals("ajcBis", loginFind.getLogin());
		Assert.assertEquals("Silenus2017", loginFind.getMotDePasse());
		Assert.assertEquals(true, loginFind.isAdmin());
		
		logins = loginDao.findAll();
		int sizeEnd = logins.size();
		
		Assert.assertEquals(1, sizeEnd-sizeStart);
		
		loginDao.delete(loginFind);
		
		loginFind = loginDao.find(login.getId());
		
		Assert.assertNull(loginFind);
		
//		if(loginFind != null) {
//			Assert.fail("loginFind not removed");
//		}
	}
	
	@Test
	public void passager() {
		System.out.println("mon test passager");
	}
	
}
