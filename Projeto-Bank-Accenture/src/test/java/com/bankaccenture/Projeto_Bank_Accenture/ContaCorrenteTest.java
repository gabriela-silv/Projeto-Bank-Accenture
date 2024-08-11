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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
	void testDeletarContaCorrentePorId() {
		doNothing().when(contaCorrenteRepository).deleteById(1);

		String result = contaCorrenteService.deletarContaCorrentePorId(1);

		assertEquals("Cliente de id 1 deletada com sucesso", result);
		verify(contaCorrenteRepository, times(1)).deleteById(1);
	}

	@Test
	void testDepositar() {
		when(contaCorrenteRepository.save(any(ContaCorrente.class))).thenReturn(contaCorrente);

		contaCorrenteService.depositar(contaCorrente, BigDecimal.valueOf(500));

		assertEquals(BigDecimal.valueOf(1500), contaCorrente.getContaCorrenteSaldo());
		verify(contaCorrenteRepository, times(1)).save(contaCorrente);
	}

	@Test
	void testSacarComSaldoSuficiente() {
		when(contaCorrenteRepository.save(any(ContaCorrente.class))).thenReturn(contaCorrente);

		contaCorrenteService.sacar(contaCorrente, BigDecimal.valueOf(500));

		assertEquals(BigDecimal.valueOf(500), contaCorrente.getContaCorrenteSaldo());
		verify(contaCorrenteRepository, times(1)).save(contaCorrente);
	}

	@Test
	void testSacarComSaldoInsuficiente() {
		assertThrows(SaldoInsuficienteException.class, () -> {
			contaCorrenteService.sacar(contaCorrente, BigDecimal.valueOf(1500));
		});
	}

	@Test
	void testTransferirComSaldoSuficiente() {

		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.of(contaCorrente));
		when(contaCorrenteRepository.findById(2)).thenReturn(Optional.of(contaDestino));
		when(contaCorrenteRepository.save(any(ContaCorrente.class))).thenReturn(contaCorrente);

		contaCorrenteService.transferir(contaCorrente, contaDestino, BigDecimal.valueOf(500));

		assertEquals(BigDecimal.valueOf(500), contaCorrente.getContaCorrenteSaldo());
		assertEquals(BigDecimal.valueOf(1000), contaDestino.getContaCorrenteSaldo());
		verify(contaCorrenteRepository, times(2)).save(any(ContaCorrente.class));
	}

	@Test
	void testTransferirComSaldoInsuficiente() {

		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.of(contaCorrente));
		when(contaCorrenteRepository.findById(2)).thenReturn(Optional.of(contaDestino));

		assertThrows(SaldoInsuficienteException.class, () -> {
			contaCorrenteService.transferir(contaCorrente, contaDestino, BigDecimal.valueOf(1500));
		});

	}

	@Test
	void testTransferirComContaOrigemNaoEncontrada() {

		when(contaCorrenteRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ContaCorrenteNaoEncontradaException.class, () -> {
			contaCorrenteService.transferir(contaCorrente, contaDestino, BigDecimal.valueOf(500));
		});

	}
}
