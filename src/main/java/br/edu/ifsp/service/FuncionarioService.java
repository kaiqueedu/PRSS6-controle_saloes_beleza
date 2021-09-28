package br.edu.ifsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.model.Funcionario;
import br.edu.ifsp.model.validator.CpfValidator;
import br.edu.ifsp.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	private CpfValidator cpfValidator;

	public Funcionario salvarFuncionario(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public void excluirFuncionario(Long idFuncionario) throws Exception {
		try {
			funcionarioRepository.deleteById(idFuncionario);

		} catch (Exception e) {
			throw new Exception(String.format("Não existe cadastro de funcionário com código %d", idFuncionario));
		}
	}

	public boolean validarCPF_Funcionario(String cpf) {
		return cpfValidator.isCPFValido(cpf);
	}

	public Funcionario buscarLoginAndSenhaFuncionarioAdministrador(String login, String senha) {
		return funcionarioRepository.buscarLoginAndSenhaFuncionarioAdministrador(login, senha);
	}
	
	
	//TODO: VALIDAR: TELEFONE + CEP + CPF

}