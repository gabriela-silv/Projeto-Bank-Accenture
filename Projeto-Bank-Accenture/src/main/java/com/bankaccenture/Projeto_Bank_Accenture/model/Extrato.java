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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
