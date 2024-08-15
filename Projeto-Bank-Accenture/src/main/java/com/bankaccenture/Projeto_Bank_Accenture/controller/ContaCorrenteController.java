package com.bankaccenture.Projeto_Bank_Accenture.controller;

import java.math.BigDecimal;
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
	public ResponseEntity<List<ContaCorrente>> listarContaCorrentes() {
		return ResponseEntity.ok(contaCorrenteService.listarContaCorrentes());
	}

	@GetMapping("/conta-corrente/{id}")
	public ResponseEntity<ContaCorrente> listarContaCorrentePorId(@PathVariable("id") int id) {
		return ResponseEntity.ok(contaCorrenteService.listarContaCorrentePorId(id));
	}

	@GetMapping("/conta/cliente/{idCliente}")
	public ResponseEntity<List<ContaCorrente>> getContaCorrenteByCliente(@PathVariable int idCliente) {
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

	@PostMapping("/sacar/{idContaCorrente}")
	public ResponseEntity<String> sacar(@PathVariable int idContaCorrente, @RequestBody BigDecimal valor) {
		contaCorrenteService.sacar(idContaCorrente, valor);
		return ResponseEntity.status(HttpStatus.OK).body("Saque realizado com sucesso");
	}

	@PostMapping("/depositar/{idContaCorrente}")
	public ResponseEntity<String> depositar(@PathVariable int idContaCorrente, @RequestBody BigDecimal valor) {
		contaCorrenteService.depositar(idContaCorrente, valor);
		return ResponseEntity.status(HttpStatus.OK).body("Depósito realizado com sucesso");
	}

	@PostMapping("/transferir/{idContaCorrenteOrigem}/{idContaCorrenteDestino}")
	public ResponseEntity<String> transferir(@PathVariable int idContaCorrenteOrigem,
			@PathVariable int idContaCorrenteDestino, @RequestBody BigDecimal valor) {
		contaCorrenteService.transferir(idContaCorrenteOrigem, idContaCorrenteDestino, valor);
		return ResponseEntity.status(HttpStatus.OK).body("Transferência realizada com sucesso");
	}

}