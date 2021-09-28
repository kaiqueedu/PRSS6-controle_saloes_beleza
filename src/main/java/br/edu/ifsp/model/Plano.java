package br.edu.ifsp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Plano implements Serializable {

	private static final long serialVersionUID = -8404289563573230089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String descricao;

	// VERIFICAR RELACIONAMENTOS(@OneToOne,@OneToMany,@ManyToOne,@ManyToMany) NA MODELAGEM UML 
	
	@OneToMany
	private Servico servico;

	@OneToMany
	private Fidelidade fidelidade;

	public Plano() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Fidelidade getFidelidade() {
		return fidelidade;
	}

	public void setFidelidade(Fidelidade fidelidade) {
		this.fidelidade = fidelidade;
	}

}
