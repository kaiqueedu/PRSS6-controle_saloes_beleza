package br.edu.ifsp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Especialidade implements Serializable {

	private static final long serialVersionUID = -1584544276914218747L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEspecialidade;

	@NotNull
	private String descricaoEspecialidade;

	// VERIFICAR RELACIONAMENTOS(@OneToOne,@OneToMany,@ManyToOne,@ManyToMany) NA MODELAGEM UML 
	
	@OneToMany
	private Funcionario funcionario;

	public Especialidade() {
	}

	public Long getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Long idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public String getDescricaoEspecialidade() {
		return descricaoEspecialidade;
	}

	public void setDescricaoEspecialidade(String descricaoEspecialidade) {
		this.descricaoEspecialidade = descricaoEspecialidade;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
