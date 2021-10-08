package br.edu.ifsp.repository;

import br.edu.ifsp.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByAtivo(Boolean active);

}
