package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Extrato")
public class Extrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idExtrato;

	//@NotBlank(message = "Informe a data e a hora da operação.")
	@Column(name = "horarioMovimentacao", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	private LocalDateTime dataHoraMovimento;

	//@NotBlank(message = "Informe o tipo da operação.")
	@Column(name = "tipoOperacao", nullable = false)
	private TipoOperacao operacao;

	@ManyToOne
	@JoinColumn(name = "idContaCorrente", nullable = false)
	private ContaCorrente idContaCorrente;

	//@NotBlank(message = "Informe o valor da transação.")
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

}
