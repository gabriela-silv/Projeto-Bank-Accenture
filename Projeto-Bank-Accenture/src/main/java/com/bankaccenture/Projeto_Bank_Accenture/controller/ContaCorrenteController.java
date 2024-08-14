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

import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ContaCorrenteService;

@RestController
public class ContaCorrenteController {

	@Autowired
	ContaCorrenteService contaCorrenteService;

	@GetMapping("/conta-corrente")
	public ResponseEntity<List<ContaCorrente>> listarContaCorrentes(){
		return ResponseEntity.ok(contaCorrenteService.listarContaCorrentes());
	}
	
	@GetMapping("/conta-corrente/{id}")
	public ResponseEntity<ContaCorrente> listarContaCorrentePorId(@PathVariable("id") int id) {
		return ResponseEntity.ok(contaCorrenteService.listarContaCorrentePorId(id));
	}
	
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<ContaCorrente> getContaCorrenteByCliente(@PathVariable int idCliente) {
		return ResponseEntity.ok(contaCorrenteService.listarContaCorrentePorCliente(idCliente));
	}
	
	@PutMapping("/conta-corrente-atualizar/{id}")
	public ResponseEntity<Void> atualizarContaCorrente(@RequestBody ContaCorrente cc, @PathVariable("id") int id) {
		contaCorrenteService.atualizarContaCorrente(cc, id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/inserir-conta-corrente")
	public ResponseEntity<ContaCorrente> salvarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
		contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		return ResponseEntity.status(HttpStatus.CREATED).body(contaCorrente);
	}
	
	@DeleteMapping("/conta-corrente-deletar/{id}")
	public ResponseEntity<Void> deletarContaCorrentePorId(@PathVariable("id") int id) {
		ContaCorrente cc = contaCorrenteService.listarContaCorrentePorId(id);
		contaCorrenteService.deletarContaCorrentePorId(cc);
		return ResponseEntity.noContent().build();
	}

	// Codigo abaixo retorna os objetos ou tipos primitivos ao inves de uma resposta
	// http
	/*
	 * @GetMapping("/conta-corrente") private List<ContaCorrente>
	 * listarContaCorrentes(){
	 * 
	 * return contaCorrenteService.listarContaCorrentes(); }
	 * 
	 * @GetMapping("/conta-corrente/{id}") private ContaCorrente
	 * listarContaCorrentePorId(@PathVariable("id") int id) { return
	 * contaCorrenteService.listarContaCorrentePorId(id); }
	 * 
	 * @GetMapping("/cliente/{idCliente}") private ContaCorrente
	 * getContaCorrenteByCliente(@PathVariable int idCliente) { return
	 * contaCorrenteService.listarContaCorrentePorCliente(idCliente);
	 * 
	 * }
	 * 
	 * @PutMapping("/conta-corrente-atualizar/{id}") private int
	 * atualizarContaCorrente(@RequestBody ContaCorrente cc,@PathVariable("id") int
	 * id) { contaCorrenteService.atualizarContaCorrente(cc, id);
	 * 
	 * return cc.getIdContaCorrente(); }
	 * 
	 * 
	 * @PostMapping("/inserir-conta-corrente") private int
	 * salvarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
	 * 
	 * contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	 * 
	 * return contaCorrente.getIdContaCorrente(); }
	 * 
	 * @DeleteMapping("/conta-corrente-deletar/{id}") private void
	 * deletarContaCorrentePorId(@PathVariable("id") int id) { ContaCorrente cc =
	 * contaCorrenteService.listarContaCorrentePorId(id);
	 * 
	 * contaCorrenteService.deletarContaCorrentePorId(cc); }
	 * 
	 */

}
