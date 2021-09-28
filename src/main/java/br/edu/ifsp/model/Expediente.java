package br.edu.ifsp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Expediente implements Serializable {

	private static final long serialVersionUID = 768805657582275129L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String periodoExpediente;

	// VERIFICAR RELACIONAMENTOS(@OneToOne,@OneToMany,@ManyToOne,@ManyToMany) NA MODELAGEM UML 
	
	@OneToMany
	private Funcionario funcionario;

	public Expediente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPeriodoExpediente() {
		return periodoExpediente;
	}

	public void setPeriodoExpediente(String periodoExpediente) {
		this.periodoExpediente = periodoExpediente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
