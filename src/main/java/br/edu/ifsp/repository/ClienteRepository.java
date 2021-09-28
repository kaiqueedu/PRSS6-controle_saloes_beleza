package br.edu.ifsp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Collection<Cliente> buscarAgendaPorEspecilidadeEDisponivel(Long idCliente, Boolean disponivel);

	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

}
