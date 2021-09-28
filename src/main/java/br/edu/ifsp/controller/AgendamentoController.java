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

import br.edu.ifsp.model.Agendamento;
import br.edu.ifsp.repository.AgendamentoRepository;
import br.edu.ifsp.service.AgendamentoService;

@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private AgendamentoService agendamentoService;

	
	// CRUD (Create, Read, Update, Delete)
	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarAgendamento(@RequestBody Agendamento agendamento) {
		try {
			agendamento = agendamentoService.salvarAgendamento(agendamento);
			return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Agendamento> listarTodosAgendamentos() {
		return agendamentoRepository.findAll();
	}
	
	@GetMapping("/buscar/{idAgendamento}")
	public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable Long idAgendamento) {
		Optional<Agendamento> agendamento = agendamentoRepository.findById(idAgendamento);

		if (agendamento.isPresent()) {
			return ResponseEntity.ok(agendamento.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/atualizar/{idAgendamento}")
	public ResponseEntity<?> atualizarAgendamento(@PathVariable Long idAgendamento,@RequestBody Agendamento agendamento) {
		try {
			Agendamento agendamentoAtual = agendamentoRepository.findById(idAgendamento).orElse(null);

			if (agendamentoAtual != null) {
				BeanUtils.copyProperties(agendamento, agendamentoAtual, "id");

				agendamentoAtual = agendamentoService.salvarAgendamento(agendamentoAtual);
				return ResponseEntity.ok(agendamentoAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/remover/{idAgendamento}")
	public ResponseEntity<?> removerFuncionarioPorId(@PathVariable Long idAgendamento) {
		try {
			agendamentoService.excluirAgendamento(idAgendamento);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}
	
	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

	@GetMapping(value = "/buscarAgendaPorEspecilidadeEDisponivel/{idEspecialidade}/{disponivel}")
	public ResponseEntity<Collection<Agendamento>> buscarAgendaPorEspecilidadeEDisponivel(
			@PathVariable Long idEspecialidade, @PathVariable Boolean disponivel) {
		Collection<Agendamento> collection = agendamentoService.buscarAgendaPorEspecilidadeEDisponivel(idEspecialidade,
				disponivel);
		return ResponseEntity.ok().body(collection);
	}

}
