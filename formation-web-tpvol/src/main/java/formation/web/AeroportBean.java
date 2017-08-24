/*
 * AeroportBean.java
 * 
 * Created by : Kean de Souza - 24/08/2017
 * Modified by: Kean de Souza - 24/08/2017
 * 
 */

package formation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import vol.metier.dao.AeroportDao;
import vol.metier.dao.AeroportVilleDao;
import vol.metier.dao.VilleDao;
import vol.metier.model.Aeroport;
import vol.metier.model.AeroportVille;
import vol.metier.model.Ville;

@Component
@Scope(value="request")
public class AeroportBean {
	
	private Long aeroportId;
	private Long villeId;
	
	private Ville ville = new Ville();
	private Aeroport aeroport = new Aeroport();
	private AeroportVille aeroportVille = new AeroportVille();
		
	@Autowired
	private AeroportDao aeroportDao;
	
	@Autowired
	private VilleDao villeDao;
	
	@Autowired
	private AeroportVilleDao aeroportVilleDao;
	
	// ACTION
	public List<Aeroport> getAeroports() {
		return aeroportDao.findAll();
	}
	
	public List<Ville> getVilles() {
		return villeDao.findAll();
	}
	
	public String add() {
		return "aeroportEdit";
	}
	
	public String edit(Long id) {
		aeroport = aeroportDao.find(id);
		return "aeroportEdit";
	}
	
	public String delete() {
		
		aeroport = aeroportDao.find(aeroportId);
		
		aeroportDao.delete(aeroport);
				
		return "aeroports";	
	}
	
	public String save() {
		if ( aeroport.getId() != null) {
			aeroportDao.update(aeroport);
			aeroportVilleDao.update(aeroportVille);

		}
		else {
			aeroportDao.create(aeroport);
			}
		
		return "aeroports";
	}
	
	/* GETTERS AND SETTERS */
	
	public Long getAeroportId() {
		return aeroportId;
	}

	public void setAeroportId(Long aeroportId) {
		this.aeroportId = aeroportId;
	}

	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

	public Long getVilleId() {
		return villeId;
	}

	public void setVilleId(Long villeId) {
		this.villeId = villeId;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
		
}
