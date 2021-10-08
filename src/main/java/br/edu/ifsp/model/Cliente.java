package br.edu.ifsp.model;

import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = -8133683407991692507L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCliente;
	@NotNull
	@CPF
	private String cpf;
	@NotNull
	private String nome;
	@NotNull
	private String telefone;
	@Email
	private String email;
	@Embedded
	private Endereco endereco;
	@Column(nullable = false, columnDefinition = "TINYINT(1) default 1")
	private Boolean ativo;

	public Cliente() {
		ativo = true;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
