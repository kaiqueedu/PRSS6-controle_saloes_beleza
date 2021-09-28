package br.edu.ifsp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.model.Cliente;
import br.edu.ifsp.repository.ClienteRepository;

public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void excluirCliente(Long idCliente) throws Exception {
		try {
			clienteRepository.deleteById(idCliente);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe cliente com código %d", idCliente));
		}
	}

	public Collection<Cliente> buscarAgendaPorEspecilidadeEDisponivel(Long idCliente, Boolean disponivel) {
		 
		return clienteRepository.buscarAgendaPorEspecilidadeEDisponivel(idCliente,disponivel);
	}

	//TODO: VALIDAR: TELEFONE + CEP + CPF
	
}
