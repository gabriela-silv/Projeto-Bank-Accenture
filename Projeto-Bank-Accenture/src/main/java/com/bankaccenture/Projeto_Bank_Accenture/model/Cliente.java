package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.io.Serializable;
import java.util.List;

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
@Table(name="Cliente")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCliente;
	
	@Column(name="nome")
	private String clienteNome;
	
	@Column(name="cpf")
	private String clienteCPF;
	
	@Column(name="telefone")
	private String clienteFone;
		
	@OneToMany(mappedBy = "cliente")
	private List<ContaCorrente>  contas;
	
	
}
