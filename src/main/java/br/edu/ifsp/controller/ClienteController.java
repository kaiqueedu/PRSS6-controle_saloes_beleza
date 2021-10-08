package br.edu.ifsp.controller;

import br.edu.ifsp.controller.vo.ClienteResponse;
import br.edu.ifsp.model.Cliente;
import br.edu.ifsp.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteResponse>> getClientes() {
		return ResponseEntity.ok(clienteService.listarClientes());
	}

	@GetMapping("/inativos")
	public ResponseEntity<List<ClienteResponse>> getClientesInativos() {
		return ResponseEntity.ok(clienteService.listarClientesInativos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> getClienteById(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscaClientePorId(id));
	}

	@PostMapping()
	public ResponseEntity adicionarCliente(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clienteService.salvarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	@PutMapping("{id}")
	public ResponseEntity atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		return ResponseEntity.ok(clienteService.atualizarCliente(id, cliente));
	}

	@PatchMapping("/ativar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarCadastroCliente(@PathVariable Long id){
		clienteService.ativarCadastroCliente(id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerClientePorId(@PathVariable Long id) {
		clienteService.excluirCliente(id);
	}

}
