package vol.metier.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class CompagnieAerienne {

	private Long id;
	private String nom;
	private List<CompagnieAerienneVol> compagniesAerienneVol;
	private int version;

	public CompagnieAerienne() {
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "Nom", length = 100)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(mappedBy = "id.compagnieAerienne")
	public List<CompagnieAerienneVol> getCompagniesAerienneVol() {
		return compagniesAerienneVol;
	}

	public void setCompagniesAerienneVol(List<CompagnieAerienneVol> compagniesAerienneVol) {
		this.compagniesAerienneVol = compagniesAerienneVol;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
