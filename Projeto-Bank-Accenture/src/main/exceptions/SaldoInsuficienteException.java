package com.bankaccenture.Projeto_Bank_Accenture.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
	private static final long serialVersionUID = 1L; 
	
    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(int valor, int saldoAtual) {
        super("Saldo insuficiente para realizar a operação de valor " + valor + ". Saldo atual: " + saldoAtual);
    }
}