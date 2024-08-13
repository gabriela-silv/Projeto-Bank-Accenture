package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ContaCorrenteTransacoes")
public class ContaCorrenteTransacoes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvento;
    
    @ManyToOne
    @JoinColumn(name="idContaCorrente", nullable = false)
    private ContaCorrente contaCorrente;
    
    @Column(name = "valorOperacao", nullable = false)
    private BigDecimal valor;
    
    @Column(name = "tipoOperacao", nullable = false)
    private TipoOperacao tipoOperacao;

    public ContaCorrenteTransacoes() {
    }

    public ContaCorrenteTransacoes(ContaCorrente contaCorrente, BigDecimal valor, TipoOperacao tipoOperacao) {
        this.contaCorrente = contaCorrente;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
    }
}
