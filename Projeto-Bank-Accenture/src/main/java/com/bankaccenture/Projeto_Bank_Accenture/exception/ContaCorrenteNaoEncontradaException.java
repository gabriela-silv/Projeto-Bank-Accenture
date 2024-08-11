package com.bankaccenture.Projeto_Bank_Accenture.exception;


@SuppressWarnings("serial")
public class ContaCorrenteNaoEncontradaException extends RuntimeException {

	public ContaCorrenteNaoEncontradaException(String message) {
        super(message);
    }

    public ContaCorrenteNaoEncontradaException(int contaCorrenteId) {
        super("Conta corrente destino não encontrada com ID " + contaCorrenteId);
    }
}