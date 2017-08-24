package formation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import vol.metier.dao.CompagnieAerienneDao;
import vol.metier.dao.VolDao;
import vol.metier.model.Aeroport;
import vol.metier.model.CompagnieAerienne;
import vol.metier.model.Vol;

@Component
@Scope("request")
public class VolBean {

	
	private Long volId;
	private Vol vol = new Vol();
	private Aeroport depart;
	private Aeroport arrivee;
	
	@Autowired
	private VolDao volDao;
	
	public List<Vol> getVols() {
		
		return volDao.findAll();
	}

	public Long getCieAerienneId() {
		return this.volId;
	}

	public void setVolId(Long volId) {
		this.volId = volId;
	}

	public Vol getVol() {
		return this.vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}
	
	public String add() {
		
		return "volEdit";
	}

	public String edit(Long id) {
		vol = volDao.find(id);

		return "volEdit";
	}
	
	public String save() {
		if(vol.getId() != null) {
			volDao.update(vol);
		} else {
			volDao.create(vol);
		}
		
		return "vols";
	}
	
	public String delete() {
		vol = volDao.find(volId);
		
		volDao.delete(vol);
		
		return "vols";
	}
	public Aeroport getDepart() {
		return depart;
	}

	public void setDepart(Aeroport depart) {
		this.depart = depart;
	}

	public Aeroport getArrivee() {
		return arrivee;
	}

	public void setArrivee(Aeroport arrivee) {
		this.arrivee = arrivee;
	}

}
