/**
 * 
 */
package br.com.lab05.saga.tests.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.controller.ControllerClienteSaga;
import br.com.lab05.saga.model.Cliente;
import br.com.lab05.saga.model.Fornecedor;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class ControllerClienteSagaTests {

	private Cliente cliente;
	
	private ControllerClienteSaga controle;
	
	@BeforeEach
	void setUp() throws Exception {
		
		cliente = new Cliente("12345678901","Mathias","mathias.trajano","LSD");
		
		controle = new ControllerClienteSaga();
		controle.cadastrarCliente("12345678901","Mathias","mathias.trajano","LSD");

		
	}

	@Test
	@DisplayName("Testando cadastro com dados válidos")
	void testCadastrar01() {
		
		assertEquals("12345678912",controle.cadastrarCliente("12345678912","Mathias","mathias.trajano","LSD"));
	}

	@Test
	@DisplayName("Testando cadastro com um cliente já cadastrado")
	void testCadastrar02() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			
			controle.cadastrarCliente("12345678901","Mathias","mathias.trajano","LSD");
		});
		
		assertEquals("Erro no cadastro do cliente: cliente ja existe.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Testando cadastro com algum dado inválido")
	void testCadastrar03() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			
			controle.cadastrarCliente("","Mathias","mathias.abreu","LSD");
		});
		
		assertEquals("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Testando metodo de busca de cliente")
	void testBuscarCliente01() {
		
		assertEquals("Mathias - LSD - mathias.trajano",controle.buscarCliente("12345678901"));
	}
	
	@Test
	@DisplayName("Testando metodo de busca com o parameto de busca errado")
	void testBuscarCliente02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.buscarCliente("");
		});
		
		assertEquals("Erro na exibicao do cliente: cliente nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando metodo de editar cliente")
	void testEditarCliente01() {
		
		controle.editarCliente("12345678901","localizacao","SACC");
		
		assertEquals("Mathias - SACC - mathias.trajano",controle.buscarCliente("12345678901").toString());
	}
	
	@Test
	@DisplayName("Testando método com cliente inexistente")
	void testEditarCliente02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.editarCliente("","nome","Matheus");
		});
		
		assertEquals("Erro na edicao do cliente: cliente nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método com algum atributo inválido")
	void testEditarCliente03() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.editarCliente("12345678901","","Matheus");
		});
		
		assertEquals("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método com o novo dado inválido")
	void testEditarCliente04() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.editarCliente("12345678901","nome","");
		});
		
		assertEquals("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de listar clientes")
	void testListarClientes01() {
		
		controle.cadastrarCliente("10987654321","Matheus","matheus.trajano","SACC");
		
		assertEquals("Matheus - SACC - matheus.trajano | Mathias - LSD - mathias.trajano",controle.listarClientes());
	}
	
	@Test
	@DisplayName("Testando método sem ter clientes cadastrados")
	void testListarClientes02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.removerCliente("12345678901");
			controle.listarClientes();
		});
		
		assertEquals("Não há clientes cadastrados no sistema!",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando o remover cliente")
	void testRemoverCliente01() {
		
		assertEquals("12345678901",controle.removerCliente("12345678901"));
	}
	
	@Test
	@DisplayName("Testando o metodo remover com um clinte inexistente")
	void testRemoverCliente02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.removerCliente("");
		});
		
		assertEquals("Impossível remover um cliente que não estava cadastrado!",npe.getMessage());
	}
}
