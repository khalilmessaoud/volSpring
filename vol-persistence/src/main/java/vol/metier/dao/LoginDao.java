package vol.metier.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import vol.metier.model.Login;

public interface LoginDao extends Dao<Login,Long>{
	
	Login find(String login);

	Login checkLogin(String login, String password);

	 List<Login> findAllLoginClientMoral() ;
	 List<Login> findAllLoginClientPhysique() ;
	

}
