package com.bankaccenture.Projeto_Bank_Accenture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankaccenture.Projeto_Bank_Accenture.exception.CampoObrigatorioException;
import com.bankaccenture.Projeto_Bank_Accenture.exception.CPFInvalidoException;
import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.repository.ClienteRepository;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ClienteService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClienteTest {

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;

	private Cliente cliente1;
	private Cliente cliente2;

	@BeforeEach
	void setUp() {
		cliente1 = new Cliente();
		cliente1.setIdCliente(1);
		cliente1.setClienteNome("João");
		cliente1.setClienteCPF("63112774094");
		cliente1.setClienteFone("999999999");

		cliente2 = new Cliente();
		cliente2.setIdCliente(2);
		cliente2.setClienteNome("Maria");
		cliente2.setClienteCPF("07569071018");
		cliente2.setClienteFone("888888888");
	}

	@Test
	void testListarClientes() {
		when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

		List<Cliente> clientes = clienteService.listarClientes();

		assertEquals(2, clientes.size());
		verify(clienteRepository, times(1)).findAll();
	}

	@Test
	void testListarClientePorId() {
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente1));

		Cliente cliente = clienteService.listarClientePorId(1);

		assertNotNull(cliente);
		assertEquals("João", cliente.getClienteNome());
		verify(clienteRepository, times(1)).findById(1);
	}

	@Test
	void testCadastrarCliente() {
		clienteService.cadastrarCliente(cliente1);
		verify(clienteRepository, times(1)).save(cliente1);
	}

	@Test
	void testAtualizarCliente() {
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente1));
		clienteService.atualizarCliente(cliente2, cliente1.getIdCliente());

		assertEquals(cliente1.getClienteNome(), cliente2.getClienteNome());
		verify(clienteRepository).save(cliente1);
	}

	@Test
	void testDeletarClientePorId() {
		doNothing().when(clienteRepository).deleteById(cliente1.getIdCliente());

		String result = clienteService.deletarClientePorId(cliente1);

		assertEquals("Cliente de id 1 deletado com sucesso", result);
		verify(clienteRepository, times(1)).deleteById(cliente1.getIdCliente());
	}

	@Test
	void testCadastrarClienteNomeObrigatorio() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(3);
		cliente.setClienteNome("");
		cliente.setClienteCPF("12345678900");
		cliente.setClienteFone("999999999");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			clienteService.cadastrarCliente(cliente);
		});

		assertEquals("Preencha os campos obrigatórios: Nome", exception.getMessage());
	}

	@Test
	void testCadastrarClienteCPFObrigatorio() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(3);
		cliente.setClienteNome("Pedro");
		cliente.setClienteCPF("");
		cliente.setClienteFone("999999999");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			clienteService.cadastrarCliente(cliente);
		});

		assertEquals("Preencha os campos obrigatórios: CPF", exception.getMessage());
	}

	@Test
	void testCadastrarClienteCPFInvalido() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(3);
		cliente.setClienteNome("Pedro");
		cliente.setClienteCPF("123");
		cliente.setClienteFone("999999999");

		Exception exception = assertThrows(CPFInvalidoException.class, () -> {
			clienteService.cadastrarCliente(cliente);
		});

		assertEquals("CPF inválido", exception.getMessage());
	}

	@Test
	void testCadastrarClienteCPFMaiorQue14Caracteres() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(3);
		cliente.setClienteNome("Pedro");
		cliente.setClienteCPF("123456789012345");
		cliente.setClienteFone("999999999");

		Exception exception = assertThrows(CPFInvalidoException.class, () -> {
			clienteService.cadastrarCliente(cliente);
		});

		assertEquals("CPF não pode ter mais de 14 caracteres", exception.getMessage());
	}

	@Test
	void testCadastrarClienteTelefoneObrigatorio() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(3);
		cliente.setClienteNome("Pedro");
		cliente.setClienteCPF("12345678900");
		cliente.setClienteFone("");

		Exception exception = assertThrows(CampoObrigatorioException.class, () -> {
			clienteService.cadastrarCliente(cliente);
		});

		assertEquals("Preencha os campos obrigatórios: Telefone", exception.getMessage());
	}

}
