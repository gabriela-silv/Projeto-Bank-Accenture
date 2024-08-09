package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
	
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;


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
