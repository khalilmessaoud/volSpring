package formation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import vol.metier.dao.CompagnieAerienneDao;
import vol.metier.model.CompagnieAerienne;

@Component
@Scope("request")
public class CompagnieBean {

	
	private Long cieAerienneId;
	private CompagnieAerienne cieAerienne = new CompagnieAerienne();
	
	@Autowired
	private CompagnieAerienneDao cieAerienneDao;
	
	public List<CompagnieAerienne> getCieAeriennes() {
		System.out.println("ICI JE SUIS");
		return cieAerienneDao.findAll();
	}

	public Long getCieAerienneId() {
		return this.cieAerienneId;
	}

	public void setCieAerienneId(Long cieAerienneId) {
		this.cieAerienneId = cieAerienneId;
	}

	public CompagnieAerienne getCompagnieAerienne() {
		return this.cieAerienne;
	}

	public void setCompagnieAerienne(CompagnieAerienne cieAerienne) {
		this.cieAerienne = cieAerienne;
	}
	
	public String add() {
		
		return "cieAerienneEdit";
	}

	public String edit(Long id) {
		cieAerienne = cieAerienneDao.find(id);

		return "cieAerienneEdit";
	}
	
	public String save() {
		if(cieAerienne.getId() != null) {
			cieAerienneDao.update(cieAerienne);
		} else {
			cieAerienneDao.create(cieAerienne);
		}
		
		return "cieAeriennes";
	}
	
	public String delete() {
		cieAerienne = cieAerienneDao.find(cieAerienneId);
		
		cieAerienneDao.delete(cieAerienne);
		
		return "cieAeriennes";
	}
	

}
