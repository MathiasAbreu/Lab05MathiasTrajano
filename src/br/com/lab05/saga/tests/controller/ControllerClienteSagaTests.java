/**
 * 
 */
package br.com.lab05.saga.tests.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.controller.ControllerClienteSaga;
import br.com.lab05.saga.controller.ControllerFornecedorSaga;
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
		controle.cadastrarCliente("13570493267","Wesley","wesley.mateus","LugaNenhum");
		controle.cadastrarCliente("09876543211","Klaywert","klay.souza","LCC03");

		
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
		
		assertEquals("Klaywert - LCC03 - klay.souza | Matheus - SACC - matheus.trajano | Mathias - LSD - mathias.trajano | Wesley - LugaNenhum - wesley.mateus",controle.listarClientes());
	}
	
	@Test
	@DisplayName("Testando método sem ter clientes cadastrados")
	void testListarClientes02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.removerCliente("12345678901");
			controle.removerCliente("13570493267");
			controle.removerCliente("09876543211");
			
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
	@DisplayName("Testando o metodo remover com um cliente inexistente")
	void testRemoverCliente02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.removerCliente("");
		});
		
		assertEquals("Impossível remover um cliente que não estava cadastrado!",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando o método de listar Compras")
	void testListarCompras01() {
		ControllerFornecedorSaga controlF = new ControllerFornecedorSaga();
		
		controlF.adicionarFornecedor("Matheus","matheus.trajano","83991847043");
		controlF.adicionarFornecedor("Dona Inês","dona.ines","1234567890");
		controlF.adicionarFornecedor("Seu Olavo","olavo.lanches","0987654321");
	
		controlF.adicionarProduto("Matheus","Arroz","Arroz Branco",3.50);
		controlF.adicionarProduto("Seu Olavo","X-Frango","Sanduiche de Frango",5.50);
		controlF.adicionarProduto("Dona Inês","X-Tudo","X-Infarto",5);
		
		controle.adicionarConta("12345678901","Matheus","11/10/2018","Arroz","Arroz Branco",3.50);
		controle.adicionarConta("13570493267","Matheus","15/11/2018","Arroz","Arroz Branco",3.50);
		
		controle.adicionarConta("12345678901","Seu Olavo","12/12/2008","X-Frango","Sanduiche de Frango",5.50);
		
		controle.adicionarConta("09876543211","Dona Inês","18/01/1999","X-Tudo","X-Infarto",5);
		
		controle.ordenarPor("Cliente");
		assertEquals("Klaywert, Dona Inês, X-Infarto, 18/01/1999 | Mathias, Matheus, Arroz Branco, 11/10/2018 | Mathias, Seu Olavo, Sanduiche de Frango, 12/12/2008 | Wesley, Matheus, Arroz Branco, 15/11/2018",controle.listarCompras());
		
		controle.ordenarPor("Fornecedor");
		assertEquals("Dona Inês, Klaywert, X-Infarto, 18/01/1999 | Matheus, Mathias, Arroz Branco, 11/10/2018 | Matheus, Wesley, Arroz Branco, 15/11/2018 | Seu Olavo, Mathias, Sanduiche de Frango, 12/12/2008",controle.listarCompras());
		
		controle.ordenarPor("Data");
		assertEquals("18/01/1999, Klaywert, Dona Inês, X-Infarto | 12/12/2008, Mathias, Seu Olavo, Sanduiche de Frango | 11/10/2018, Mathias, Matheus, Arroz Branco | 15/11/2018, Wesley, Matheus, Arroz Branco",controle.listarCompras());
	}
}
