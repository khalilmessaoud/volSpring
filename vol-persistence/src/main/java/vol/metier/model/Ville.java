package vol.metier.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Ville {

	private Long id;
	private String nom;
	private List<AeroportVille> aeroports;
	private int version;

	public Ville() {
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "Nom", length = 50)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(mappedBy = "ville")
	public List<AeroportVille> getAeroports() {
		return aeroports;
	}

	public void setAeroports(List<AeroportVille> aeroports) {
		this.aeroports = aeroports;
	}

	@Version
	@Column(name = "Version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
