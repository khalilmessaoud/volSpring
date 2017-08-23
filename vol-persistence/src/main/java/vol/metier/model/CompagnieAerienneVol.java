package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="compagnieaerienne_vol")
public class CompagnieAerienneVol {

	private String numero;
	private CompagnieAerienneVolId id;
	private int version;
	
	public CompagnieAerienneVol() {
	}
	
	public CompagnieAerienneVol(CompagnieAerienne compagnieAerienne, Vol vol) {
		this.id = new CompagnieAerienneVolId(compagnieAerienne,vol);
	}
	

	@EmbeddedId
	public CompagnieAerienneVolId getId() {
		return id;
	}

	public void setId(CompagnieAerienneVolId id) {
		this.id = id;
	}

	

	@Column(name="Numero", length=50)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
