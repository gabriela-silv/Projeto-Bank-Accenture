package com.bankaccenture.Projeto_Bank_Accenture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrenteTransacoes;

@Repository

public interface ContaCorrenteTransacoesRepository extends JpaRepository<ContaCorrenteTransacoes, Integer>{

}
