package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "Informe o nome do cliente.")
	@Column(name="nome", nullable = false)
	private String clienteNome;
	
	@NotBlank(message = "Informe o CPF do cliente.")
	@Size(max = 14, message="O CPF do cliente deve ter no máximo {max} caracteres.")
	@Column(name="cpf", nullable = false)
	private String clienteCPF;
	
	@NotBlank(message = "Informe o telefone do cliente.")
	@Column(name="telefone", nullable = false)
	private String clienteFone;
	
	
}
