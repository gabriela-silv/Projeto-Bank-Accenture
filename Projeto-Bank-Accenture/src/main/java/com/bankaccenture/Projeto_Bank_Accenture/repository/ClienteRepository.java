package com.bankaccenture.Projeto_Bank_Accenture.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
	Optional<Cliente> findByClienteCPF(String cpf);

}