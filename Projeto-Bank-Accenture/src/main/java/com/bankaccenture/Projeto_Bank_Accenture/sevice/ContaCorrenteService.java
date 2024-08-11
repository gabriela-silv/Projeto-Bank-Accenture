package com.bankaccenture.Projeto_Bank_Accenture.sevice;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.exception.ContaCorrenteNaoEncontradaException;
import com.bankaccenture.Projeto_Bank_Accenture.exception.SaldoInsuficienteException;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
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
	public String deletarContaCorrentePorId(int idContaCorrente) {
		
		contaCorrenteRepository.deleteById(idContaCorrente);
		return "Cliente de id " + idContaCorrente + " deletada com sucesso";
	}
	

	@Transactional(readOnly = false)
	public void depositar(ContaCorrente contaCorrente, BigDecimal valor) {
		contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo().add(valor));
		contaCorrenteRepository.save(contaCorrente);
	}

	@Transactional(readOnly = false)
	public void sacar(ContaCorrente contaCorrente, BigDecimal valor) {
		if (contaCorrente.getContaCorrenteSaldo().compareTo(valor) > 0) {
			contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo().subtract(valor));
			contaCorrenteRepository.save(contaCorrente);
		} else {
			throw new SaldoInsuficienteException(valor, contaCorrente.getContaCorrenteSaldo());
		}
	}

	@Transactional(readOnly = false)
	public void transferir(ContaCorrente contaCorrenteOrigem, ContaCorrente contaCorrenteDestino, BigDecimal valor) {
	    
		if (contaCorrenteRepository.findById(contaCorrenteOrigem.getIdContaCorrente()).isEmpty()
				|| contaCorrenteRepository.findById(contaCorrenteDestino.getIdContaCorrente()).isEmpty()
				) {
	        throw new ContaCorrenteNaoEncontradaException("Conta corrente de origem não encontrada.");
	    }
	    if (contaCorrenteOrigem.getContaCorrenteSaldo().compareTo(valor) < 0) {
	        throw new SaldoInsuficienteException(valor, contaCorrenteOrigem.getContaCorrenteSaldo());
	    }
	    contaCorrenteOrigem.setContaCorrenteSaldo(contaCorrenteOrigem.getContaCorrenteSaldo().subtract(valor));
	    contaCorrenteDestino.setContaCorrenteSaldo(contaCorrenteDestino.getContaCorrenteSaldo().add(valor));
	    contaCorrenteRepository.save(contaCorrenteOrigem);
	    contaCorrenteRepository.save(contaCorrenteDestino);
	}
	
	
}
