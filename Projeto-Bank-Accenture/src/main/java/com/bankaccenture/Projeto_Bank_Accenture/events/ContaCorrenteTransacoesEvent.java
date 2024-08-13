package com.bankaccenture.Projeto_Bank_Accenture.events;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ContaCorrenteEvent")
public class ContaCorrenteEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvento;
    
    @NotBlank(message = "Informe a conta corrente.")
    @ManyToOne
	@JoinColumn(name="idContaCorrente", nullable = false)
    private ContaCorrente idContaCorrente;
    
    @NotBlank(message = "Informe o valor da operacao.")
	@Column(name = "valorOperacao", nullable = false)
    private BigDecimal valor;
    
    @NotBlank(message = "Informe o tipo da operacao.")
	@Column(name = "tipoOperacao", nullable = false)
    private TipoOperacao tipoOperacao;

    public ContaCorrenteEvent() {
        super((Object) null); // Chama o construtor da classe pai com source nulo
    }

    public ContaCorrenteEvent(Object source, ContaCorrente contaCorrente, BigDecimal valor, TipoOperacao tipoOperacao) {
        super(source);
        this.idContaCorrente = contaCorrente;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
    }
}