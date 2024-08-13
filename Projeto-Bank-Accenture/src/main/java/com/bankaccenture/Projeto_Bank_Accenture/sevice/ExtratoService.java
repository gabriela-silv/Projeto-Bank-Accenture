package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bankaccenture.Projeto_Bank_Accenture.events.ContaCorrenteTransacoesEvent;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrenteTransacoes;
import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ContaCorrenteTransacoesRepository;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ExtratoRepository;

@Service
public class ExtratoService {
	
	@Autowired
	private ExtratoRepository extratoRepository;
	
	private final ContaCorrenteTransacoesRepository contaCorrenteTransacoesRepository;

    @Autowired
    public ExtratoService(ExtratoRepository extratoRepository, ContaCorrenteTransacoesRepository contaCorrenteTransacoesRepository) {
        this.extratoRepository = extratoRepository;
        this.contaCorrenteTransacoesRepository = contaCorrenteTransacoesRepository;
    }
	
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
	
    @EventListener
    @Transactional(readOnly = false)
    public void handleContaCorrenteOperacaoEvent(ContaCorrenteTransacoesEvent event) {
        Extrato extrato = new Extrato();
        extrato.setDataHoraMovimento(LocalDateTime.now());
        extrato.setOperacao(event.getTipoOperacao());
        extrato.setValor(event.getValor());
        extrato.setIdContaCorrente(event.getContaCorrente());
        extratoRepository.save(extrato);
        
        ContaCorrenteTransacoes eventEntity = new ContaCorrenteTransacoes(event.getContaCorrente(), event.getValor(), event.getTipoOperacao());
        contaCorrenteTransacoesRepository.save(eventEntity);
    }
	
	@Transactional(readOnly = false)
	public Extrato atualizarExtrato(Extrato extrato){
		return extratoRepository.save(extrato);
	}
	
	@Transactional(readOnly = false)
	public String deletarExtratoPorId(Extrato extrato) {
		int id = extrato.getIdExtrato();
		extratoRepository.deleteById(extrato.getIdExtrato());
		return "Extrato de id " + id + " deletado com sucesso";
	}
	
	@Transactional(readOnly = true)
    public List<Extrato> listarExtratosPorContaCorrente(int idContaCorrente) {
        return null;
    }
    
	   
}
