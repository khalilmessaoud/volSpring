package vol.metier.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	private String rue;
	private String codePostal;
	private String ville;
	private String pays;

	public Adresse() {
	}

	@Column(name = "rue", length = 100)
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	@Column(name = "codepostal", length = 100)
	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	@Column(name = "ville", length = 100)
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Column(name = "pays", length = 100)
	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

}
