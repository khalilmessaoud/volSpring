package vol.metier.dao;

import vol.metier.model.Ville;


public interface VilleDao extends Dao<Ville, Long> {

	Ville find(String ville);
}
