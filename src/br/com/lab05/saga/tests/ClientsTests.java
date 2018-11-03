/**
 * 
 */
package br.com.lab05.saga.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.model.Cliente;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class ClientsTests {

	private Cliente cliente;
	private Cliente cliente02;
	private Cliente cliente03;
	
	@BeforeEach
	void setUp() throws Exception {
		
		cliente = new Cliente("12345678905","Mathias","mathias.trajano@ccc.ufcg.edu.br","LSD");
		cliente02 = new Cliente("09876543211","Klaywert","mathias.abreu79@hotmail.com","MSI");
		cliente03 = new Cliente("12345678905","Mathias","mathias.abreu79@hotmail.com","ABC");
	}

	@Test
	@DisplayName("Tentando criar um cliente com dados válidos!")
	void testCriarCliente01() {
		
		cliente = new Cliente("12345678910","Mathias","mathias.trajano@ccc.ufcg.edu.br","LSD");
		
		assertEquals("Mathias - LSD - mathias.trajano@ccc.ufcg.edu.br",cliente.toString());
	}

	@Test
	@DisplayName("Tentando criar um cliente com cpf null")
	void testCriarCliente02() {
		RuntimeException npe = assertThrows(RuntimeException.class,() -> {
			
			cliente = new Cliente(null,"Mathias","mathias.trajano@ccc.ufcg.edu.br","LSD");
		});

		assertEquals("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um cliente com nome null")
	void testCriarCliente03() {
		RuntimeException npe = assertThrows(RuntimeException.class,() ->{
			
			cliente = new Cliente("12345678910",null,"mathias.trajano@ccc.ufcg.edu.br","LSD");
		});
		
		assertEquals("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um cliente com email null")
	void testCriarCliente04() {
		RuntimeException npe = assertThrows(RuntimeException.class,() -> {
			
			cliente = new Cliente("12345678910","Mathias",null,"LSD");
		});
		
		assertEquals("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um cliente com local de trabalho null")
	void testCriarCliente05() {
		RuntimeException npe = assertThrows(RuntimeException.class,() -> {
			
			cliente = new Cliente("12345678910","Mathias","mathias.trajano@ccc.ufcg.edu.br",null);
		});
		
		assertEquals("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.",npe.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um cliente com cpf vazio")
	void testCriarCliente06() {
		RuntimeException iae = assertThrows(RuntimeException.class,() -> {
			
			cliente = new Cliente("","Mathias","mathias.trajano@ccc.ufcg.edu.br","LSD");
		});
		
		assertEquals("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um cliente com nome vazio")
	void testCriarCliente07() {
		RuntimeException iae = assertThrows(RuntimeException.class,() -> {
			
			cliente = new Cliente("12345678910","","mathias.trajano@ccc.ufcg.edu.br","LSD");
		});
		
		assertEquals("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um cliente com email vazio")
	void testCriarCliente08() {
		RuntimeException iae = assertThrows(RuntimeException.class,() -> {
			
			cliente = new Cliente("12345678910","Mathias","","LSD");
		});
		
		assertEquals("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Tentando criar um cliente com local de trabalho vazio")
	void testCriarCliente09() {
		RuntimeException iae = assertThrows(RuntimeException.class,() -> {
			
			cliente = new Cliente("12345678910","Mathias","mathias.trajano@ccc.ufcg.edu.br","");
		});
		
		assertEquals("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.",iae.getMessage());
	}
	
	@Test
	@DisplayName("Verificando a funcionalidade do equals().")
	void testEquals() {
		
		assertEquals(true,cliente.equals(cliente03));
		assertEquals(false,cliente.equals(cliente02));
		assertEquals(false,cliente02.equals(cliente));
		assertEquals(true,cliente03.equals(cliente));
	}
	
	@Test
	@DisplayName("Verificando a funcionalidade do toString()")
	void testToString() {
		
		assertEquals("Mathias - LSD - mathias.trajano@ccc.ufcg.edu.br",cliente.toString());
	}
}
