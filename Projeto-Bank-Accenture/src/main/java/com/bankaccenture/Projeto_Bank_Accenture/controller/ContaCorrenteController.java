package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/conta-corrente")
	private List<ContaCorrente> listarContaCorrentes(){
		
		return contaCorrenteService.listarContaCorrentes();	
	}
	
	@GetMapping("/conta-corrente/{id}")
	private ContaCorrente listarContaCorrentePorId(@PathVariable("id") int id) 
	{
	    return contaCorrenteService.listarContaCorrentePorId(id);
	}
	
	@GetMapping("/cliente/{idCliente}")
    private ContaCorrente getContaCorrenteByCliente(@PathVariable 	int idCliente) {
		return contaCorrenteService.listarContaCorrentePorCliente(idCliente);
        
    }
	
	@PutMapping("/conta-corrente-atualizar/{id}")
	private int atualizarContaCorrente(@RequestBody ContaCorrente cc,@PathVariable("id") int id) {
		contaCorrenteService.atualizarContaCorrente(cc, id);

		return cc.getIdContaCorrente();
	}
	
	
	@PostMapping("/inserir-conta-corrente")
	private int salvarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
		
		contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		
		return contaCorrente.getIdContaCorrente();	
	}
	
	@DeleteMapping("/conta-corrente-deletar/{id}")
	private void deletarContaCorrentePorId(@PathVariable("id") int id) {
		ContaCorrente cc = contaCorrenteService.listarContaCorrentePorId(id);

		contaCorrenteService.deletarContaCorrentePorId(cc);
	}
	
	

}
