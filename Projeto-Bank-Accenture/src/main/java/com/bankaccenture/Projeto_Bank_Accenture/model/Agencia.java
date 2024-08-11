package com.bankaccenture.Projeto_Bank_Accenture.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="Agencia")
public class Agencia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAgencia;
	
	@NotBlank(message = "Informe o nome da agência.")
	@Column(name="agencia", nullable = false)
	private String nomeAgencia;
	
	@NotBlank(message = "Informe o endereço da agência.")
	@Column(name="endereco", nullable = false)
	private String endereco;
	
	@NotBlank(message = "Informe o telefone da agência.")
	@Column(name="telefone", nullable = false)
	private String telefone;
	
	@ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente idCliente;
	
}
