package com.bankaccenture.Projeto_Bank_Accenture.exception;

@SuppressWarnings("serial")
public class CampoObrigatorioException extends RuntimeException {
	
    public CampoObrigatorioException(String message) {
        super(message.concat(" é um campo obrigatório"));
    }
    
    
    
}