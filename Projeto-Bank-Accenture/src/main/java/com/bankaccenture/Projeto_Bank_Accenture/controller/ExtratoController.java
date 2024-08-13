package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ExtratoService;

@RestController
public class ExtratoController {
	
	@Autowired
	ExtratoService extratoService;
	
	@GetMapping("/extrato")
	private List<Extrato> listarExtrato(){
		
		return extratoService.listarExtratos();
	}
	
	@PostMapping("/extrato-inserir")
	private int salvarExtrato(@RequestBody Extrato extrato) {
		
		extratoService.cadastrarExtrato(extrato);
		
		return extrato.getIdExtrato();
	}

}