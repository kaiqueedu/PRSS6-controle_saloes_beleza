package br.edu.ifsp.controller.vo;

import br.edu.ifsp.model.Endereco;
import java.io.Serializable;

public class ClienteResponse implements Serializable {

	private Long idCliente;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private Endereco endereco;

	public ClienteResponse() {
	}

	public ClienteResponse(Long idCliente, String cpf, String nome, String email, String telefone, Endereco endereco) {
		this.idCliente = idCliente;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
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

}
