package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name="ContaCorrente")
public class ContaCorrente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idContaCorrente;
	
	@NotBlank(message = "Informe o número da conta corrente.")
	@Column(name = "numero", nullable = false)
	private String contaCorrenteNumero;
	
	@ManyToOne
	@JoinColumn(name="idCliente", nullable = false)
	private Cliente idCliente;
	
	@NotBlank(message = "Informe o saldo da conta.")
	@Column(name = "saldo", nullable = false)
	private BigDecimal contaCorrenteSaldo;
	
	@ManyToOne
	@JoinColumn(name="idAgencia", nullable = false)
	private Agencia idAgencia;

}
