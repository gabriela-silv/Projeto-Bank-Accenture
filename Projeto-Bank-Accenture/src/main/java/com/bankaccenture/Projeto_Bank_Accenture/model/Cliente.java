package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table(name="Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	@Column
	private StringBuffer clienteNome;
	private StringBuffer clienteCPF;
	private StringBuffer clienteFone;
	
	@OneToMany(mappedBy = "cliente")
	private List<ContaCorrente>  contas;
	
	
	
	public Cliente() {}

	public Cliente(StringBuffer clienteNome, StringBuffer clienteCPF, StringBuffer clienteFone) {
		this.clienteNome = clienteNome;
		this.clienteCPF = clienteCPF;
		this.clienteFone = clienteFone;
		this.contas = new ArrayList<ContaCorrente>();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public StringBuffer getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(StringBuffer clienteNome) {
		this.clienteNome = clienteNome;
	}

	public StringBuffer getClienteCPF() {
		return clienteCPF;
	}

	public void setClienteCPF(StringBuffer clienteCPF) {
		this.clienteCPF = clienteCPF;
	}

	public StringBuffer getClienteFone() {
		return clienteFone;
	}

	public void setClienteFone(StringBuffer clienteFone) {
		this.clienteFone = clienteFone;
	}
	

	public List<ContaCorrente> getContas() {
		return contas;
	}

	public void setContas(List<ContaCorrente> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", clienteNome=" + this.clienteNome + ", clienteCPF=" + this.clienteCPF
				+ ", clienteFone=" + this.clienteFone + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(idCliente, other.idCliente);
	}
	
	
	
	
}
