/**
 * 
 */
package br.com.lab05.saga.tests.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.model.Fornecedor;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class FornecedorsTests {

	private Fornecedor fornecedor;
	private Fornecedor fornecedor02;
	private Fornecedor fornecedor03;
	
	@BeforeEach
	void setUp() throws Exception {
		
		fornecedor = new Fornecedor("Mathias","mathias.trajano","1234567890");
		fornecedor02 = new Fornecedor("Mathias","mathias.abreu","1234567890");
		fornecedor03 = new Fornecedor("João","joao.silva","-098765432");
		
		fornecedor.adicionarProduto("ArrozBranco","Pacote de arroz",3.50);
		fornecedor.adicionarProduto("Farinha","Farinha de Trigo",2.35);
	}

	@Test
	@DisplayName("Tentando criar um fornecedor com parametros válidos")
	void testCadastrarFornecedor01() {
		
		fornecedor = new Fornecedor("Mathias","mathias.trajano","1234567890");
		
		assertEquals("Mathias - mathias.trajano - 1234567890",fornecedor.toString());
	}

	@Test
	@DisplayName("Tentando criar um fornecedor com nome null")
	void testCadastrarFornecedor02() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor = new Fornecedor(null,"mathias.trajano","1234567890");

		});
		
		assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um fornecedor com nome vazio")
	void testCadastrarFornecedor03() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor = new Fornecedor("","mathias.trajano","1234567890");

		});
		
		assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um fornecedor com email null")
	void testCadastrarFornecedor04() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor = new Fornecedor("Mathias",null,"1234567890");

		});
		
		assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um fornecedor com email vazio")
	void testCadastrarFornecedor05() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor = new Fornecedor("Mathias","","1234567890");

		});
		
		assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um fornecedor com telefone null")
	void testCadastrarFornecedor06() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor = new Fornecedor("Mathias","mathias.trajano",null);

		});
		
		assertEquals("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um fornecedor com telefone vazio")
	void testCadastrarFornecedor07() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor = new Fornecedor("Mathias","mathias.trajano","");

		});
		
		assertEquals("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Testando método toString() de fornecedor")
	void testToString() {
		
		assertEquals(true,fornecedor.equals(fornecedor02));
		assertEquals(true,fornecedor02.equals(fornecedor));
		assertEquals(false,fornecedor.equals(fornecedor03));
		assertEquals(false,fornecedor02.equals(fornecedor03));
	}
	
	@Test
	@DisplayName("Testando método adicionar produto")
	void testAdicionaProduto01() {
		
		assertEquals("Produto cadastrado com sucesso!",fornecedor.adicionarProduto("Arroz","Pacote de arroz",3.50));
	}
	
	@Test
	@DisplayName("Testando método adicionando um produto já cadastrado")
	void testAdicionaProduto02() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor.adicionarProduto("Arroz","Pacote de arroz",2.50);
			fornecedor.adicionarProduto("Arroz","Pacote de arroz",3.50);

		});
		
		assertEquals("Erro no cadastro de produto: produto ja existe.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Testando método adicionando um produto com parametro inválido")
	void testAdicionaProduto03() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			fornecedor.adicionarProduto("","Pacote de arroz",3.50);

		});
		
		assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de buscar produto")
	void testBuscarProduto01() {
		
		assertEquals("ArrozBranco - Pacote de arroz - R$3,50",fornecedor.buscarProduto("ArrozBranco","Pacote de arroz").toString());
	}
	
	@Test
	@DisplayName("Testando método de busca com algum dado inválido!")
	void testBuscarProduto02() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			fornecedor.buscarProduto("ArrozBranco","");

		});
		
		assertEquals("descricao nao pode ser vazia ou nula.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de busca com produto inexistente")
	void testBuscarProduto03() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			fornecedor.buscarProduto("Feijão","Feijão carioca");
		});
		
		assertEquals("produto nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de remover produto")
	void testRemoverProduto01() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {

			fornecedor.removerProduto("ArrozBranco","Pacote de arroz");
			fornecedor.buscarProduto("ArrozBranco","Pacote de arroz");
		});
		
		assertEquals("produto nao existe.",npe.getMessage());
		
	}
	
	@Test
	@DisplayName("Testando método de remover produto com parametro inválido")
	void testRemoverProduto02() {
		IllegalArgumentException npe = assertThrows(IllegalArgumentException.class,() -> {

			fornecedor.removerProduto("","Pacote de arroz");

		});
		
		assertEquals("Erro na remocao de produto: nome nao pode ser vazio ou nulo.",npe.getMessage());
		
	}
	
	@Test
	@DisplayName("Testando método de busca com produto inexistente")
	void testRemoverProduto03() {
		NullPointerException npe = assertThrows(NullPointerException.class,() -> {
			
			fornecedor.removerProduto("Feijão","Feijão carioca");
		});
		
		assertEquals("Erro na remocao de produto: produto nao existe.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de listar produtos")
	void testListarProdutos01() {
		
		assertEquals("Mathias - ArrozBranco - Pacote de arroz - R$3,50 | Mathias - Farinha - Farinha de Trigo - R$2,35",fornecedor.listarProdutos());
	}
	
	@Test
	@DisplayName("Testando método de listar sem ter produtos cadastrados")
	void testListarProdutos02() {
		
		assertEquals("",fornecedor02.listarProdutos());

	}
	
	@Test
	@DisplayName("Testando método de editar produto")
	void testEditarProduto01() {
		
		fornecedor.editarProduto("Farinha","Farinha de Trigo",4.40);
		
		assertEquals("Farinha - Farinha de Trigo - R$4,40",fornecedor.buscarProduto("Farinha","Farinha de Trigo").toString());
	}
	
	@Test
	@DisplayName("Testando método de editar produto com parametro inválido")
	void testEditarProduto02() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			fornecedor.editarProduto("","Farinha de Trigo",4.40);
		});
		
		assertEquals("Erro na edicao de produto: nome nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Testando método de editar produto com preço inválido")
	void testEditarProduto03() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,() -> {
			
			fornecedor.editarProduto("Farinha","Farinha de Trigo",-5);
		});
		
		assertEquals("Erro na edicao de produto: preco invalido.",iae.getMessage());
	}
}
