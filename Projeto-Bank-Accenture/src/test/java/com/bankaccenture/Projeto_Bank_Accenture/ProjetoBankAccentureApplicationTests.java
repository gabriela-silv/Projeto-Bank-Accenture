package com.bankaccenture.Projeto_Bank_Accenture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankaccenture.Projeto_Bank_Accenture.exception.ContaCorrenteNaoEncontradaException;
import com.bankaccenture.Projeto_Bank_Accenture.model.Agencia;
import com.bankaccenture.Projeto_Bank_Accenture.model.Cliente;
import com.bankaccenture.Projeto_Bank_Accenture.model.ContaCorrente;
import com.bankaccenture.Projeto_Bank_Accenture.model.Extrato;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ContaCorrenteService;
import com.bankaccenture.Projeto_Bank_Accenture.sevice.ExtratoService;

@SpringBootTest
class ProjetoBankAccentureApplicationTests {
	
	@Autowired
    private ContaCorrenteService contaCorrenteService;
	
	@Autowired
    private ExtratoService extratoService;
	
	

	 @Test
	    void deveCadastrarContaCorrenteComSucesso() {
			 
			Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        assertNotNull(contaCorrenteSalva);
	        assertEquals(contaCorrente.getContaCorrenteNumero(), contaCorrenteSalva.getContaCorrenteNumero());

	    }

	    @Test
	    void deveConsultarContaExistente() {
	    	
	    	Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        ContaCorrente contaCorrenteConsultada = contaCorrenteService.listarContaCorrentePorId(contaCorrenteSalva.getIdContaCorrente());
	        assertNotNull(contaCorrenteConsultada);
	        assertEquals(contaCorrente.getContaCorrenteNumero(), contaCorrenteConsultada.getContaCorrenteNumero());

	    }

	    
	    @Test
	    void deveAtualizarContaCorrenteComSucesso() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        contaCorrenteSalva.setContaCorrenteSaldo(2000);
	        ContaCorrente contaCorrenteAtualizada = contaCorrenteService.atualizarContaCorrente(contaCorrenteSalva);
	        assertNotNull(contaCorrenteAtualizada);
	        assertEquals(2000, contaCorrenteAtualizada.getContaCorrenteSaldo());
	    }
	    
	    @Test
	    void deveDeletarContaCorrenteComSucesso() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        String mensagem = contaCorrenteService.deletarContaCorrentePorId(contaCorrenteSalva);
	        assertEquals("Conta de id " + contaCorrenteSalva.getIdContaCorrente() + " deletada com sucesso", mensagem);
	        ContaCorrente contaCorrenteConsultada = contaCorrenteService.listarContaCorrentePorId(contaCorrenteSalva.getIdContaCorrente());
	        assertNull(contaCorrenteConsultada);
	    }
	    
	    @Test
	    void deveFazerDepositoComSucesso() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        contaCorrenteService.depositar(contaCorrenteSalva, 500);
	        assertEquals(500, contaCorrenteSalva.getContaCorrenteSaldo());
	    }

	    @Test
	    void deveFazerSaqueComSucesso() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        contaCorrenteService.depositar(contaCorrente, 1000);
	        contaCorrenteService.sacar(contaCorrenteSalva, 200);
	        assertEquals(800, contaCorrenteSalva.getContaCorrenteSaldo());
	    }

	    @Test
	    void deveConsultarDadosDaConta() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        ContaCorrente contaCorrenteConsultada = contaCorrenteService.listarContaCorrentePorId(contaCorrenteSalva.getIdContaCorrente());
	        assertNotNull(contaCorrenteConsultada);
	        assertEquals(contaCorrente.getContaCorrenteNumero(), contaCorrenteConsultada.getContaCorrenteNumero());
	    }

	    @Test
	    void deveFazerTransferenciaEntreContasComSucesso() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrente1Salva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);
	        
	        contaCorrenteService.depositar(contaCorrente1Salva, 1000);
	        
	        Cliente cliente2 = new Cliente();
	        Agencia agencia2 = new Agencia();
	        ContaCorrente contaCorrente2 = new ContaCorrente();
	        ContaCorrente contaCorrente2Salva = contaCorrenteService.cadastrarContaCorrente(contaCorrente2);
        

	        contaCorrenteService.transferir(contaCorrente1Salva, contaCorrente2Salva, 200);
	        assertEquals(800, contaCorrente1Salva.getContaCorrenteSaldo());
	        assertEquals(200, contaCorrente2Salva.getContaCorrenteSaldo());
	    }

	    @Test
	    void deveLancarExcecaoQuandoContaDestinoNaoExistir() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrente1Salva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);

	        assertThrows(ContaCorrenteNaoEncontradaException.class, () -> {
	            contaCorrenteService.transferir(contaCorrente1Salva, new ContaCorrente(), 200);
	        });
	    }

	    @Test
	    void deveExibirExtratoDeConta() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);

	        Extrato extrato = extratoService.exibirExtrato(contaCorrenteSalva);
	        assertNotNull(extrato);
	        assertEquals(contaCorrenteSalva.getContaCorrenteNumero(), extrato.getIdContaCorrente());
	    }

	    @Test
	    void deveRecalcularSaldoDeCliente() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);

	        contaCorrenteService.depositar(contaCorrenteSalva, 500);
	        contaCorrenteService.sacar(contaCorrenteSalva, 200);

	        assertEquals(300, contaCorrenteSalva.getContaCorrenteSaldo());
	    }

	    @Test
	    void deveVerificarSeCadastroDeContaFoiExecutadoComSucesso() {
	        Cliente cliente = new Cliente();
	        Agencia agencia = new Agencia();
	        ContaCorrente contaCorrente = new ContaCorrente();
	        ContaCorrente contaCorrenteSalva = contaCorrenteService.cadastrarContaCorrente(contaCorrente);

	        assertNotNull(contaCorrenteSalva);
	        assertEquals(contaCorrente.getContaCorrenteNumero(), contaCorrenteSalva.getContaCorrenteNumero());
	    }
	
	
	

}
