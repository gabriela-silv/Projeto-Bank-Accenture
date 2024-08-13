package com.bankaccenture.Projeto_Bank_Accenture.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;

public interface ExtratoRepository extends JpaRepository<Extrato,Integer>{
	List<Extrato> findByIdContaCorrenteIdContaCorrente(int idContaCorrente);
	List<Extrato> findByIdContaCorrenteIdClienteIdClienteAndDataBetween(int idCliente, Date dataInicio, Date dataFim);

}

