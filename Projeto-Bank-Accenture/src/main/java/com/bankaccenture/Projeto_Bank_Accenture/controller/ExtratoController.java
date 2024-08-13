package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ExtratoService;

@RestController
public class ExtratoController {
	
	@Autowired
	ExtratoService extratoService;
	
	//Listar extratos
	@GetMapping("/extrato")
	private List<Extrato> listarExtrato(){
		
		return extratoService.listarExtratos();
	}
	
	//Inserir extrato
	@PostMapping("/extrato-inserir")
	private int salvarExtrato(@RequestBody Extrato extrato) {
		
		extratoService.cadastrarExtrato(extrato);
		
		return extrato.getIdExtrato();
	}
	
	//Atualizar extrato
	@PutMapping("/extrato-atualizar")
	private int atualizarExtrato(@RequestBody Extrato extrato) 
	{
		extratoService.atualizarExtrato(extrato);
		
		return extrato.getIdExtrato();
	}
	
	//Deletar extrato
	@DeleteMapping("/extrato-deletar")
	private int deletarExtrato(@RequestBody Extrato extrato) 
	{
		int id = extrato.getIdExtrato();
		extratoService.deletarExtrato(extrato);
		return id;
	}
	
	//Deletar extrato por id
	@DeleteMapping("/extrato-deletar-por-id")
	private String deletarExtratoPorId(int id) 
	{
		return extratoService.deletarExtratoPorId(id);
	}

}
