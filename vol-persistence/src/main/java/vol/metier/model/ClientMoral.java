package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("ClientMoral")
public class ClientMoral extends Client {

	private TitreMoral titre;
	private String siret;
	
	public ClientMoral() {
	}
	
	@Column(name="titre")
	//@Size(min = 1, max = 100, message = "{clientEdit.titre.obligatoire}")
	@Enumerated(EnumType.STRING)
	public TitreMoral getTitre() {
		return titre;
	}

	public void setTitre(TitreMoral titre) {
		this.titre = titre;
	}

	@Column(name="siret",length=50)
	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	

}
