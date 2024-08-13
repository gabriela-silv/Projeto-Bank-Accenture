package com.bankaccenture.Projeto_Bank_Accenture.event;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TransacaoEvent {
    private final ContaCorrente contaCorrente;
    private final BigDecimal valor;
    private final TipoOperacao tipoOperacao;
}