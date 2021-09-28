package br.edu.ifsp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.model.Funcionario;
import br.edu.ifsp.repository.FuncionarioRepository;
import br.edu.ifsp.service.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioService funcionarioService;

	// CRUD (Create, Read, Update, Delete)

	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarFuncionario(@RequestBody Funcionario funcionario) {
		try {
			funcionario = funcionarioService.salvarFuncionario(funcionario);
			return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Funcionario> listarTodosFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@GetMapping("buscar/{idFuncionario}")
	public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long idFuncionario) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

		if (funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("atualizar/{idFuncionario}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long idFuncionario,
			@RequestBody Funcionario funcionario) {
		Optional<Funcionario> funcionarioAtual = funcionarioRepository.findById(idFuncionario);

		if (funcionarioAtual.isPresent()) {
			BeanUtils.copyProperties(funcionario, funcionarioAtual.get(), "id");

			Funcionario FuncionarioSalvo = funcionarioService.salvarFuncionario(funcionarioAtual.get());
			return ResponseEntity.ok(FuncionarioSalvo);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("remover/{idFuncionario}")
	public ResponseEntity<?> removerFuncionarioPorId(@PathVariable Long idFuncionario) {
		try {
			funcionarioService.excluirFuncionario(idFuncionario);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}

	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

	@GetMapping("/buscar/{nome}")
	public List<Funcionario> buscarFuncionariosPorNomeExato(@PathVariable String nome) {
		return funcionarioRepository.buscarFuncionariosPorNomeExato(nome);
	}

	@GetMapping("/buscar/{nomeLetra}")
	public List<Funcionario> buscarFuncionarioPorQualquerNomeOuLetra(@PathVariable String nomeLetra) {
		return funcionarioRepository.buscarTodosFuncionariosQualquerNomeOuLetra(nomeLetra);
	}

	@GetMapping("/{login}/{senha}")
	public ResponseEntity<Funcionario> findByLoginAndSenha(@PathVariable String login, @PathVariable String senha) {
		Funcionario obj = funcionarioService.buscarLoginAndSenhaFuncionarioAdministrador(login, senha);
		return ResponseEntity.ok().body(obj);
	}

}
