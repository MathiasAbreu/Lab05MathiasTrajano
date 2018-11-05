/**
 * 
 */
package br.com.lab05.saga.tests.comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.comparators.ProdutoComparator;
import br.com.lab05.saga.model.ProdutoSimples;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class ProdutsComparatorTests {

	private ProdutoSimples produto01;
	private ProdutoSimples produto02;
	private ProdutoSimples produto03;
	
	private ProdutoComparator comparador;
	
	@BeforeEach
	void setUp() throws Exception {
		
		produto01 = new ProdutoSimples("X-Frango","Sanduiche de frango",4.50);
		produto02 = new ProdutoSimples("X-Infarto","Super X-tudo",7);
		produto03 = new ProdutoSimples("Coca-cola","Latinha de 250ml",2.50);
		
		comparador = new ProdutoComparator();
		
	}

	@Test
	@DisplayName("Testando metodo compare")
	void test() {
		
		assertEquals(-1,comparador.compare(produto01, produto02));
		assertEquals(0,comparador.compare(produto03, produto03));
		assertEquals(1,comparador.compare(produto02, produto03));
		assertEquals(1,comparador.compare(produto01, produto03));
		
	}

}
