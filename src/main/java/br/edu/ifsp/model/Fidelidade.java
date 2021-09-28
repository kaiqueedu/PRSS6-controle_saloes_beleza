package br.edu.ifsp.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

@Entity
public class Fidelidade implements Serializable {

	private static final long serialVersionUID = 6522504923311273345L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String tipoFidelidade;

	@NotNull
	private BigDecimal saldoFidelidade;

	// VERIFICAR RELACIONAMENTOS(@OneToOne,@OneToMany,@ManyToOne,@ManyToMany) NA MODELAGEM UML 
	
	@OneToMany
	private Cliente cliente;

	@OneToOne
	private Plano plano;

	public Fidelidade() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoFidelidade() {
		return tipoFidelidade;
	}

	public void setTipoFidelidade(String tipoFidelidade) {
		this.tipoFidelidade = tipoFidelidade;
	}

	public BigDecimal getSaldoFidelidade() {
		return saldoFidelidade;
	}

	public void setSaldoFidelidade(BigDecimal saldoFidelidade) {
		this.saldoFidelidade = saldoFidelidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

}
