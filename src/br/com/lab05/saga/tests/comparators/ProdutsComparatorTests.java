/**
 * 
 */
package br.com.lab05.saga.tests.comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.comparators.ProdutoComparator;
import br.com.lab05.saga.model.Produto;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class ProdutsComparatorTests {

	private Produto produto01;
	private Produto produto02;
	private Produto produto03;
	
	private ProdutoComparator comparador;
	
	@BeforeEach
	void setUp() throws Exception {
		
		produto01 = new Produto("X-Frango","Sanduiche de frango",4.50);
		produto02 = new Produto("X-Infarto","Super X-tudo",7);
		produto03 = new Produto("Coca-cola","Latinha de 250ml",2.50);
		
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
