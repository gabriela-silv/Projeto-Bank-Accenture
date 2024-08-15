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

import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	// Listar todos os clientes
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> listarClientes() {
		return ResponseEntity.ok(clienteService.listarClientes());
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> getClientePorId(@PathVariable("id") int id) {
		return ResponseEntity.ok(clienteService.listarClientePorId(id));
	}

	// Inserir clientes
	@PostMapping("/cliente-inserir")
	public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
		clienteService.cadastrarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}

	@PutMapping("/cliente-atualizar/{id}")
	public ResponseEntity<Void> atualizarAgencia(@RequestBody Cliente cliente, @PathVariable("id") int id) {
		clienteService.atualizarCliente(cliente, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/cliente-deletar/{id}")
	public ResponseEntity<Void> deletarClientePorId(@PathVariable("id") int id) {
		Cliente cliente = clienteService.listarClientePorId(id);
		clienteService.deletarClientePorId(cliente);
		return ResponseEntity.noContent().build();
	}
}
