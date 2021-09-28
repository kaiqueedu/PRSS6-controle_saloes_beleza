package br.edu.ifsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.model.Expediente;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

}
