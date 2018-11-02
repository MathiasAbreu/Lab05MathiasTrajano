package br.com.lab05.saga.tests;

import static org.junit.jupiter.api.Assertions.*;

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
	
	@Test
	@DisplayName("Criando um produto com dados corretos")
	void testCadastrarProduto01() {
		
		produto = new Produto("X-Frango","Sanduiche com frango",3.50);
		
		assertEquals("X-Frango - Sanduiche com frango - R$3,50",produto.toString());
	}

}