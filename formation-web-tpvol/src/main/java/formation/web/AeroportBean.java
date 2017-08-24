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
import vol.metier.model.Aeroport;

@Component
@Scope(value="request")
public class AeroportBean {
	
	private Long aeroportId;
	private Aeroport aeroport = new Aeroport();
	
	@Autowired
	private AeroportDao aeroportDao;
	
	// ACTION
	public List<Aeroport> getAeroports() {
		return aeroportDao.findAll();
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
		}
		else aeroportDao.create(aeroport);
		
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
	
		
}
