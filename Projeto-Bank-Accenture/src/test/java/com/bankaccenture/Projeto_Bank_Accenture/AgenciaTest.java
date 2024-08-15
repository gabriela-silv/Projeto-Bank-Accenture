package com.bankaccenture.Projeto_Bank_Accenture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankaccenture.Projeto_Bank_Accenture.exception.CampoObrigatorioException;
import com.bankaccenture.Projeto_Bank_Accenture.model.Agencia;
import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.repository.AgenciaRepository;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.AgenciaService;

@SpringBootTest
public class AgenciaTest {

	@Mock
	private AgenciaRepository agenciaRepository;

	@InjectMocks
	private AgenciaService agenciaService;

	private Agencia agencia1;
	private Agencia agencia2;
	private Cliente cliente;

	@BeforeEach
	void setUp() {
		cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente.setClienteNome("João");
		cliente.setClienteCPF("12345678900");
		cliente.setClienteFone("999999999");

		agencia1 = new Agencia();
		agencia1.setIdAgencia(1);
		agencia1.setNomeAgencia("Agencia teste");
		agencia1.setEndereco("Rua na rua");
		agencia1.setTelefone("11223344554");

		agencia2 = new Agencia();
		agencia2.setIdAgencia(2);
		agencia2.setNomeAgencia("Teste de Agencia");
		agencia2.setEndereco("Endereço de agencia");
		agencia2.setTelefone("99887766554");

	}

	@Test
	public void testListarAgencias() {

		when(agenciaRepository.findAll()).thenReturn(Arrays.asList(agencia1, agencia2));
		List<Agencia> agencias = agenciaService.listarAgencias();

		assertEquals(2, agencias.size());
		verify(agenciaRepository, times(1)).findAll();
	}

	@Test
	public void testListarAgenciaPorId() {
		when(agenciaRepository.findById(1)).thenReturn(Optional.of(agencia1));

		Agencia agencia = agenciaService.listarAgenciaPorId(1);

		assertNotNull(agencia);
		assertEquals("Agencia teste", agencia.getNomeAgencia());
		verify(agenciaRepository, times(1)).findById(1);
	}

	@Test
	public void testCadastrarAgencia() {
		agenciaService.cadastrarAgencia(agencia1);

		verify(agenciaRepository, times(1)).save(agencia1);
	}

	@Test
	public void testAtualizarAgencia() {
		when(agenciaRepository.findById(1)).thenReturn(Optional.of(agencia1));
		agenciaService.atualizarAgencia(agencia2, agencia1.getIdAgencia());

		assertEquals(agencia1.getNomeAgencia(), agencia2.getNomeAgencia());
		verify(agenciaRepository).save(agencia1);
	}

	@Test
	public void testDeletarAgencia() {
		doNothing().when(agenciaRepository).deleteById(agencia1.getIdAgencia());

		String result = agenciaService.deletarAgenciaPorId(agencia1);

		assertEquals("Agencia de id 1 deletada com sucesso", result);
		verify(agenciaRepository, times(1)).deleteById(agencia1.getIdAgencia());
	}

	@Test
	public void testNomeObrigatorio() {

		Agencia agencia = new Agencia();
		agencia.setIdAgencia(3);
		agencia.setNomeAgencia("");
		agencia.setEndereco("Rua na rua");
		agencia.setTelefone("11223344554");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			agenciaService.cadastrarAgencia(agencia);
		});

		assertEquals("Preencha os campos obrigatórios: Nome", exception.getMessage());

	}

	@Test
	public void testNomeNulo() {

		Agencia agencia = new Agencia();
		agencia.setIdAgencia(3);
		agencia.setEndereco("Rua na rua");
		agencia.setTelefone("11223344554");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			agenciaService.cadastrarAgencia(agencia);
		});

		assertEquals("Preencha os campos obrigatórios: Nome", exception.getMessage());

	}

	@Test
	public void testCampoEnderecoVazio() {
		Agencia agencia = new Agencia();
		agencia.setIdAgencia(3);
		agencia.setNomeAgencia("Nome da Agencia");
		agencia.setEndereco("");
		agencia.setTelefone("11223344554");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			agenciaService.cadastrarAgencia(agencia);
		});

		assertEquals("Preencha os campos obrigatórios: Endereço", exception.getMessage());
	}

	@Test
	public void testCampoTelefoneVazio() {
		Agencia agencia = new Agencia();
		agencia.setIdAgencia(4);
		agencia.setNomeAgencia("Nome da Agencia");
		agencia.setEndereco("Endereço da agencia");
		agencia.setTelefone("");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			agenciaService.cadastrarAgencia(agencia);
		});

		assertEquals("Preencha os campos obrigatórios: Telefone", exception.getMessage());
	}

}
