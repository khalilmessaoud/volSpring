/*
 * VilleBean.java
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

import vol.metier.dao.VilleDao;
import vol.metier.model.Ville;

@Component
@Scope(value = "request")
public class VilleBean {

	private Long villeId;
	private Ville ville = new Ville();

	@Autowired
	private VilleDao villeDao;

	/* ACTION BEAN */

	// List Villes
	public List<Ville> getVilles() {
		return villeDao.findAll();
	}

	// Add Ville
	public String add() {
		return "villeEdit";
	}

	public String save() {
		if (ville.getId() != null) {
			villeDao.update(ville);
		} else {
			villeDao.create(ville);
		}

		return "villes";
	}

	// Delete Ville
	public String delete() {
		// TO DO
		return null;

	}

	// Edit Ville
	public String edit(Long id) {
		ville = villeDao.find(id);
		return "villeEdit";

	}

	/* GETTERS AND SETTERS */
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
