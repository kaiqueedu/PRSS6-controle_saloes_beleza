
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

import br.edu.ifsp.model.Endereco;
import br.edu.ifsp.repository.EnderecoRepository;
import br.edu.ifsp.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public List<Endereco> listarEnderecos() {
		return enderecoRepository.findAll();
	}

	// CRUD (Create, Read, Update, Delete)
	@PostMapping("/atualizar")
	public ResponseEntity<?> adicionarEndereco(@RequestBody Endereco endereco) {
		try {
			endereco = enderecoService.salvarEndereco(endereco);
			return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Endereco> listarTodosEnderecos() {
		return enderecoRepository.findAll();
	}

	@GetMapping("/buscar/{idEndereco}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long idEndereco) {
		Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);

		if (endereco.isPresent()) {
			return ResponseEntity.ok(endereco.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/atualizar/{idEndereco}")
	public ResponseEntity<?> atualizarEndereco(@PathVariable Long idEndereco, @RequestBody Endereco endereco) {
		try {
			Endereco enderecoAtual = enderecoRepository.findById(idEndereco).orElse(null);

			if (enderecoAtual != null) {
				BeanUtils.copyProperties(endereco, enderecoAtual, "id");

				enderecoAtual = enderecoService.salvarEndereco(enderecoAtual);
				return ResponseEntity.ok(enderecoAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("remover/{cep}")
	public ResponseEntity<?> removerClientePorId(@PathVariable Long cep) {
		try {
			enderecoService.excluirEndereco(cep);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}
	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

}
