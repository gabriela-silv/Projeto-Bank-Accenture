package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.util.Calendar;
import java.util.Objects;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Extrato")
public class Extrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExtrato;
	
	@Column
	private Calendar dataHoraMovimento;
	private TipoOperacao operacao;
	
	@ManyToOne
	@JoinColumn(name="idContaCorrente")
	private ContaCorrente contaCorrente;

	public Extrato() {}
	
	public Extrato(Calendar dataHoraMovimento, TipoOperacao operacao) {
		this.dataHoraMovimento = dataHoraMovimento;
		this.operacao = operacao;
	}

	public Long getIdExtrato() {
		return idExtrato;
	}

	public void setIdExtrato(Long idExtrato) {
		this.idExtrato = idExtrato;
	}

	public Calendar getDataHoraMovimento() {
		return dataHoraMovimento;
	}

	public void setDataHoraMovimento(Calendar dataHoraMovimento) {
		this.dataHoraMovimento = dataHoraMovimento;
	}

	public TipoOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(TipoOperacao operacao) {
		this.operacao = operacao;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	@Override
	public String toString() {
		return "Extrato [idExtrato=" + idExtrato + ", dataHoraMovimento=" + dataHoraMovimento + ", operacao=" + operacao
				+ ", contaCorrente=" + contaCorrente + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idExtrato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extrato other = (Extrato) obj;
		return Objects.equals(idExtrato, other.idExtrato);
	}

	
	
}
