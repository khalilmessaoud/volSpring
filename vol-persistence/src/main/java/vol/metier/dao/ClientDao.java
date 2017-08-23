package vol.metier.dao;

import java.util.List;

import vol.metier.model.Client;

public interface ClientDao extends Dao<Client, Long> {
	Client findByTitre(String titre);
	
	List<Client> findAllClientMoral();
	List<Client> findAllClientPhysique();
}
