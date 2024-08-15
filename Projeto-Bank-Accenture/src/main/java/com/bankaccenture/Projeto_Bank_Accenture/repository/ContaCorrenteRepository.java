package com.bankaccenture.Projeto_Bank_Accenture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente,Integer>{

	Optional<List<ContaCorrente>> findByIdClienteIdCliente(int idCliente);
}

