package vol.metier.dao;

import vol.metier.model.Aeroport;

public interface AeroportDao extends Dao<Aeroport, Long> {

	Aeroport find(String name);
}
