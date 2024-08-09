package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional(readOnly = true)
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Cliente listarClientePorId(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		return cliente;
	}

	@Transactional(readOnly = false)
	public Cliente cadastrarCliente(Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	@Transactional(readOnly = false)
	public Cliente atualizarCliente(Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	@Transactional(readOnly = false)
	public String deletarClientePorId(Cliente cliente) {
		Long id = cliente.getIdCliente();
		clienteRepository.deleteById(cliente.getIdCliente());
		return "Cliente de id " + id + " deletado com sucesso";
	}

	
	
	
}
