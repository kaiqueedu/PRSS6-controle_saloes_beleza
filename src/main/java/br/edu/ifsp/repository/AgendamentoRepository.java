package br.edu.ifsp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	Collection<Agendamento> findByClienteAndPeriodo(Long idCliente, String inicio, String termino);

	Collection<?> findQuantidadesReservasOfClientesByPeriodo(String inicio, String termino);

	Collection<Agendamento> buscarAgendaPorEspecilidadeEDisponivel(Long idEspecialidade, Boolean disponivel);

	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL
	
}
