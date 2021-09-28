package br.edu.ifsp.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.model.Especialidade;
import br.edu.ifsp.repository.EspecialidadeRepository;

public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	public Especialidade salvarEspecialidade(Especialidade especialidade) {
		return especialidadeRepository.save(especialidade);
	}

	public void excluirEspecialidade(Long idEspecialidade) throws Exception {
		try {
			especialidadeRepository.deleteById(idEspecialidade);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe especialidade com código %d", idEspecialidade));
		}
	}
}
