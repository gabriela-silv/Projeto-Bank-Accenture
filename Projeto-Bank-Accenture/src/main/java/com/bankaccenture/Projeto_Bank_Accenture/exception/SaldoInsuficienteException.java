package com.bankaccenture.Projeto_Bank_Accenture.exception;


@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(double valor, double saldoAtual) {
        super("Saldo insuficiente para realizar a operação de valor " + valor + ". Saldo atual: " + saldoAtual);
    }
}