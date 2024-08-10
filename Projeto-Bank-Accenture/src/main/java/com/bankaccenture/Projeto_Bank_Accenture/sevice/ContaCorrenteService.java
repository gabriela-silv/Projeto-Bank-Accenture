package com.bankaccenture.Projeto_Bank_Accenture.sevice;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.exceptions.ContaCorrenteNaoEncontradaException;
import com.bankaccenture.Projeto_Bank_Accenture.exceptions.SaldoInsuficienteException;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	@Transactional(readOnly = true)
	public List<ContaCorrente> listarContaCorrentes(){
		return contaCorrenteRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public ContaCorrente listarContaCorrentePorId(int id) {
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).orElse(null);
		return contaCorrente;
	}
	
	@Transactional(readOnly = false)
	public ContaCorrente cadastrarContaCorrente(ContaCorrente contaCorrente){
		return contaCorrenteRepository.save(contaCorrente);
	}
	
	@Transactional(readOnly = false)
	public ContaCorrente atualizarContaCorrente(ContaCorrente contaCorrente){
		return contaCorrenteRepository.save(contaCorrente);
	}
	
	@Transactional(readOnly = false)
	public String deletarContaCorrentePorId(ContaCorrente contaCorrente) {
		int id = contaCorrente.getIdContaCorrente();
		contaCorrenteRepository.deleteById(contaCorrente.getIdContaCorrente());
		return "Conta de id " + id + " deletada com sucesso";
	}
	

	@Transactional(readOnly = false)
	public void depositar(ContaCorrente contaCorrente, double valor) {
		contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo() + valor);
		contaCorrenteRepository.save(contaCorrente);
	}

	@Transactional(readOnly = false)
	public void sacar(ContaCorrente contaCorrente, double valor) {
		if (contaCorrente.getContaCorrenteSaldo() >= valor) {
			contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo() - valor);
			contaCorrenteRepository.save(contaCorrente);
		} else {
			throw new SaldoInsuficienteException(valor, contaCorrente.getContaCorrenteSaldo());
		}
	}

	@Transactional(readOnly = false)
	public void transferir(ContaCorrente contaCorrenteOrigem, ContaCorrente contaCorrenteDestino, double valor) {
	    
		if (contaCorrenteRepository.findById(contaCorrenteOrigem.getIdContaCorrente()).isEmpty()
				|| contaCorrenteRepository.findById(contaCorrenteDestino.getIdContaCorrente()).isEmpty()
				) {
	        throw new ContaCorrenteNaoEncontradaException("Conta corrente de origem não encontrada.");
	    }
	    if (contaCorrenteOrigem.getContaCorrenteSaldo() < valor) {
	        throw new SaldoInsuficienteException(valor, contaCorrenteOrigem.getContaCorrenteSaldo());
	    }
	    contaCorrenteOrigem.setContaCorrenteSaldo(contaCorrenteOrigem.getContaCorrenteSaldo() - valor);
	    contaCorrenteDestino.setContaCorrenteSaldo(contaCorrenteDestino.getContaCorrenteSaldo() + valor);
	    contaCorrenteRepository.save(contaCorrenteOrigem);
	    contaCorrenteRepository.save(contaCorrenteDestino);
	}
	
	
}

