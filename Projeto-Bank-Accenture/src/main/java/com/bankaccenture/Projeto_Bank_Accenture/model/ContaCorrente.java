package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table(name="ContaCorrente")
public class ContaCorrente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContaCorrente;
	
	@Column
	private String contaCorrenteNumero;
	private Long contaCorrenteSaldo;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idAgencia")
	private Agencia agencia;
	
	@OneToMany(mappedBy = "contaCorrente")
	private List<Extrato> extratos;

	public ContaCorrente(String contaCorrenteNumero, Long contaCorrenteSaldo) {
		this.contaCorrenteNumero = contaCorrenteNumero;
		this.contaCorrenteSaldo = contaCorrenteSaldo;
		this.extratos = new ArrayList<Extrato>();
	}

	public ContaCorrente() {}

	public Long getIdContaCorrente() {
		return idContaCorrente;
	}

	public void setIdContaCorrente(Long idContaCorrente) {
		this.idContaCorrente = idContaCorrente;
	}

	public String getContaCorrenteNumero() {
		return contaCorrenteNumero;
	}

	public void setContaCorrenteNumero(String contaCorrenteNumero) {
		this.contaCorrenteNumero = contaCorrenteNumero;
	}

	public Long getContaCorrenteSaldo() {
		return contaCorrenteSaldo;
	}

	public void setContaCorrenteSaldo(Long contaCorrenteSaldo) {
		this.contaCorrenteSaldo = contaCorrenteSaldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	public List<Extrato> getExtratos() {
		return extratos;
	}

	public void setExtratos(List<Extrato> extratos) {
		this.extratos = extratos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idContaCorrente);
	}

	@Override
	public String toString() {
		return "ContaCorrente [idContaCorrente=" + idContaCorrente + ", contaCorrenteNumero=" + this.contaCorrenteNumero
				+ ", contaCorrenteSaldo=" + this.contaCorrenteSaldo + ", cliente=" + cliente + ", agencia=" + agencia + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaCorrente other = (ContaCorrente) obj;
		return Objects.equals(idContaCorrente, other.idContaCorrente);
	}

	
	
}
