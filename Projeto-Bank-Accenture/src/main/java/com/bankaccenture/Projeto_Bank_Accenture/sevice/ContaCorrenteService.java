package com.bankaccenture.Projeto_Bank_Accenture.sevice;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public ContaCorrente listarContaCorrentePorId(Long id) {
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
		Long id = contaCorrente.getIdContaCorrente();
		contaCorrenteRepository.deleteById(contaCorrente.getIdContaCorrente());
		return "Conta de id " + id + " deletada com sucesso";
	}

	
	
	
}

