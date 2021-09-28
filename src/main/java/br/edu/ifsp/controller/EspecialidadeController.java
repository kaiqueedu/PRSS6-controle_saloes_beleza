package br.edu.ifsp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.model.Especialidade;
import br.edu.ifsp.repository.EspecialidadeRepository;
import br.edu.ifsp.service.EspecialidadeService;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@Autowired
	private EspecialidadeService especialidadeService;


	// CRUD (Create, Read, Update, Delete)

	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarEspecialidade(@RequestBody Especialidade especialidade) {
		try {
			especialidade = especialidadeService.salvarEspecialidade(especialidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(especialidade);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Especialidade> listarTodasEspecialidades() {
		return especialidadeRepository.findAll();
	}

	@GetMapping("/{idEspecialidade}")
	public ResponseEntity<Especialidade> buscarEspecialidadePorId(@PathVariable Long idEspecialidade) {
		Optional<Especialidade> especialidade = especialidadeRepository.findById(idEspecialidade);

		if (especialidade.isPresent()) {
			return ResponseEntity.ok(especialidade.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/atualizar/{idEspecialidade}")
	public ResponseEntity<?> atualizarEspecialidade(@PathVariable Long idEspecialidade,
			@RequestBody Especialidade especialidade) {
		try {
			Especialidade especialidadeAtual = especialidadeRepository.findById(idEspecialidade).orElse(null);

			if (especialidadeAtual != null) {
				BeanUtils.copyProperties(especialidade, especialidadeAtual, "id");

				especialidadeAtual = especialidadeService.salvarEspecialidade(especialidadeAtual);
				return ResponseEntity.ok(especialidadeAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

}
