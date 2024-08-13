package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/conta/{idContaCorrente}")
    public List<Extrato> listarExtratosPorContaCorrente(@PathVariable int idContaCorrente) {
        return extratoService.listarExtratosPorContaCorrente(idContaCorrente);
    }

	
	@GetMapping("/cliente/{idCliente}/periodo")
	public List<Extrato> listarExtratosPorClienteEPeriodo(@PathVariable int idCliente, 
	                                                      @RequestParam("dataInicio") Date dataInicio, 
	                                                      @RequestParam("dataFim") Date dataFim) {
	    return extratoService.listarExtratosPorClienteEPeriodo(idCliente, dataInicio, dataFim);
	}
}
