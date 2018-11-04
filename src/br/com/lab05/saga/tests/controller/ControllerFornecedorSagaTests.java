/**
 * 
 */
package br.com.lab05.saga.tests.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.comparators.FornecedorComparator;
import br.com.lab05.saga.controller.ControllerFornecedorSaga;
import br.com.lab05.saga.model.Fornecedor;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class ControllerFornecedorSagaTests {

	private Fornecedor fornecedor01;
	private Fornecedor fornecedor02;
	private Fornecedor fornecedor03;
	
	private ControllerFornecedorSaga controle;
	
	@BeforeEach
	void setUp() throws Exception {
		
		controle = new ControllerFornecedorSaga();

		controle.adicionarFornecedor("Matheus","matheus.trajano","83991847043");
		controle.adicionarFornecedor("Dona Inês","dona.ines","1234567890");
		controle.adicionarFornecedor("Seu Olavo","olavo.lanches","0987654321");
	
		controle.adicionarProduto("Matheus","Arroz","Arroz Branco",3.50);
		controle.adicionarProduto("Matheus","X-Frango","Sanduiche de Frango",5.50);
	}

	@Test
	@DisplayName("Testando método de cadastrar fornecedor")
	void testCadastrarFornecedor01() {
		
		assertEquals("Mathias",controle.adicionarFornecedor("Mathias","mathias.trajano","1234567890"));
	}

	@Test
	@DisplayName("Testando cadastro com algum parametro inválido")
	void testCadastrarFornecedor02() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			
			controle.adicionarFornecedor("Mathias","","1234567890");
		});
		
		assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de cadastro com um fornecedor já cadastrado")
	void testCadastroFornecedor03() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.adicionarFornecedor("Mathias","mathias.trajano","1234567890");
			controle.adicionarFornecedor("Mathias","mathias.trajano","1234567890");

		});
		
		assertEquals("Erro no cadastro de fornecedor: fornecedor ja existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de busca de fornecedor")
	void testBuscaFornecedor01() {
		
		controle.adicionarFornecedor("Mathias","mathias.trajano","1234567890");
		
		assertEquals("Mathias - mathias.trajano - 1234567890",controle.buscarFornecedor("Mathias"));
	}
	
	@Test
	@DisplayName("Testando método de busca com fornecedor inexistente")
	void testBuscaFornecedor02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.buscarFornecedor("Joaquim");
		});
		
		assertEquals("Erro na exibicao do fornecedor: fornecedor nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método listar sem ter fornecedores cadastrados")
	void testListarFonecedores01() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.removerFornecedor("Matheus");
			controle.removerFornecedor("Dona Inês");
			controle.removerFornecedor("Seu Olavo");
			controle.listarFornecedores();
		});
		
		assertEquals("Não há fornecedores cadastrados!",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método listar fornecedores")
	void testListarFornecedores02() {
		
		assertEquals("Dona Inês - dona.ines - 1234567890 | Matheus - matheus.trajano - 83991847043 | Seu Olavo - olavo.lanches - 0987654321",controle.listarFornecedores());
	}
	
	@Test
	@DisplayName("Testando método de editar fornecedor com fornecedor inexistente")
	void testEditarFornecedor01() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.editarFornecedor("Joao","email","joao.2018");
		});
		
		assertEquals("Erro na edicao do fornecedor: fornecedor nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de editar fornecedor com atributo inválido")
	void testEditarFornecedor02() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.editarFornecedor("Matheus","","matheus.2018");
		});
		
		assertEquals("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de editar fornecedor com novo dado inválido")
	void testEditarFornecedor03() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.editarFornecedor("Matheus","email","");
		});
		
		assertEquals("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de editar fornecedor fornecendo um atributo que não existe")
	void testEditarFornecedor04() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.editarFornecedor("Matheus","cpf","12345678901");
		});
		
		assertEquals("Erro na edicao do fornecedor: atributo nao existe.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de editar fornecedor")
	void testEditarFornecedor05() {
		
		controle.editarFornecedor("Matheus","email","matheus.2018");
		
		assertEquals("Matheus - matheus.2018 - 83991847043",controle.buscarFornecedor("Matheus"));
	}
	
	@Test
	@DisplayName("Testando método de remover fornecedor")
	void testRemoverFornecedor01() {
		
		assertEquals("Dona Inês",controle.removerFornecedor("Dona Inês"));
	}
	
	@Test
	@DisplayName("Testando método de remover fornecedor com nome inválido")
	void testRemoverFornecedor02() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.removerFornecedor("");
		});
		
		assertEquals("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de remover fornecedor com um fornecedor inexistente")
	void testRemoverFornecedor03() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.removerFornecedor("Mathias");
		});
		
		assertEquals("Impossível remover um fornecedor que não estava cadastrado!",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de adicionar Produto")
	void testAdicionarProduto01() {
		
		assertEquals("Produto cadastrado com sucesso!",controle.adicionarProduto("Matheus","Feijão","Feijão Carioca",2.25));
	}
	
	@Test 
	@DisplayName("Testando método de adicionar produto com fornecedor inválido")
	void testAdicionarProduto02() {
		IllegalArgumentException npe = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.adicionarProduto("","Arroz","Arroz Branco",1.50);
		});
		
		assertEquals("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de adicionar produto com fornecedor inexistente")
	void testAdicionarProduto03() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.adicionarProduto("Joao","Arroz","Arroz Branco",2.25);
		});
		
		assertEquals("Erro no cadastro de produto: fornecedor nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de buscar produto")
	void testBuscarProduto01() {
		
		assertEquals("Arroz - Arroz Branco - R$3,50",controle.buscarProduto("Arroz","Arroz Branco","Matheus"));
	}
	
	@Test
	@DisplayName("Testando método de buscar produto com fornecedor inválido")
	void testBuscarProduto02() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.buscarProduto("Arroz","Arroz Branco","");
		});
		
		assertEquals("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de buscar produto com fornecedor inexistente")
	void testBuscarProduto03() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.buscarProduto("Arroz","Arroz Branco","Mathias");
		});
		
		assertEquals("Erro na exibicao de produto: fornecedor nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de editar produto")
	void testEditarProduto01() {
		
		controle.editarProduto("Arroz","Arroz Branco","Matheus",2.50);
		
		assertEquals("Arroz - Arroz Branco - R$2,50",controle.buscarProduto("Arroz","Arroz Branco","Matheus"));
	}
	
	@Test
	@DisplayName("Testando método de editar produto com fornecedor inválido")
	void testEditarProduto02() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.editarProduto("Arroz","Arroz Branco","",2);
		});
		
		assertEquals("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de editar produto com fornecedor inexistente")
	void testEditarProduto03() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.editarProduto("Arroz","Arroz Branco","Mathias",2);
		});
		
		assertEquals("Erro na edicao de produto: fornecedor nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de exibir produtos de um fornecedor")
	void testExibirProduto01() {
		
		assertEquals("Matheus - Arroz - Arroz Branco - R$3,50 | Matheus - X-Frango - Sanduiche de Frango - R$5,50",controle.exibirProdutos("Matheus"));
	}
	
	@Test
	@DisplayName("Testando método de exibir produtos de um fornecedor inexistente")
	void testExibirProduto02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.exibirProdutos("Math");
		});
		
		assertEquals("Fornecedor não encontrado!",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de exibir todos os produtos do sistema")
	void testExibirProdutos01() {
		
		controle.adicionarProduto("Dona Inês","X-Bacon","Sanduiche de Bacon",4);
		
		assertEquals("Dona Inês - X-Bacon - Sanduiche de Bacon - R$4,00 | Matheus - Arroz - Arroz Branco - R$3,50 | Matheus - X-Frango - Sanduiche de Frango - R$5,50",controle.exibirProdutos());
	}

	@Test
	@DisplayName("Testando metodo de remover produto com fornecedor inválido")
	void testRemoverProduto01() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			controle.removerProduto("Arroz","Arroz Branco","");
		});
		
		assertEquals("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de remover produto com fornecedor inexistente")
	void testRemoverProduto02() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			controle.removerProduto("Arroz","Arroz Branco","Math");
		});
		
		assertEquals("Erro na remocao de produto: fornecedor nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de remover produto")
	void testRemoverProduto03() {
		
		assertEquals("Produto removido com sucesso!",controle.removerProduto("Arroz","Arroz Branco","Matheus"));
	}
	
	
	
	
}
