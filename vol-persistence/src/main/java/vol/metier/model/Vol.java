package vol.metier.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.IndexColumn;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Vol {

	private Long id;
	private Date dateDepart;
	private Date dateArrivee;
	private Date heureDepart;
	private Date heureArrivee;
	private List<Escale> escales;
	private List<Reservation> reservations;
	private Aeroport depart;
	private Aeroport arrivee;
	private List<CompagnieAerienneVol> compagniesAerienneVol;
	private int version;

	public Vol() {
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	
	@Column(name = "datedepart")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	@Column(name = "datearrivee")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	@Column(name = "heuredepart")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HHHH:mm")
	public Date getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Date heureDepart) {
		this.heureDepart = heureDepart;
	}

	@Column(name = "heurearrivee")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern="HHHH:mm")
	public Date getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Date heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	@OneToMany(mappedBy = "vol", fetch = FetchType.EAGER)
	public List<Escale> getEscales() {
		return escales;
	}

	public void setEscales(List<Escale> escales) {
		this.escales = escales;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depart_id")
	public Aeroport getDepart() {
		return depart;
	}

	public void setDepart(Aeroport depart) {
		this.depart = depart;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "arrivee_id")
	public Aeroport getArrivee() {
		return arrivee;
	}

	public void setArrivee(Aeroport arrivee) {
		this.arrivee = arrivee;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) //mappedBy was removed
    @IndexColumn(name="INDEX_COL")
	//@OneToMany(mappedBy = "vol", fetch = FetchType.EAGER)
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) //mappedBy was removed
    @IndexColumn(name="INDEX_COL")
	//@OneToMany(mappedBy = "id.vol", fetch = FetchType.LAZY)
	public List<CompagnieAerienneVol> getCompagniesAerienneVol() {
		return compagniesAerienneVol;
	}

	public void setCompagniesAerienneVol(List<CompagnieAerienneVol> compagniesAerienneVol) {
		this.compagniesAerienneVol = compagniesAerienneVol;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Vol other = (Vol) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
