package br.edu.ifsp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 9113197393383771794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionario;

	@NotNull
	private String nome;

	@NotNull
	private String cpf;

	// VERIFICAR RELACIONAMENTOS(@OneToOne,@OneToMany,@ManyToOne,@ManyToMany) NA MODELAGEM UML 
	
	@OneToOne
	private Especialidade especialidade;

	@OneToMany
	private Expediente expediente;

	private Endereco endereco;

	public Funcionario() {
		super();
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
