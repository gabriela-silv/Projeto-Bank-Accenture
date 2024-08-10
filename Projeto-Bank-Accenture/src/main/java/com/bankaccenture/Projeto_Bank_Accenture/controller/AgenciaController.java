package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccenture.Projeto_Bank_Accenture.model.Agencia;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.AgenciaService;

@RestController
public class AgenciaController {
	
	@Autowired
	AgenciaService agenciaService;

	@GetMapping("/agencias")
	private List<Agencia> listarAgencias() {
		return agenciaService.listarAgencias();
	}
	
	@PostMapping("/agencia-inserir")
	private int salvarAgencia(@RequestBody Agencia agencia) {
		
		agenciaService.cadastrarAgencia(agencia);
		
		return agencia.getIdAgencia();
	}

}
