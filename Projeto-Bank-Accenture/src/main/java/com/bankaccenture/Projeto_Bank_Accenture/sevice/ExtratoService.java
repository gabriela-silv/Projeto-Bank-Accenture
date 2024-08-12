package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ExtratoRepository;

@Service
public class ExtratoService {
	
	@Autowired
	private ExtratoRepository extratoRepository;
	
	@Transactional(readOnly = true)
	public List<Extrato> listarExtratos(){
		return extratoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Extrato listarExtratoPorId(int id) {
		Extrato extrato = extratoRepository.findById(id).orElse(null);
		return extrato;
	}
	
	@Transactional(readOnly = false)
	public Extrato cadastrarExtrato(Extrato extrato){		
		return extratoRepository.save(extrato);
	}
	
	@Transactional(readOnly = false)
	public Extrato cadastrarExtrato(ContaCorrente contaCorrente, BigDecimal valor, TipoOperacao tipoOperacao){
		
		Extrato extrato = new Extrato();
        extrato.setDataHoraMovimento(LocalDateTime.now());
        extrato.setOperacao(tipoOperacao);
        extrato.setValor(valor);
        extrato.setIdContaCorrente(contaCorrente);
		
		return extratoRepository.save(extrato);
	}
	
	@Transactional(readOnly = false)
	public Extrato atualizarExtrato(Extrato extrato){
		return extratoRepository.save(extrato);
	}
	
	@Transactional(readOnly = false)
	public String deletarExtrato(Extrato extrato) {
		int id = extrato.getIdExtrato();
		extratoRepository.delete(extrato);
		return "Extrato de id " + id + " deletado com sucesso";
	}
	
	@Transactional(readOnly = false)
	public String deletarExtratoPorId(int id) {
		extratoRepository.deleteById(id);
		return "Extrato de id " + id + " deletado com sucesso";
	}
}
