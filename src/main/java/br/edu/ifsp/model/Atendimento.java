package br.edu.ifsp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Atendimento implements Serializable {

	private static final long serialVersionUID = -6151172803472107366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAtendimento;

	@NotNull
	private String horarioAtendimeno;

	@NotNull
	private boolean atendimentoExecutado;

	//TODO: VERIFICAR RELACIONAMENTOS(@OneToOne,@OneToMany,@ManyToOne,@ManyToMany) NA MODELAGEM UML 
	
	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getHorarioAtendimeno() {
		return horarioAtendimeno;
	}

	public void setHorarioAtendimeno(String horarioAtendimeno) {
		this.horarioAtendimeno = horarioAtendimeno;
	}

	public boolean isAtendimentoExecutado() {
		return atendimentoExecutado;
	}

	public void setAtendimentoExecutado(boolean atendimentoExecutado) {
		this.atendimentoExecutado = atendimentoExecutado;
	}

	public Atendimento() {
		super();
	}

}
