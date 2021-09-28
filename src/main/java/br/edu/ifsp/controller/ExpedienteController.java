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

import br.edu.ifsp.model.Expediente;
import br.edu.ifsp.repository.ExpedienteRepository;
import br.edu.ifsp.service.ExpedienteService;

@RestController
@RequestMapping(value = "/expedientes")
public class ExpedienteController {

	@Autowired
	private ExpedienteRepository epedienteRepository;

	@Autowired
	private ExpedienteService expedienteService;

	@GetMapping
	public List<Expediente> listarExpedientes() {
		return epedienteRepository.findAll();
	}

	// CRUD (Create, Read, Update, Delete)
	@PostMapping("/atualizar")
	public ResponseEntity<?> adicionarServico(@RequestBody Expediente atendimento) {
		try {
			atendimento = expedienteService.salvarExpediente(atendimento);
			return ResponseEntity.status(HttpStatus.CREATED).body(atendimento);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Expediente> listarTodasEspedientes() {
		return epedienteRepository.findAll();
	}

	@GetMapping("/buscar/{idExpediente}")
	public ResponseEntity<Expediente> buscarExpedientePorId(@PathVariable Long idExpediente) {
		Optional<Expediente> atendimento = epedienteRepository.findById(idExpediente);

		if (atendimento.isPresent()) {
			return ResponseEntity.ok(atendimento.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/ataulizar/{idExpediente}")
	public ResponseEntity<?> atualizarExpediente(@PathVariable Long idExpediente, @RequestBody Expediente atendimento) {
		try {
			Expediente atendimentoAtual = epedienteRepository.findById(idExpediente).orElse(null);

			if (atendimentoAtual != null) {
				BeanUtils.copyProperties(atendimento, atendimentoAtual, "id");

				atendimentoAtual = expedienteService.salvarExpediente(atendimentoAtual);
				return ResponseEntity.ok(atendimentoAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("remover/{idExpediente}")
	public ResponseEntity<?> removerEspedientePorId(@PathVariable Long idExpediente) {
		try {
			expedienteService.excluirExpediente(idExpediente);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}
	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

}
