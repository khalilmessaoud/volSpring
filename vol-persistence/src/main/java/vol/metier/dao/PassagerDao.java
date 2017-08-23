package vol.metier.dao;

import vol.metier.model.Passager;

public interface PassagerDao extends Dao<Passager, Long> {

	Passager find(String passager);
}
