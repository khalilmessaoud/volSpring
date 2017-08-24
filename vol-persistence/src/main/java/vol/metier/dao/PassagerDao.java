package vol.metier.dao;

import java.util.List;

import vol.metier.model.Passager;
import vol.metier.model.Reservation;

public interface PassagerDao extends Dao<Passager, Long> {

	Passager find(String passager);
}
