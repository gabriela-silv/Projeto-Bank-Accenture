package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.bankaccenture.Projeto_Bank_Accenture.event.TransacaoEvent;
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
        extrato.setDataHoraMovimento(LocalDateTime.now());
        extrato.setOperacao(extrato.getOperacao());
        extrato.setValor(extrato.getValor());
        extrato.setIdContaCorrente(extrato.getIdContaCorrente());
		
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
	public Extrato atualizarExtrato(Extrato extrato, int id) {

		Extrato ext = this.listarExtratoPorId(id);
		
		ext.setDataHoraMovimento(extrato.getDataHoraMovimento());
        ext.setOperacao(extrato.getOperacao());
        ext.setIdContaCorrente(extrato.getIdContaCorrente());
        ext.setValor(extrato.getValor());
        
		
		System.out.println(ext.toString());

		return extratoRepository.save(ext);
	}
	
	@Transactional(readOnly = false)
	public String deletarExtratoPorId(Extrato extrato) {
		int id = extrato.getIdExtrato();
		extratoRepository.deleteById(extrato.getIdExtrato());
		return "Extrato de id " + id + " deletado com sucesso";
	}
	
	@EventListener
    public void handleTransacaoEvent(TransacaoEvent event) {
        Extrato extrato = new Extrato();
        extrato.setIdContaCorrente(event.getContaCorrente());
        extrato.setValor(event.getValor());
        extrato.setOperacao(event.getTipoOperacao());
        extrato.setDataHoraMovimento(LocalDateTime.now());

        extratoRepository.save(extrato);
    }

	@Transactional(readOnly = true)
    public List<Extrato> listarExtratosPorContaCorrente(int idContaCorrente) {
        return extratoRepository.findByIdContaCorrenteIdContaCorrente(idContaCorrente);
    }
	/*
	@Transactional(readOnly = true)
	public List<Extrato> listarExtratosPorClienteEPeriodo(int idCliente, Date dataInicio, Date dataFim) {
	    return extratoRepository.findByIdContaCorrenteIdClienteIdClienteAndDataBetween(idCliente, dataInicio, dataFim);
	}*/
}
