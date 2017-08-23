package vol.metier.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EscaleId implements Serializable {

	// l'analyseur syntaxique d'éclipse voit parfois cette classe d'identifiant en erreur
	// si vous souhaitez éviter ce bug, changez par la type de l'identifiant de classe cible (exemple : Aeroport -> Long)
	private Aeroport aeroport;
	private Vol vol;

	public EscaleId() {
	}

	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aeroport == null) ? 0 : aeroport.hashCode());
		result = prime * result + ((vol == null) ? 0 : vol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EscaleId other = (EscaleId) obj;
		if (aeroport == null) {
			if (other.aeroport != null)
				return false;
		} else if (!aeroport.equals(other.aeroport))
			return false;
		if (vol == null) {
			if (other.vol != null)
				return false;
		} else if (!vol.equals(other.vol))
			return false;
		return true;
	}

}
