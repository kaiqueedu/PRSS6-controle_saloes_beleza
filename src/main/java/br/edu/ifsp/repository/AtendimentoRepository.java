package br.edu.ifsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL
	
}
