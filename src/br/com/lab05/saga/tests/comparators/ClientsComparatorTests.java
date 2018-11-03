/**
 * 
 */
package br.com.lab05.saga.tests.comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.comparators.ClienteComparator;
import br.com.lab05.saga.model.Cliente;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class ClientsComparatorTests {

	private Cliente cliente01;
	private Cliente cliente02;
	private Cliente cliente03;
	
	private ClienteComparator comparador;
	
	@BeforeEach
	void setUp() throws Exception {
		
		cliente01 = new Cliente("12345678901","Mathias","mathias.trajano","LSD");
		cliente02 = new Cliente("23456789034","Klaywert","klaywert.danillo","SACC");
		cliente03 = new Cliente("09876543212","Natan","natan.vinicius","Embeeded");
		
		comparador = new ClienteComparator();
	}

	@Test
	@DisplayName("Testando método de comparação")
	void testCompare() {
		
		assertEquals(1,comparador.compare(cliente01, cliente02));
		assertEquals(0,comparador.compare(cliente02, cliente02));
		assertEquals(1,comparador.compare(cliente03, cliente01));
		assertEquals(-1,comparador.compare(cliente02, cliente03));
		
	}

}
