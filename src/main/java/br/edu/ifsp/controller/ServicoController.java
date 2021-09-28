package br.edu.ifsp.controller;

import java.math.BigDecimal;
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

import br.edu.ifsp.model.Servico;
import br.edu.ifsp.repository.ServicoRepository;
import br.edu.ifsp.service.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ServicoService servicoService;

	@GetMapping
	public List<Servico> listar() {
		return servicoRepository.findAll();
	}

	// CRUD (Create, Read, Update, Delete)

	@PostMapping("/adicionar")
	public ResponseEntity<?> adicionarServico(@RequestBody Servico servico) {
		try {
			servico = servicoService.salvarServico(servico);
			return ResponseEntity.status(HttpStatus.CREATED).body(servico);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public List<Servico> listarTodosServicos() {
		return servicoRepository.findAll();
	}

	@GetMapping("/buscar/{idServico}")
	public ResponseEntity<Servico> buscarServicoPorId(@PathVariable Long idServico) {
		Optional<Servico> servico = servicoRepository.findById(idServico);

		if (servico.isPresent()) {
			return ResponseEntity.ok(servico.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/atualizar/{idServico}")
	public ResponseEntity<?> atualizarServico(@PathVariable Long idServico, @RequestBody Servico servico) {
		try {
			Servico servicoFuncionarioAtual = servicoRepository.findById(idServico).orElse(null);

			if (servicoFuncionarioAtual != null) {
				BeanUtils.copyProperties(servico, servicoFuncionarioAtual, "id");

				servicoFuncionarioAtual = servicoService.salvarServico(servicoFuncionarioAtual);
				return ResponseEntity.ok(servicoFuncionarioAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("remover/{idServico")
	public ResponseEntity<?> removerServicoPorId(@PathVariable Long idServico) {
		try {
			servicoService.excluirServico(idServico);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}
	}

	//TODO: CONSULTAS CUSTOMIZADAS - ELABORAR JDBC/JPQL

	@GetMapping("/buscar/{valorInicial}/{valorFinal}")
	public List<Servico> buscarServicosPorValor(@PathVariable BigDecimal valorInicial,@PathVariable BigDecimal valorFinal) {
		return servicoRepository.buscarServicoPorValoresEntre(valorInicial, valorFinal);
	}

	@GetMapping("/buscar/{descricao}/{idFuncionario}")
	public List<Servico> ServicosPorNomeEFuncionario(@PathVariable String descricao, @PathVariable Long idFuncionario) {
		return servicoRepository.buscarServicosPorNomeEFuncionario(descricao, idFuncionario);
	}

	@GetMapping("/buscar/{descricao}/{funionarioId}")
	public List<Servico> buscarFuncionariosRelacionadosAServico(@PathVariable String descricao,	@PathVariable Long funionarioId) {
		return servicoRepository.buscarFuncionariosRelacionadosAServico(descricao, funionarioId);
	}

	@GetMapping("/buscar/{descricao}/{valorInicial}/{valorFinal}")
	public List<Servico> buscarServicosPorDescricaoEValor(@PathVariable String descricao,@PathVariable BigDecimal valorInicial, @PathVariable BigDecimal valorFinal) {
		return servicoRepository.buscarServicosPorDescricaoEValor(descricao, valorInicial, valorFinal);
	}

	@GetMapping("/buscar/desconto/{idCliente}")
	public List<Servico> consultarDescontoServicoParaClienteFidelidade(@PathVariable Long idCliente) {
		return servicoRepository.consultarDescontoServicoClienteFidelidade(idCliente);
	}

	@GetMapping(value = "/buscar/servico-por/{idEspecialidade}/{disponivel}")
	public ResponseEntity<Collection<Servico>> buscarServicoPorEspecilidadeEDisponivel(	@PathVariable Long idEspecialidade, @PathVariable Boolean disponivel) {
		Collection<Servico> collection = servicoService.buscarServicoPorEspecilidadeEDisponivel(idEspecialidade,disponivel);
		return ResponseEntity.ok().body(collection);
	}

}
