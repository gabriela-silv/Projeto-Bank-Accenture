package com.bankaccenture.Projeto_Bank_Accenture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import com.bankaccenture.Projeto_Bank_Accenture.enums.TipoOperacao;
import com.bankaccenture.Projeto_Bank_Accenture.event.TransacaoEvent;
import com.bankaccenture.Projeto_Bank_Accenture.exception.CampoObrigatorioException;
import com.bankaccenture.Projeto_Bank_Accenture.exception.ContaCorrenteNaoEncontradaException;
import com.bankaccenture.Projeto_Bank_Accenture.exception.SaldoInsuficienteException;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.model.Agencia;
import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ContaCorrenteRepository;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ContaCorrenteService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ContaCorrenteTest {

	@Mock
	private ContaCorrenteRepository contaCorrenteRepository;

	@Mock
	private ApplicationEventPublisher eventPublisher;

	@InjectMocks
	private ContaCorrenteService contaCorrenteService;

	private ContaCorrente contaCorrente;
	private ContaCorrente contaDestino;
	private Cliente cliente;
	private Agencia agencia;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		cliente = new Cliente();
		cliente.setIdCliente(1);

		agencia = new Agencia();
		agencia.setIdAgencia(1);

		contaCorrente = new ContaCorrente();
		contaCorrente.setIdContaCorrente(1);
		contaCorrente.setContaCorrenteNumero("12345");
		contaCorrente.setIdCliente(cliente);
		contaCorrente.setContaCorrenteSaldo(BigDecimal.valueOf(1000));
		contaCorrente.setIdAgencia(agencia);

		contaDestino = new ContaCorrente();
		contaDestino.setIdContaCorrente(2);
		contaDestino.setContaCorrenteNumero("67890");
		contaDestino.setIdCliente(cliente);
		contaDestino.setContaCorrenteSaldo(BigDecimal.valueOf(500));
		contaDestino.setIdAgencia(agencia);
	}

	@Test
	void testListarContaCorrentes() {

		List<ContaCorrente> contaCorrentes = Arrays.asList(contaCorrente);
		when(contaCorrenteRepository.findAll()).thenReturn(contaCorrentes);

		List<ContaCorrente> result = contaCorrenteService.listarContaCorrentes();

		assertEquals(1, result.size());
		verify(contaCorrenteRepository, times(1)).findAll();
	}

	@Test
	void testListarContaCorrentePorId() {
		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.of(contaCorrente));

		ContaCorrente result = contaCorrenteService.listarContaCorrentePorId(1);

		assertNotNull(result);
		assertEquals(contaCorrente.getContaCorrenteNumero(), result.getContaCorrenteNumero());
		verify(contaCorrenteRepository, times(1)).findById(1);
	}

	@Test
	void testCadastrarContaCorrente() {
		when(contaCorrenteRepository.save(any(ContaCorrente.class))).thenReturn(contaCorrente);

		ContaCorrente result = contaCorrenteService.cadastrarContaCorrente(contaCorrente);

		assertNotNull(result);
		assertEquals(contaCorrente.getContaCorrenteNumero(), result.getContaCorrenteNumero());
		verify(contaCorrenteRepository, times(1)).save(contaCorrente);
	}

	@Test
	void testAtualizarContaCorrente() {
		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.of(contaCorrente));
		contaCorrenteService.atualizarContaCorrente(contaDestino, contaCorrente.getIdContaCorrente());

		assertEquals(contaCorrente.getContaCorrenteNumero(), contaDestino.getContaCorrenteNumero());
		verify(contaCorrenteRepository).save(contaCorrente);

	}

	@Test
	void testDepositar() {

		BigDecimal valorDeposito = BigDecimal.valueOf(500);
		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.of(contaCorrente));

		contaCorrenteService.depositar(contaCorrente.getIdContaCorrente(), valorDeposito);

		assertEquals(BigDecimal.valueOf(1500), contaCorrente.getContaCorrenteSaldo());
		verify(contaCorrenteRepository, times(1)).save(any(ContaCorrente.class));
		verify(eventPublisher, times(1)).publishEvent(any(TransacaoEvent.class));

		ArgumentCaptor<TransacaoEvent> eventCaptor = ArgumentCaptor.forClass(TransacaoEvent.class);
		verify(eventPublisher).publishEvent(eventCaptor.capture());
		TransacaoEvent capturedEvent = eventCaptor.getValue();
		assertEquals(contaCorrente, capturedEvent.getContaCorrente());
		assertEquals(valorDeposito, capturedEvent.getValor());
		assertEquals(TipoOperacao.DEPOSITO, capturedEvent.getTipoOperacao());

	}

	@Test
	void testSacarComSaldoSuficiente() {

		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.of(contaCorrente));

		contaCorrenteService.sacar(contaCorrente.getIdContaCorrente(), BigDecimal.valueOf(500));
		assertEquals(BigDecimal.valueOf(500), contaCorrente.getContaCorrenteSaldo());
		verify(contaCorrenteRepository, times(1)).save(any(ContaCorrente.class));
	}

	@Test
	void testSacarComSaldoInsuficiente() {

		when(contaCorrenteRepository.findById(contaCorrente.getIdContaCorrente()))
				.thenReturn(Optional.of(contaCorrente));

		assertThrows(SaldoInsuficienteException.class, () -> {
			contaCorrenteService.sacar(contaCorrente.getIdContaCorrente(), BigDecimal.valueOf(1500));
		});

	}

	@Test
	void testTransferirComSaldoSuficiente() {

		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.of(contaCorrente));
		when(contaCorrenteRepository.findById(2)).thenReturn(Optional.of(contaDestino));
		when(contaCorrenteRepository.save(any(ContaCorrente.class))).thenReturn(contaCorrente);

		contaCorrenteService.transferir(contaCorrente.getIdContaCorrente(), contaDestino.getIdContaCorrente(),
				BigDecimal.valueOf(500));

		assertEquals(BigDecimal.valueOf(500), contaCorrente.getContaCorrenteSaldo());
		assertEquals(BigDecimal.valueOf(1000), contaDestino.getContaCorrenteSaldo());
		verify(contaCorrenteRepository, times(2)).save(any(ContaCorrente.class));
	}

	@Test
	void testTransferirComSaldoInsuficiente() {

		when(contaCorrenteRepository.findById(contaCorrente.getIdContaCorrente()))
				.thenReturn(Optional.of(contaCorrente));
		when(contaCorrenteRepository.findById(contaDestino.getIdContaCorrente())).thenReturn(Optional.of(contaDestino));

		assertThrows(SaldoInsuficienteException.class, () -> {
			contaCorrenteService.transferir(contaCorrente.getIdContaCorrente(), contaDestino.getIdContaCorrente(),
					BigDecimal.valueOf(1500));
		});

	}

	@Test
	void testTransferirComContaOrigemNaoEncontrada() {

		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ContaCorrenteNaoEncontradaException.class, () -> {
			contaCorrenteService.transferir(contaCorrente.getIdContaCorrente(), contaDestino.getIdContaCorrente(),
					BigDecimal.valueOf(500));
		});
	}

	@Test
	void testValidaCamposAgenciaNula() {
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setContaCorrenteNumero("123456");
		contaCorrente.setIdCliente(cliente);

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		});

		assertEquals("Preencha os campos obrigatórios: Agencia", exception.getMessage());
	}

	@Test
	void testValidaCamposContaCorrenteNumeroVazio() {
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setIdAgencia(agencia);
		contaCorrente.setContaCorrenteNumero("");
		contaCorrente.setIdCliente(cliente);

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		});

		assertEquals("Preencha os campos obrigatórios: Numero da Conta Corrente", exception.getMessage());
	}

	@Test
	void testValidaCamposIDClienteNulo() {
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setIdAgencia(agencia);
		contaCorrente.setContaCorrenteNumero("123456");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		});

		assertEquals("Preencha os campos obrigatórios: Cliente", exception.getMessage());
	}

	@Test
	void testValidaCamposTodosCamposVazios() {
		ContaCorrente contaCorrente = new ContaCorrente();

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			contaCorrenteService.cadastrarContaCorrente(contaCorrente);
		});

		assertEquals("Preencha os campos obrigatórios: Numero da Conta Corrente Agencia Cliente",
				exception.getMessage());
	}

}