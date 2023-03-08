package it.epicode.BW2.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the be_service_fatture database table.
 * 
 */
@Entity
@Table(name="be_service_fatture")
@NamedQuery(name="BeServiceFatture.findAll", query="SELECT b FROM BeServiceFatture b")
public class BeServiceFatture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	private Integer anno;

	private Timestamp data;

	@Column(precision=19, scale=2)
	private BigDecimal importo;

	private Integer numero;

	//bi-directional many-to-one association to BeServiceClienti
	@ManyToOne
	@JoinColumn(name="cliente_id")
	@JsonBackReference
	private BeServiceClienti beServiceClienti;

	//bi-directional many-to-one association to BeServiceStatoFattura
	@ManyToOne
	@JoinColumn(name="stato_id")
	@JsonBackReference
	private BeServiceStatoFattura beServiceStatoFattura;

	public BeServiceFatture() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAnno() {
		return this.anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public BigDecimal getImporto() {
		return this.importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BeServiceClienti getBeServiceClienti() {
		return this.beServiceClienti;
	}

	public void setBeServiceClienti(BeServiceClienti beServiceClienti) {
		this.beServiceClienti = beServiceClienti;
	}

	public BeServiceStatoFattura getBeServiceStatoFattura() {
		return this.beServiceStatoFattura;
	}

	public void setBeServiceStatoFattura(BeServiceStatoFattura beServiceStatoFattura) {
		this.beServiceStatoFattura = beServiceStatoFattura;
	}

}