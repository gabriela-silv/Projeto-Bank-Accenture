package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Agencia>> listarAgencias() {
		return ResponseEntity.ok(agenciaService.listarAgencias());
	}

	@GetMapping("/agencia/{id}")
	public ResponseEntity<Agencia> getAgenciaPorId(@PathVariable("id") int id) {
		return ResponseEntity.ok(agenciaService.listarAgenciaPorId(id));
	}

	@PostMapping("/agencia-inserir")
	public ResponseEntity<Agencia> salvarAgencia(@RequestBody Agencia agencia) {
		agenciaService.cadastrarAgencia(agencia);
		return ResponseEntity.status(HttpStatus.CREATED).body(agencia);
	}

	@PutMapping("/agencia-atualizar/{id}")
	public ResponseEntity<Void> atualizarAgencia(@RequestBody Agencia agencia, @PathVariable("id") int id) {
		agenciaService.atualizarAgencia(agencia, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/agencia/{id}")
	public ResponseEntity<Void> deletarAgenciaPorId(@PathVariable("id") int id) {
		Agencia agencia = agenciaService.listarAgenciaPorId(id);
		agenciaService.deletarAgenciaPorId(agencia);
		return ResponseEntity.noContent().build();
	}
}
