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
	
	@GetMapping("/agencia/{id}")
	private Agencia getAgenciaPorId(@PathVariable("id") int id) 
	{
	    return agenciaService.listarAgenciaPorId(id);
	}
	
	@PostMapping("/agencia-inserir")
	private int salvarAgencia(@RequestBody Agencia agencia) {

		agenciaService.cadastrarAgencia(agencia);

		return agencia.getIdAgencia();
	}


	@PutMapping("/agencia-atualizar/{id}")
	private int atualizarAgencia(@RequestBody Agencia agencia,@PathVariable("id") int id) {
		agenciaService.atualizarAgencia(agencia, id);

		return agencia.getIdAgencia();
	}

	@DeleteMapping("/agencia/{id}")
	private void deletarAgenciaPorId(@PathVariable("id") int id) {
		Agencia agencia = agenciaService.listarAgenciaPorId(id);

		agenciaService.deletarAgenciaPorId(agencia);
	}

}