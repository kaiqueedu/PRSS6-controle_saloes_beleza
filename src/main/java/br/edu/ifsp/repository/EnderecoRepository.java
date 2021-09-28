package br.edu.ifsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
