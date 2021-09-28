package br.edu.ifsp.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.model.Expediente;
import br.edu.ifsp.repository.ExpedienteRepository;

public class ExpedienteService {

	@Autowired
	private ExpedienteRepository expedienteRepository;

	public Expediente salvarExpediente(Expediente expediente) {
		return expedienteRepository.save(expediente);
	}

	public void excluirExpediente(Long idExpediente) throws Exception {
		try {
			expedienteRepository.deleteById(idExpediente);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe expediente com código %d", idExpediente));
		}
	}
}
