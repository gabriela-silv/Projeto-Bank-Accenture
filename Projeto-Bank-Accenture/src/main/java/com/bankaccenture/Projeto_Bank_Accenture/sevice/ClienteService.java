package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.exception.CPFInvalidoException;
import com.bankaccenture.Projeto_Bank_Accenture.exception.CampoObrigatorioException;
import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ClienteRepository;

import jakarta.validation.constraints.AssertTrue;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clienteRepository.findAll().forEach(aluno -> clientes.add(aluno));
		return clientes;
	}

	@Transactional(readOnly = true)
	public Cliente listarClientePorId(int id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		return cliente;
	}

	@Transactional(readOnly = false)
	public Cliente cadastrarCliente(Cliente cliente) throws CampoObrigatorioException {

		if (cliente.getClienteNome() == null || cliente.getClienteNome().isEmpty()) {
			throw new CampoObrigatorioException("Nome");
		}

		if (cliente.getClienteCPF() == null || cliente.getClienteCPF().isEmpty()) {
			throw new CampoObrigatorioException("CPF");
		}

		if (cliente.getClienteFone() == null || cliente.getClienteFone().isEmpty()) {
			throw new CampoObrigatorioException("Telefone");
		}

		cpfValido(cliente.getClienteCPF());

		return clienteRepository.save(cliente);
	}

	@Transactional(readOnly = false)
	public Cliente atualizarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Transactional(readOnly = false)
	public String deletarClientePorId(Cliente cliente) {
		int id = cliente.getIdCliente();
		clienteRepository.deleteById(cliente.getIdCliente());
		return "Cliente de id " + id + " deletado com sucesso";
	}

	@AssertTrue
	private boolean cpfValido(String cpf) {

		if (cpf.length() > 14) {
			throw new CPFInvalidoException("CPF não pode ter mais de 14 caracteres");
		}

		cpf = cpf.replaceAll("\\D", "");

		if (!cpf.matches("\\d+")) {
			throw new CPFInvalidoException("CPF inválido");
		}
		
		if (cpf.length() < 11) {
			throw new CPFInvalidoException("CPF inválido");
		}
		return true;
	}
	/*
	 * // Verificar se o CPF é válido usando a fórmula de validação int soma = 0;
	 * int peso = 10; for (int i = 0; i < 9; i++) { soma +=
	 * Integer.parseInt(cpf.substring(i, i + 1)) * peso; peso--; } int resto = soma
	 * % 11; if (resto < 2) { resto = 0; } else { resto = 11 - resto; } if (resto !=
	 * Integer.parseInt(cpf.substring(9, 10))) { return false; }
	 * 
	 * soma = 0; peso = 11; for (int i = 0; i < 10; i++) { soma +=
	 * Integer.parseInt(cpf.substring(i, i + 1)) * peso; peso--; } resto = soma %
	 * 11; if (resto < 2) { resto = 0; } else { resto = 11 - resto; } if (resto !=
	 * Integer.parseInt(cpf.substring(10, 11))) { return false; }
	 * 
	 * return true; }
	 */

}
