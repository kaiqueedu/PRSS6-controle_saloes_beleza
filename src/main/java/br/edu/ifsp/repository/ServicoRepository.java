package br.edu.ifsp.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico,Long>{

	List<Servico> buscarServicoPorValoresEntre(BigDecimal valorInicial, BigDecimal valorFinal);

	List<Servico> buscarServicosPorNomeEFuncionario(String descricao, Long idFuncionario);

	List<Servico> buscarFuncionariosRelacionadosAServico(String descricao, Long funionarioId);

	List<Servico> buscarServicosPorDescricaoEValor(String descricao, BigDecimal valorInicialServico,BigDecimal valorFinalServico);

	Collection<Servico> buscarServicoPorEspecilidadeEDisponivel(Long idEspecialidade, Boolean disponivel);

	List<Servico> consultarDescontoServicoClienteFidelidade(Long idCliente);


}
