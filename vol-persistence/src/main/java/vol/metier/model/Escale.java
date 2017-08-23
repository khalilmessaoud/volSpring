package vol.metier.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@IdClass(EscaleId.class)
public class Escale {

	private Date heureDepart;
	private Date heureArrivee;
	private Aeroport aeroport;
	private Vol vol;
	private int version;

	public Escale() {
	}

	public Escale(Date heureDepart, Date heureArrivee, Aeroport aeroport, Vol vol) {
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.aeroport = aeroport;
		this.vol = vol;
	}

	@Column(name = "heuredepart")
	@Temporal(TemporalType.TIME)
	public Date getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Date heureDepart) {
		this.heureDepart = heureDepart;
	}

	@Column(name = "heurearrivee")
	@Temporal(TemporalType.TIME)
	public Date getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Date heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "aeroport_id")
	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "vol_id")
	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
