package com.bankaccenture.Projeto_Bank_Accenture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente,Integer>{

	@Query("SELECT cc FROM ContaCorrente cc WHERE cc.idCliente.id = :idCliente")
    Optional<ContaCorrente> findByIdCliente(@Param("idCliente") int idCliente);


}

