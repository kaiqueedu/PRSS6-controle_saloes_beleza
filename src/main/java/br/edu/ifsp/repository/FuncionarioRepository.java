package br.edu.ifsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> buscarTodosFuncionariosPeloNome(String nome);

	List<Funcionario> buscarFuncionariosPorNomeExato(String nome);

	List<Funcionario> buscarTodosFuncionariosQualquerNomeOuLetra(String nome);

	Funcionario buscarLoginAndSenhaFuncionarioAdministrador(String login, String senha);

}
