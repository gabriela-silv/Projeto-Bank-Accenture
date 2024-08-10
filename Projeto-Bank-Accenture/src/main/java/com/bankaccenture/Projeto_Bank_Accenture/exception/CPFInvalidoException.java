package com.bankaccenture.Projeto_Bank_Accenture.exception;

@SuppressWarnings("serial")
public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String mensagem) {
        super(mensagem);
    }
}