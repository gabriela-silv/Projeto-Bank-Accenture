package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankaccenture.Projeto_Bank_Accenture.model.Agencia;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.AgenciaService;

@RestController
public class AgenciaController {
	
	@Autowired
	AgenciaService agenciaService;
	
	//Listar agências
	@GetMapping("/agencias")
	private List<Agencia> listarAgencias() {
		return agenciaService.listarAgencias();
	}
	
	//Cadastrar agência
	@PostMapping("/agencia-inserir")
	private int salvarAgencia(@RequestBody Agencia agencia) {
		
		agenciaService.cadastrarAgencia(agencia);
		
		return agencia.getIdAgencia();
	}
	
	//Atualizar agência
	@PutMapping("/agencia-atualizar")
	private int atualizarAgencia(@RequestBody Agencia agencia) 
	{
		agenciaService.atualizarAgencia(agencia);
		
		return agencia.getIdAgencia();
	}
	
	//Deletar agencia
	@DeleteMapping("/agencia-deletar")
	private int deletarAgencia(@RequestBody Agencia agencia) 
	{
		int id = agencia.getIdAgencia();
		agenciaService.deletarAgencia(agencia);
		return id;
	}
	
	//Deletar agencia por id
	@DeleteMapping("/agencia-deletar-por-id")
	private String deletarAgenciaPorId(int id) 
	{
		return agenciaService.deletarAgenciaPorId(id);
	}

}
