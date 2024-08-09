package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Extrato listarExtratoPorId(Long id) {
		Extrato extrato = extratoRepository.findById(id).orElse(null);
		return extrato;
	}
	
	@Transactional(readOnly = false)
	public Extrato cadastrarExtrato(Extrato extrato){
		return extratoRepository.save(extrato);
	}
	
	@Transactional(readOnly = false)
	public Extrato atualizarExtrato(Extrato extrato){
		return extratoRepository.save(extrato);
	}
	
	@Transactional(readOnly = false)
	public String deletarExtratoPorId(Extrato extrato) {
		Long id = extrato.getIdExtrato();
		extratoRepository.deleteById(extrato.getIdExtrato());
		return "Extrato de id " + id + " deletado com sucesso";
	}

	
	
	
}
