package br.edu.ifsp.controller;

import java.util.Collection;
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

import br.edu.ifsp.model.Cliente;
import br.edu.ifsp.repository.ClienteRepository;
import br.edu.ifsp.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;


	// CRUD (Create, Read, Update, Delete)
	@PostMapping("/adicionar")
	public ResponseEntity<?> adcionarCliente(@RequestBody Cliente cliente) {
		try {
			cliente = clienteService.salvarCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public List<Cliente> listarTodosClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("buscar/{idCliente}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long idCliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("atualizar/{idCliente}")
	public ResponseEntity<?> atualizarCliente(@PathVariable Long idCliente, @RequestBody Cliente cliente) {
		try {
			Cliente clienteAtual = clienteRepository.findById(idCliente).orElse(null);

			if (clienteAtual != null) {
				BeanUtils.copyProperties(cliente, clienteAtual, "id");

				clienteAtual = clienteService.salvarCliente(clienteAtual);
				return ResponseEntity.ok(clienteAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@DeleteMapping("remover/{idCliente}")
	public ResponseEntity<?> removerClientePorId(@PathVariable Long idCliente) {
		try {
			clienteService.excluirCliente(idCliente);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}
	
	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL
	
	@GetMapping(value="/buscar/{idCliente}/{disponivel}")
	public ResponseEntity<Collection<Cliente>> buscarClienteEDisponivel(@PathVariable Long idCliente, @PathVariable Boolean disponivel) {
		Collection<Cliente> collection = clienteService.buscarAgendaPorEspecilidadeEDisponivel(idCliente, disponivel);
		return ResponseEntity.ok().body(collection);
	}

}
