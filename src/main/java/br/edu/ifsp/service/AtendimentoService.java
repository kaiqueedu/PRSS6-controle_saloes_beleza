package br.edu.ifsp.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.model.Atendimento;
import br.edu.ifsp.repository.AtendimentoRepository;

public class AtendimentoService {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	public Atendimento salvarAtendimento(Atendimento atendimento) {
		return atendimentoRepository.save(atendimento);
	}

	public void excluirAtendimento(Long idAtendimento) throws Exception {
		try {
			atendimentoRepository.deleteById(idAtendimento);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe atendimento com código %d", idAtendimento));
		}
	}
	
}
