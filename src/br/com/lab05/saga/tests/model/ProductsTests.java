package br.com.lab05.saga.tests.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.model.Produto;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class ProductsTests {

	private Produto produto;
	private Produto produto02;
	private Produto produto03;
	
	@BeforeEach
	void setUp() throws Exception {
		
		produto = new Produto("X-Frango","Sanduiche com frango",3.50);
		produto02 = new Produto("X-Frango","Sanduiche com frango",4.50);
		produto03 = new Produto("X-Bacon","Sanduiche de Bacon",5);
		
	}
	@Test
	@DisplayName("Criando um produto com dados corretos")
	void testCadastrarProduto01() {
		
		produto = new Produto("X-Frango","Sanduiche com frango",3.50);
		
		assertEquals("X-Frango - Sanduiche com frango - R$3,50",produto.toString());
	}

	@Test
	@DisplayName("Criando um produto com nome null")
	void testCadastrarProduto02() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			
			produto = new Produto(null,"Sanduiche",5);
		});
		
		assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Criando um produto com nome vazio")
	void testCadastrarProduto03() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			
			produto = new Produto("","Sanduiche",5);
		});
		
		assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Criando um produto com descricao null")
	void testCadastrarProduto04() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			
			produto = new Produto("X-Frango",null,5);
		});
		
		assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Criando um produto com descricao vazia")
	void testCadastrarProduto05() {
		RuntimeException rte = assertThrows(RuntimeException.class,() -> {
			
			produto = new Produto("X-Frango","",5);
		});
		
		assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.",rte.getMessage());
	}
	
	@Test
	@DisplayName("Testando metodo equals() do produto")
	void testEquals() {
		
		assertEquals(true,produto.equals(produto02));
		assertEquals(true,produto02.equals(produto));
		assertEquals(false,produto.equals(produto03));
		assertEquals(false,produto03.equals(produto02));
	}
	
	@Test
	@DisplayName("Testando método toString() do produto")
	void testToString() {
		
		assertEquals("X-Frango - Sanduiche com frango - R$3,50",produto.toString());
	}
}