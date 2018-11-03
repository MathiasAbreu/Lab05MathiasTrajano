/**
 * 
 */
package br.com.lab05.saga.tests.comparators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.comparators.FornecedorComparator;
import br.com.lab05.saga.model.Fornecedor;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class FornecedorsComparatorTests {

	private Fornecedor fornecedor01;
	private Fornecedor fornecedor02;
	private Fornecedor fornecedor03;
	
	private FornecedorComparator comparador;
	
	@BeforeEach
	void setUp() throws Exception {
		
		fornecedor01 = new Fornecedor("Dona Ines","ines.lanches","76975478575");
		fornecedor02 = new Fornecedor("Seu Olavo","olavo.silva","1234567890");
		fornecedor03 = new Fornecedor("Lanchonete Amarelinha","lanchonete.amarelinha","35312090");
		
		comparador = new FornecedorComparator();
	}

	@Test
	void testCompare() {
		
		assertEquals(-1,comparador.compare(fornecedor01, fornecedor02));
		assertEquals(0,comparador.compare(fornecedor02, fornecedor02));
		assertEquals(1,comparador.compare(fornecedor02, fornecedor01));
		assertEquals(-1,comparador.compare(fornecedor03, fornecedor02));
	}

}
