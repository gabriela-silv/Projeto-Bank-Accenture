package com.bankaccenture.Projeto_Bank_Accenture.exceptions;

public class ContaCorrenteNaoEncontradaException extends RuntimeException {
	
    private static final long serialVersionUID = 1L; 

    public ContaCorrenteNaoEncontradaException(String message) {
        super(message);
    }

    public ContaCorrenteNaoEncontradaException(Long contaCorrenteId) {
        super("Conta corrente destino não encontrada com ID " + contaCorrenteId);
    }
}