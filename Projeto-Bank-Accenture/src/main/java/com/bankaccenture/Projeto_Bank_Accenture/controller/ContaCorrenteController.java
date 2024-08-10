package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ContaCorrenteService;

@RestController
public class ContaCorrenteController {
	
	@Autowired
	ContaCorrenteService contaCorrenteService;
	
	@GetMapping("/conta-corrente")
	private List<ContaCorrente> istarContaCorrentes(){
		
		return contaCorrenteService.listarContaCorrentes();	
	}
	
	@PostMapping("/inserir-conta-corrente")
	private int salvarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
		
		contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		
		return contaCorrente.getIdContaCorrente();	
	}

}
