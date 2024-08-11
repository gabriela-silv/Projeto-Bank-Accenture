package com.bankaccenture.Projeto_Bank_Accenture.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bankaccenture.Projeto_Bank_Accenture.exception.CPFInvalidoException;
import com.bankaccenture.Projeto_Bank_Accenture.exception.CampoObrigatorioException;
import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;

import jakarta.validation.constraints.AssertTrue;

public class validacaoDeDados {

	public boolean validaCampos(Cliente cliente) {

		List<String> errors = new ArrayList<>();

		if (StringUtils.isBlank(cliente.getClienteNome())) {
			errors.add("Nome");
		}

		if (StringUtils.isBlank(cliente.getClienteCPF())) {
			errors.add("CPF");
		}

		if (StringUtils.isBlank(cliente.getClienteFone())) {
			errors.add("Telefone");
		}

		if (!errors.isEmpty()) {
			String erroCampos = String.join(" ", errors);
			throw new CampoObrigatorioException(erroCampos);
		}

		validaCPF(cliente.getClienteCPF());

		return true;
	}

	@AssertTrue
	private boolean validaCPF(String cpf) {

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

		// Verificar se o CPF é válido usando a fórmula de validação do CPF
		
		int soma = 0;
		int peso = 10;
		for (int i = 0; i < 9; i++) {
			soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso;
			peso--;
		}
		int resto = soma % 11;
		if (resto < 2) {
			resto = 0;
		} else {
			resto = 11 - resto;
		}
		if (resto != Integer.parseInt(cpf.substring(9, 10))) {
			return false;
		}

		soma = 0;
		peso = 11;
		for (int i = 0; i < 10; i++) {
			soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso;
			peso--;
		}
		resto = soma % 11;
		if (resto < 2) {
			resto = 0;
		} else {
			resto = 11 - resto;
		}
		if (resto != Integer.parseInt(cpf.substring(10, 11))) {
			return false;
		}

		return true;
	}

}