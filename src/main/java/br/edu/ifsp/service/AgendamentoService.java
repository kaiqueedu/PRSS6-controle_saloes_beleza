package br.edu.ifsp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.model.Agendamento;
import br.edu.ifsp.repository.AgendamentoRepository;
import br.edu.ifsp.repository.ClienteRepository;

public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	
	public Agendamento salvarAgendamento(Agendamento agendamento) {
		return agendamentoRepository.save(agendamento);
	}

	public void excluirAgendamento(Long idAgendamento) throws Exception {
		try {
			agendamentoRepository.deleteById(idAgendamento);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe agendamento com código %d", idAgendamento));
		}
	}

	public Collection<Agendamento> buscarClienteEPeriodo(Long idCliente, String inicio, String termino) {
		return agendamentoRepository.findByClienteAndPeriodo(idCliente, inicio, termino);
	}

	public Collection<?> findQuantidadesReservasOfClientesByPeriodo(String inicio, String termino) {
		return agendamentoRepository.findQuantidadesReservasOfClientesByPeriodo(inicio, termino);
	}
 

	public Collection<Agendamento> buscarAgendaPorEspecilidadeEDisponivel(Long idEspecialidade, Boolean disponivel) {
		 
		return agendamentoRepository.buscarAgendaPorEspecilidadeEDisponivel(idEspecialidade, disponivel);
	}

	 

}
