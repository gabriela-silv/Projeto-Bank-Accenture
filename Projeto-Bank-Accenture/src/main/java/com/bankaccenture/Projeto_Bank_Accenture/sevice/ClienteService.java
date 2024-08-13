package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.commons.validacaoDeDados;
import com.bankaccenture.Projeto_Bank_Accenture.exception.CampoObrigatorioException;
import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	private validacaoDeDados validacaoDeDados = new validacaoDeDados();

	@Transactional(readOnly = true)
	public List<Cliente> listarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clienteRepository.findAll().forEach(aluno -> clientes.add(aluno));
		return clientes;
	}

	@Transactional(readOnly = true)
	public Cliente listarClientePorId(int id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		return cliente;
	}

	@Transactional(readOnly = false)
	public Cliente cadastrarCliente(Cliente cliente) throws CampoObrigatorioException {

		validacaoDeDados.validaCampos(cliente);
		return clienteRepository.save(cliente);
	}

	@Transactional(readOnly = false)
	public Cliente atualizarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Transactional(readOnly = false)
	public Cliente atualizarNomeCliente(int id, String novoNome) {
	    Cliente cliente = listarClientePorId(id);
	    cliente.setClienteNome(novoNome);
	    validacaoDeDados.validaCampos(cliente);    
	    return atualizarCliente(cliente);
	}

	@Transactional(readOnly = false)
	public Cliente atualizarTelefoneCliente(int id, String novoTelefone) {
		Cliente cliente = listarClientePorId(id);
	    cliente.setClienteFone(novoTelefone);
	    validacaoDeDados.validaCampos(cliente);    
	    return atualizarCliente(cliente);
	}

	@Transactional(readOnly = false)
	public Cliente atualizarCPFCliente(int id, String novoCPF) {
		Cliente cliente = listarClientePorId(id);
	    cliente.setClienteCPF(novoCPF);
	    validacaoDeDados.validaCampos(cliente);    
	    return atualizarCliente(cliente);
	}

	@Transactional(readOnly = false)
	public String deletarCliente(Cliente cliente) {
		clienteRepository.delete(cliente);;
		return "Cliente deletado com sucesso";
	}
	
	@Transactional(readOnly = false)
	public String deletarClientePorId(int id) {
		clienteRepository.deleteById(id);
		return "Cliente de id " + id + " deletado com sucesso";
	}

}
