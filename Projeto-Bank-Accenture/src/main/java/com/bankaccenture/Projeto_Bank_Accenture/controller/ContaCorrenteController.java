package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ContaCorrenteService;

@RestController
public class ContaCorrenteController {
	
	@Autowired
	ContaCorrenteService contaCorrenteService;
	
	//Listar contas correntes
	@GetMapping("/conta-corrente")
	private List<ContaCorrente> istarContaCorrentes(){
		
		return contaCorrenteService.listarContaCorrentes();	
	}
	
	//Inserir conta corrente
	@PostMapping("/conta-corrente-inserir")
	private int salvarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
		
		contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		
		return contaCorrente.getIdContaCorrente();	
	}
	
	//Atualizar conta corrente
	@PutMapping("/conta-corrente-atualizar")
	private int atualizarContaCorrente(@RequestBody ContaCorrente contaCorrente) 
	{
		contaCorrenteService.atualizarContaCorrente(contaCorrente);
		
		return contaCorrente.getIdContaCorrente();
	}
	
	//Deletar conta corrente
	@DeleteMapping("/conta-corrente-deletar")
	private int deletarContaCorrente(@RequestBody ContaCorrente contaCorrente) 
	{
		int id = contaCorrente.getIdContaCorrente();
		contaCorrenteService.deletarContaCorrente(contaCorrente);
		return id;
	}
	
	//Deletar conta corrente por id
	@DeleteMapping("/conta-corrente-deletar-por-id")
	private String deletarContaCorrentePorId(int id) 
	{
		return contaCorrenteService.deletarContaCorrentePorId(id);
	}

}
