package br.edu.ifsp.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.model.Fidelidade;
import br.edu.ifsp.repository.FidelidadeRepository;

public class FidelidadeService {

	@Autowired
	private FidelidadeRepository fidelidadeRepository;

	public Fidelidade salvarFidelidade(Fidelidade fidelidade) {
		return fidelidadeRepository.save(fidelidade);
	}

	public void excluirFidelidade(Long idFidelidade) throws Exception {
		try {
			fidelidadeRepository.deleteById(idFidelidade);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe fildelidade com código %d", idFidelidade));
		}
	}
}
