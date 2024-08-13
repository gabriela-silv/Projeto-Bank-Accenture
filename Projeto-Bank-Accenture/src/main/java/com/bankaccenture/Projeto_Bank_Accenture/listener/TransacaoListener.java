package com.bankaccenture.Projeto_Bank_Accenture.listener;

import com.bankaccenture.Projeto_Bank_Accenture.event.TransacaoEvent;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



@Component
public class TransacaoListener {

    @Autowired
    private ExtratoService extratoService;

    @EventListener
    public void handleOperacaoEvent(TransacaoEvent event) {
        extratoService.cadastrarExtrato(event.getContaCorrente(), event.getValor(), event.getTipoOperacao());
    }
}
