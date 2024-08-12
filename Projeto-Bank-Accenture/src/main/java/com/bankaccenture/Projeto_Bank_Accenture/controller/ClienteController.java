package com.bankaccenture.Projeto_Bank_Accenture.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//Inserir clientes
	@PostMapping("/cliente-inserir")
	private int salvarCliente(@RequestBody Cliente cliente) 
	{
		clienteService.cadastrarCliente(cliente);
		
		return cliente.getIdCliente();
	}
	
	//Atualizar clientes
	@PutMapping("/cliente-atualizar")
	private int atualizarCliente(@RequestBody Cliente cliente) 
	{
		clienteService.atualizarCliente(cliente);
		
		return cliente.getIdCliente();
	}
	
	//Deletar clientes
	@DeleteMapping("/cliente-deletar")
	private int deletarCliente(@RequestBody Cliente cliente) 
	{
		int id = cliente.getIdCliente();
		clienteService.deletarCliente(cliente);
		return id;
	}
	
	//Deletar clientes por id
	@DeleteMapping("/cliente-deletar-por-id")
	private String deletarClientePorId(int id) 
	{
		return clienteService.deletarClientePorId(id);
	}
	
}
