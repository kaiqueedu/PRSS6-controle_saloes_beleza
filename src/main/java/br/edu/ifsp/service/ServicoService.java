package br.edu.ifsp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.model.Funcionario;
import br.edu.ifsp.model.Servico;
import br.edu.ifsp.repository.FuncionarioRepository;
import br.edu.ifsp.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private FuncionarioRepository FuncionarioRepository;

	public Servico salvarServico(Servico Servico) {
		Long funcionarioId = Servico.getFuncionario().getIdFuncionario();

		Funcionario funcionario = FuncionarioRepository.findById(funcionarioId).orElseThrow();
		Servico.setFuncionario(funcionario);

		return servicoRepository.save(Servico);
	}

	public Collection<Servico> buscarServicoPorEspecilidadeEDisponivel(Long idEspecialidade, Boolean disponivel) {
		 return servicoRepository.buscarServicoPorEspecilidadeEDisponivel(idEspecialidade, disponivel);
	}

	 
	
	public void excluirServico(Long idServico) throws Exception {
		try {
			servicoRepository.deleteById(idServico);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe cadastro de servico com código %d", idServico));
		}
	}


}
