package br.edu.ifsp.model;

import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 6670656061290471396L;

	@NotNull
	private Long cep;
	@NotNull
	private String logradouro;
	@NotNull
	private int numero;
	private String complemento;
	
	public Endereco() {}

	public String getLogradouro() {	return logradouro; }

	public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
