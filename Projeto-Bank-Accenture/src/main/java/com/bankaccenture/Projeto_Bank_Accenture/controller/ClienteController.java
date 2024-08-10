package com.bankaccenture.Projeto_Bank_Accenture.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
