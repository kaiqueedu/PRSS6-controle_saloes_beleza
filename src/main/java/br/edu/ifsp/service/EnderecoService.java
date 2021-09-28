package br.edu.ifsp.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.model.Endereco;
import br.edu.ifsp.repository.EnderecoRepository;

public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco salvarEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void excluirEndereco(Long cep) throws Exception {
		try {
			enderecoRepository.deleteById(cep);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe cep com código %d", cep));
		}
	}

}
