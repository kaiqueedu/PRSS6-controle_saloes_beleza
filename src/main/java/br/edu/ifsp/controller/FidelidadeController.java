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

import br.edu.ifsp.model.Fidelidade;
import br.edu.ifsp.repository.FidelidadeRepository;
import br.edu.ifsp.service.FidelidadeService;

@RestController
@RequestMapping(value = "/fidelidades")
public class FidelidadeController {

	@Autowired
	private FidelidadeRepository fidelidadeRepository;

	@Autowired
	private FidelidadeService fidelidadeService;

	// CRUD (Create, Read, Update, Delete)

	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarFidelidade(@RequestBody Fidelidade fildeidade) {
		try {
			fildeidade = fidelidadeService.salvarFidelidade(fildeidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(fildeidade);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Fidelidade> listarTodosTiposFidelidades() {
		return fidelidadeRepository.findAll();
	}

	@GetMapping("/buscar/{idFidelidade}")
	public ResponseEntity<Fidelidade> buscarFidelidadePorId(@PathVariable Long idFidelidade) {
		Optional<Fidelidade> fildeidade = fidelidadeRepository.findById(idFidelidade);

		if (fildeidade.isPresent()) {
			return ResponseEntity.ok(fildeidade.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/atualizar/{idFidelidade}")
	public ResponseEntity<?> atualizarFidelidade(@PathVariable Long idFidelidade, @RequestBody Fidelidade fildeidade) {
		try {
			Fidelidade fildeidadeAtual = fidelidadeRepository.findById(idFidelidade).orElse(null);

			if (fildeidadeAtual != null) {
				BeanUtils.copyProperties(fildeidade, fildeidadeAtual, "id");

				fildeidadeAtual = fidelidadeService.salvarFidelidade(fildeidadeAtual);
				return ResponseEntity.ok(fildeidadeAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("remover/{idFidelidade}")
	public ResponseEntity<?> removerFidelidadePorId(@PathVariable Long idFidelidade) {
		try {
			fidelidadeService.excluirFidelidade(idFidelidade);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}
	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

}
