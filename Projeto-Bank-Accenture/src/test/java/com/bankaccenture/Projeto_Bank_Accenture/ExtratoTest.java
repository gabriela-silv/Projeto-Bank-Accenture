package com.bankaccenture.Projeto_Bank_Accenture;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.bankaccenture.Projeto_Bank_Accenture.event.TransacaoEvent;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ExtratoRepository;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ExtratoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExtratoTest {

	@Mock
	private ExtratoRepository extratoRepository;

	@InjectMocks
	private ExtratoService extratoService;

	private ContaCorrente contaCorrente;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		contaCorrente = new ContaCorrente();
		contaCorrente.setIdContaCorrente(1);
		contaCorrente.setContaCorrenteSaldo(BigDecimal.valueOf(1000));
	}

	@Test
	public void testHandleTransacaoEvent() {
		BigDecimal valorOperacao = BigDecimal.valueOf(200);
		TransacaoEvent event = new TransacaoEvent(contaCorrente, valorOperacao, TipoOperacao.DEPOSITO);

		extratoService.handleTransacaoEvent(event);

		ArgumentCaptor<Extrato> extratoCaptor = ArgumentCaptor.forClass(Extrato.class);
		verify(extratoRepository).save(extratoCaptor.capture());

		Extrato capturedExtrato = extratoCaptor.getValue();
		assertEquals(contaCorrente, capturedExtrato.getIdContaCorrente());
		assertEquals(valorOperacao, capturedExtrato.getValor());
		assertEquals(TipoOperacao.DEPOSITO, capturedExtrato.getOperacao());
		assertEquals(LocalDateTime.now().getDayOfMonth(), capturedExtrato.getDataHoraMovimento().getDayOfMonth());
	}

	@Test
	public void testListarExtratosPorContaCorrente() {
		Extrato extrato1 = new Extrato();
		extrato1.setIdExtrato(1);
		extrato1.setIdContaCorrente(contaCorrente);
		extrato1.setValor(BigDecimal.valueOf(200));
		extrato1.setOperacao(TipoOperacao.DEPOSITO);
		extrato1.setDataHoraMovimento(LocalDateTime.now());

		Extrato extrato2 = new Extrato();
		extrato2.setIdExtrato(2);
		extrato2.setIdContaCorrente(contaCorrente);
		extrato2.setValor(BigDecimal.valueOf(300));
		extrato1.setOperacao(TipoOperacao.SAQUE);
		extrato2.setDataHoraMovimento(LocalDateTime.now());

		List<Extrato> extratos = Arrays.asList(extrato1, extrato2);

		when(extratoRepository.findByIdContaCorrenteIdContaCorrente(contaCorrente.getIdContaCorrente()))
				.thenReturn(extratos);

		List<Extrato> result = extratoService.listarExtratosPorContaCorrente(contaCorrente.getIdContaCorrente());

		assertEquals(2, result.size());
		assertEquals(extrato1, result.get(0));
		assertEquals(extrato2, result.get(1));

		verify(extratoRepository, times(1)).findByIdContaCorrenteIdContaCorrente(contaCorrente.getIdContaCorrente());
	}
}
