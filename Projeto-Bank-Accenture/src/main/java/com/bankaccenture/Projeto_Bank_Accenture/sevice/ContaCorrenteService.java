package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.commons.validacaoDeDados;
import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.bankaccenture.Projeto_Bank_Accenture.exception.ContaCorrenteNaoEncontradaException;
import com.bankaccenture.Projeto_Bank_Accenture.exception.SaldoInsuficienteException;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	private validacaoDeDados validacaoDeDados = new validacaoDeDados();
	@Autowired
	private ExtratoService extratoService;

	@Transactional(readOnly = true)
	public List<ContaCorrente> listarContaCorrentes() {
		return contaCorrenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public ContaCorrente listarContaCorrentePorId(int id) {
		return contaCorrenteRepository.findById(id).orElseThrow(
				() -> new ContaCorrenteNaoEncontradaException("Conta corrente com ID " + id + " não encontrada."));
	}

	@Transactional(readOnly = false)
	public ContaCorrente cadastrarContaCorrente(ContaCorrente contaCorrente) {

		validacaoDeDados.validaCampos(contaCorrente);
		return contaCorrenteRepository.save(contaCorrente);
	}

	@Transactional(readOnly = false)
	public ContaCorrente atualizarContaCorrente(ContaCorrente contacon, int id) {

		ContaCorrente cc = this.listarContaCorrentePorId(id);

		cc.setContaCorrenteNumero(contacon.getContaCorrenteNumero());
		cc.setContaCorrenteSaldo(contacon.getContaCorrenteSaldo());
		cc.setIdCliente(contacon.getIdCliente());
		
		System.out.println(cc.toString());

		return contaCorrenteRepository.save(cc);
	}

	@Transactional(readOnly = false)
	public String deletarContaCorrentePorId(ContaCorrente cc) {

		int id = cc.getIdContaCorrente();
		contaCorrenteRepository.deleteById(cc.getIdContaCorrente());
		return "Cliente de id " + id + " deletada com sucesso";
	}

	@Transactional(readOnly = false)
	public void depositar(int idCcontaCorrente, BigDecimal valor) {
		ContaCorrente contaCorrente = listarContaCorrentePorId(idCcontaCorrente);
		contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo().add(valor));
		contaCorrenteRepository.save(contaCorrente);

		extratoService.cadastrarExtrato(contaCorrente, valor, TipoOperacao.DEPOSITO);
	}

	@Transactional(readOnly = false)
	public void sacar(int idContaCorrente, BigDecimal valor) {
		ContaCorrente contaCorrente = listarContaCorrentePorId(idContaCorrente);
		if (contaCorrente.getContaCorrenteSaldo().compareTo(valor) < 0) {
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}

		contaCorrente.setContaCorrenteSaldo(contaCorrente.getContaCorrenteSaldo().subtract(valor));
		contaCorrenteRepository.save(contaCorrente);
		extratoService.cadastrarExtrato(contaCorrente, valor, TipoOperacao.SAQUE);
	}

	@Transactional(readOnly = false)
	public void transferir(int idContaCorrenteOrigem, int idContaCorrenteDestino, BigDecimal valor) {
		ContaCorrente contaCorrenteOrigem = listarContaCorrentePorId(idContaCorrenteOrigem);
		ContaCorrente contaCorrenteDestino = listarContaCorrentePorId(idContaCorrenteDestino);

		if (contaCorrenteOrigem.getContaCorrenteSaldo().compareTo(valor) < 0) {
			throw new SaldoInsuficienteException(valor, contaCorrenteOrigem.getContaCorrenteSaldo());
		}

		contaCorrenteOrigem.setContaCorrenteSaldo(contaCorrenteOrigem.getContaCorrenteSaldo().subtract(valor));
		contaCorrenteRepository.save(contaCorrenteOrigem);

		contaCorrenteDestino.setContaCorrenteSaldo(contaCorrenteDestino.getContaCorrenteSaldo().add(valor));
		contaCorrenteRepository.save(contaCorrenteDestino);

		extratoService.cadastrarExtrato(contaCorrenteOrigem, valor.multiply(BigDecimal.valueOf(-1)),
				TipoOperacao.TRANSFERENCIA);
		extratoService.cadastrarExtrato(contaCorrenteDestino, valor, TipoOperacao.TRANSFERENCIA);
	}

}