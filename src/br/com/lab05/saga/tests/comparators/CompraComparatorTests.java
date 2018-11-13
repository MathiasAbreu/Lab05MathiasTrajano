package br.com.lab05.saga.tests.comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.lab05.saga.controller.ControllerSaga;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
class CompraComparatorTests {

	private ControllerSaga controle;
	
	@BeforeEach
	void setUp() throws Exception {
		
		controle = new ControllerSaga();
		
		controle.cadastrarCliente("12345678901","Mathias","mathias.trajano","LSD");
		controle.cadastrarCliente("23456789034","Klaywert","klaywert.danillo","SACC");
		controle.cadastrarCliente("09876543212","Natan","natan.vinicius","Embeeded");
		
		controle.adicionarFornecedor("Dona Ines","ines.lanches","76975478575");
		controle.adicionarFornecedor("Seu Olavo","olavo.silva","1234567890");
		controle.adicionarFornecedor("Lanchonete Amarelinha","lanchonete.amarelinha","35312090");
		
		controle.adicionarProduto("Dona Ines","X-Frango","Sanduiche de frango",4.50);
		controle.adicionarProduto("Lanchonete Amarelinha","X-Infarto","Super X-tudo",7);
		controle.adicionarProduto("Seu Olavo","Coca-cola","Latinha de 250ml",2.50);
		
		controle.adicionarConta("12345678901","Dona Ines","12/11/2017","X-Frango","Sanduiche de frango");
		controle.adicionarConta("09876543212","Seu Olavo","01/01/2018","Coca-cola","Latinha de 250ml");
		controle.adicionarConta("23456789034","Lanchonete Amarelinha","04/05/1999","X-Infarto","Super X-tudo");

	}

	@Test
	@DisplayName("Testando ordenação por cliente")
	void testOrdenarPorCliente() {
		
		controle.ordenarPor("Cliente");
		
		assertEquals("Klaywert, Lanchonete Amarelinha, Super X-tudo, 04/05/1999 | Mathias, Dona Ines, Sanduiche de frango, 12/11/2017 | Natan, Seu Olavo, Latinha de 250ml, 01/01/2018",controle.listarCompras());

	}
	
	@Test
	@DisplayName("Testando ordenação por Fornecedor")
	void testOrdenarPorFornecedor() {
		
		controle.ordenarPor("Fornecedor");
		
		assertEquals("Dona Ines, Mathias, Sanduiche de frango, 12/11/2017 | Lanchonete Amarelinha, Klaywert, Super X-tudo, 04/05/1999 | Seu Olavo, Natan, Latinha de 250ml, 01/01/2018",controle.listarCompras());
	}

	@Test
	@DisplayName("Testando ordenação por data")
	void testOrdenarPorData() {
		
		controle.ordenarPor("Data");
		
		assertEquals("04/05/1999, Klaywert, Lanchonete Amarelinha, Super X-tudo | 12/11/2017, Mathias, Dona Ines, Sanduiche de frango | 01/01/2018, Natan, Seu Olavo, Latinha de 250ml",controle.listarCompras());
	}
}
