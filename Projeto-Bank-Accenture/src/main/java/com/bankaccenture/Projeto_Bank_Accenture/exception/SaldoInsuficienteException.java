package com.bankaccenture.Projeto_Bank_Accenture.exception;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(BigDecimal valor, BigDecimal saldoAtual) {
        super("Saldo insuficiente para realizar a operação de valor " + valor + ". Saldo atual: " + saldoAtual);
    }
}