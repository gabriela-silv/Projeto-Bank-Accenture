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

import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ClienteService;


@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	//Listar todos os clientes
	@GetMapping("/clientes")
	private List<Cliente> listarClientes() 
	{
		return clienteService.listarClientes();
	}
	
	@GetMapping("/cliente/{id}")
	private Cliente getClientePorId(@PathVariable("id") int id) 
	{
	    return clienteService.listarClientePorId(id);
	}
	
	@GetMapping("/cliente/{cpf}")
	private Cliente getClientePorCpf(@PathVariable("cpf") String cpf) 
	{
	    return clienteService.listarClientePorCPF(cpf);
	}
	
	//Inserir clientes
	@PostMapping("/cliente-inserir")
	private int salvarCliente(@RequestBody Cliente cliente) 
	{
		clienteService.cadastrarCliente(cliente);
		
		return cliente.getIdCliente();
	}
	
	@PutMapping("/cliente-atualizar/{id}")
	private int atualizarAgencia(@RequestBody Cliente cliente,@PathVariable("id") int id) {
		clienteService.atualizarCliente(cliente, id);

		return cliente.getIdCliente();
	}
	
	@DeleteMapping("/cliente-deletar/{id}")
	private void deletarClientePorId(@PathVariable("id") int id) {
		Cliente cliente = clienteService.listarClientePorId(id);

		clienteService.deletarClientePorId(cliente);
	}
	
}
