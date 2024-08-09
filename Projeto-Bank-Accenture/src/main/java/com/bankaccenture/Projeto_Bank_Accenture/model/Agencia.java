package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="Agencia")
public class Agencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgencia;
	
	@Column
	private StringBuffer nomeAgencia;
	private StringBuffer endereco;
	private StringBuffer telefone;
	
	@OneToMany(mappedBy = "agencia")
	private List<ContaCorrente> contas;

	public Agencia() {}
	
	public Agencia(StringBuffer nomeAgencia, StringBuffer endereco, StringBuffer telefone) {
		this.nomeAgencia = nomeAgencia;
		this.endereco = endereco;
		this.telefone = telefone;
		this.contas = new ArrayList<ContaCorrente>();
	}


	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public StringBuffer getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(StringBuffer nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public StringBuffer getEndereco() {
		return endereco;
	}

	public void setEndereco(StringBuffer endereco) {
		this.endereco = endereco;
	}

	public StringBuffer getTelefone() {
		return telefone;
	}

	public void setTelefone(StringBuffer telefone) {
		this.telefone = telefone;
	}

	public List<ContaCorrente> getContas() {
		return contas;
	}

	public void setContas(List<ContaCorrente> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		return "Agencia [idAgencia=" + idAgencia + ", nomeAgencia=" + nomeAgencia + ",endereco=" + endereco
				+ ", telefone=" + telefone + ", contas=" + contas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAgencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agencia other = (Agencia) obj;
		return Objects.equals(idAgencia, other.idAgencia);
	}
	
	
	
}
