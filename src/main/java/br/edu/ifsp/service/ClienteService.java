package br.edu.ifsp.service;

import br.edu.ifsp.controller.exception.ClienteNotFoundException;
import br.edu.ifsp.controller.vo.ClienteResponse;
import br.edu.ifsp.model.Cliente;
import br.edu.ifsp.repository.ClienteRepository;
import br.edu.ifsp.util.ClienteMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<ClienteResponse> listarClientes() {
		return listaClientesByAtivo(true);
		//return clienteRepository.findAll().stream().filter(it -> it.getAtivo()).map(mapper::clienteToClienteResponse).collect(Collectors.toList());
	}

	public List<ClienteResponse> listarClientesInativos() {
		return listaClientesByAtivo(false);
	}

	public ClienteResponse buscaClientePorId(Long id) {
		var mapper = Mappers.getMapper(ClienteMapper.class);
		Cliente clienteSalvo = getClienteById(id);
		return mapper.clienteToClienteResponse(clienteSalvo);
	}

	public ClienteResponse atualizarCliente(Long id, Cliente cliente) {
		var mapper = Mappers.getMapper(ClienteMapper.class);
		Cliente clienteSalvo = getClienteById(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		return mapper.clienteToClienteResponse(clienteRepository.save(clienteSalvo));
	}

	public void ativarCadastroCliente(Long id) {
		atualizaStatusCliente(true, getClienteById(id));
	}

	public void excluirCliente(Long id) {
		atualizaStatusCliente(false, getClienteById(id));
	}

	private Cliente getClienteById(Long id)  {
		Cliente cliente = new Cliente();
		try{
			return clienteRepository.findById(id).orElseThrow();
		} catch (Exception e) {
			throw new ClienteNotFoundException(String.format("Não existe cliente com código %d", id));
		}
	}

	private List<ClienteResponse> listaClientesByAtivo(Boolean ativo){
		var mapper = Mappers.getMapper(ClienteMapper.class);
		return clienteRepository.findAllByAtivo(ativo).stream().map(mapper::clienteToClienteResponse).collect(Collectors.toList());
	}

	private Cliente getClienteByIdAtivo(Long id)  {
		Cliente cliente = new Cliente();
		try{
			return clienteRepository.findById(id).orElseThrow();
		} catch (Exception e) {
			throw new ClienteNotFoundException(String.format("Não existe cliente com código %d", id));
		}
	}

	private void atualizaStatusCliente(Boolean ativo, Cliente cliente){
		cliente.setAtivo(ativo);
		clienteRepository.save(cliente);
	}

}
