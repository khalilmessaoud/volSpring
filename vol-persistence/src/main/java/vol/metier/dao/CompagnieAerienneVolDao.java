package vol.metier.dao;

import vol.metier.model.CompagnieAerienneVol;
import vol.metier.model.CompagnieAerienneVolId;

public interface CompagnieAerienneVolDao extends
		Dao<CompagnieAerienneVol, CompagnieAerienneVolId> {

	CompagnieAerienneVol find(String numero);
}
