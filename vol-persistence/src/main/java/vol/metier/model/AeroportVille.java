package vol.metier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "aeroport_ville")
public class AeroportVille {

	private Long id;
	private Ville ville;
	private Aeroport aeroport;
	private int version;

	public AeroportVille() {
	}

	public AeroportVille(Ville ville, Aeroport aeroport) {
		super();
		this.ville = ville;
		this.aeroport = aeroport;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@ManyToOne
	@JoinColumn(name = "ville_id")
	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	@ManyToOne
	@JoinColumn(name = "aeroport_id")
	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

}
