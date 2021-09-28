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

import br.edu.ifsp.model.Atendimento;
import br.edu.ifsp.repository.AtendimentoRepository;
import br.edu.ifsp.service.AtendimentoService;

@RestController
@RequestMapping(value = "/atendimentos")
public class AtendimentoController {

	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	private AtendimentoService atendimentoService;

	
	// CRUD (Create, Read, Update, Delete)
	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarServico(@RequestBody Atendimento atendimento) {
		try {
			atendimento = atendimentoService.salvarAtendimento(atendimento);
			return ResponseEntity.status(HttpStatus.CREATED).body(atendimento);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Atendimento> listarTodosAtendimentos() {
		return atendimentoRepository.findAll();
	}

	@GetMapping("/buscar/{idAtendimento}")
	public ResponseEntity<Atendimento> buscarAtendimentoPorId(@PathVariable Long idAtendimento) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(idAtendimento);

		if (atendimento.isPresent()) {
			return ResponseEntity.ok(atendimento.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/atualizar/{idAtendimento}")
	public ResponseEntity<?> atualizarAtendimento(@PathVariable Long idAtendimento,@RequestBody Atendimento atendimento) {
		try {
			Atendimento atendimentoAtual = atendimentoRepository.findById(idAtendimento).orElse(null);

			if (atendimentoAtual != null) {
				BeanUtils.copyProperties(atendimento, atendimentoAtual, "id");

				atendimentoAtual = atendimentoService.salvarAtendimento(atendimentoAtual);
				return ResponseEntity.ok(atendimentoAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/remover/{idAtendimento}")
	public ResponseEntity<?> removerAtendimentoPorId(@PathVariable Long idAtendimento) {
		try {
			atendimentoService.excluirAtendimento(idAtendimento);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}
	
	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL
	 

}
