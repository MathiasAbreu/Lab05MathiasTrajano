package br.com.lab05.saga.facade;

import br.com.lab05.saga.controller.ControllerClienteSaga;
import br.com.lab05.saga.controller.ControllerFornecedorSaga;
import br.com.lab05.saga.model.Cliente;
import br.com.lab05.saga.model.Combo;
import br.com.lab05.saga.model.Fornecedor;
import br.com.lab05.saga.model.ProdutoSimples;
import easyaccept.EasyAccept;

/**
 * Representação da classe de fachada do cliente.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class SagaFacade {

	/**
	 * Instância da classe de controle de clientes.
	 */
	private ControllerClienteSaga controleCliente;
	
	/**
	 * Instância da classe de controle de fornecedores.
	 */
	private ControllerFornecedorSaga controleFornecedor;
	
	/**
	 * Construtor que inicia a classe e instancia o controle de clientes.
	 */
	public SagaFacade() {
		
		controleCliente = new ControllerClienteSaga();
		controleFornecedor = new ControllerFornecedorSaga();
	}
	
	/**
	 * Método que cadastra um novo {@link Cliente}.
	 * 
	 * @param cpf cpf do cliente
	 * @param nome nome do cliente
	 * @param email email do cliente
	 * @param localizacao local de trabalho do cliente
	 * 
	 * @return Retorna mensagem de confimação ou não do cadastro do novo cliente.
	 * 
	 * @throws RuntimeException Este método recebe exceções que podem ser ocasionadas nas classes de controle e de cliente.
	 */
	public String adicionaCliente(String cpf,String nome,String email,String localizacao) throws RuntimeException {
		
		return controleCliente.cadastrarCliente(cpf, nome, email, localizacao);
	}
	
	/**
	 * Método que cadastra um novo {@link Fornecedor}.
	 * 
	 * @param nome nome do fornecedor
	 * @param email email do fornecedor
	 * @param telefone telefone do fornecedor
	 * 
	 * @return Retorna uma mensagem de confirmação da operação.
	 * 
	 * @throws RuntimeException Pode gerar várias exceções, desde a criação do novo fornecedor, até o momento 
	 * de sua inserção no sistema de armazenamento do sistema. Todas podem ser tratadas utilizando sua classe 
	 * mãe {@link RuntimeException}.
	 */
	public String adicionaFornecedor(String nome,String email, String telefone) throws RuntimeException {
		
		return controleFornecedor.adicionarFornecedor(nome, email, telefone);
	}
	
	/**
	 * Método que cadastra um novo {@link ProdutoSimples}.
	 * 
	 * @param fornecedor fornecedor do produto
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param preco preço do produto
	 * 
	 * @return Repassa o retorno advindo de métodos anteriores a esse.
	 * 
	 * @throws RuntimeException Gera exceções que possem ser causadas pela ṕassagem de parâmetros inválidos.
	 */
	public String adicionaProduto(String fornecedor,String nome,String descricao,double preco) throws RuntimeException{
		
		return controleFornecedor.adicionarProduto(fornecedor,nome,descricao,preco);
	}
	
	/**
	 * Método que cadastra um novo {@link Combo}.
	 * 
	 * @param fornecedor fornecedor do combo
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fator fator de desconto do combo
	 * @param produtos produtos que compõem o combo
	 * 
	 */
	public void adicionaCombo(String fornecedor,String nome,String descricao,double fator,String produtos) {
		
		controleFornecedor.adicionarCombo(fornecedor,nome,descricao,fator,produtos);
		
	}
	
	/**
	 * Método que busca algum cliente no sistema.
	 * 
	 * @param cpf cpf do cliente
	 * 
	 * @return Retorna o cliente ou uma mensagem de erro na operação.
	 */
	public String exibeCliente(String cpf) {
		
		return controleCliente.buscarCliente(cpf);
	}
	
	/**
	 * Método que busca algum fornecedor no sistema.
	 * 
	 * @param nome nome do fornecedor
	 * 
	 * @return Retorna uma representação do fornecedor.
	 * 
	 * @throws RuntimeException Essa exceção é gerada caso haja a tentativa de busca de um fornece
	 * dor inexistente no sistema.
	 */
	public String exibeFornecedor(String nome) throws RuntimeException {
		
		return controleFornecedor.buscarFornecedor(nome);
	}
	
	/**
	 * Método que busca algum produto de determinado fornecedor.
	 * 
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param fornecedor fornecedor do produto
	 * 
	 * @return Retorna a representação do produto, em String.
	 */
	public String exibeProduto(String nome,String descricao, String fornecedor) {
		
		return controleFornecedor.buscarProduto(nome,descricao,fornecedor);
	}
		
	/**
	 * Método que exibe todos os produtos de um determinado fornecedor.
	 * 
	 * @param fornecedor fornecedor em questão
	 * 
	 * @return Retorna uma representação de todos os produtos de um fornecedor.
	 */
	public String exibeProdutosFornecedor(String fornecedor) {
		
		return controleFornecedor.exibirProdutos(fornecedor);
	}
	
	/**
	 * Método que exibe todos os produtos de todos os fornecedores do sistema, em ordem alfabética.
	 * 
	 * @return Retorna uma representação de todos os produtos.
	 */
	public String exibeProdutos() {
		
		return controleFornecedor.exibirProdutos();
	}
	
	/**
	 * Método que retorna todos os clientes cadastrados no sistema, em ordem alfabética.
	 * 
	 * @return Retorna todos os clientes do sistema.
	 */
	public String exibeClientes() {
		
		return controleCliente.listarClientes();
	}
	
	/**
	 * Método que retorna todos os fornecedores cadastrados no sistema, em ordem alfabética.
	 * 
	 * @return Retorna todos os fornecedores do sistema.
	 */
	public String exibeFornecedores() {
		
		return controleFornecedor.listarFornecedores();
	}
	
	/**
	 * Método que edita dados de um certo cliente.
	 * 
	 * @param cpf cpf do cliente a ser editado
	 * @param atributo dado a ser modificado 
	 * @param novoValor valor de representação do dado a ser editado
	 * 
	 * @throws RuntimeException Este método pode receber exceções advindos das classes de controle e cliente.
	 */
	public void editaCliente(String cpf,String atributo,String novoValor) throws RuntimeException {
		
		controleCliente.editarCliente(cpf,atributo,novoValor);
	}
	
	/**
	 * Método que edita dados de um certo fornecedor.
	 * 
	 * @param nome nome do fornecedor a ser editado
	 * @param atributo dado a ser alterado
	 * @param novoValor valor de representação do dado a ser editado
	 * 
	 * @return Retorna uma mensagem de êxito do processo de edição do fornecedor.
	 * 
	 * @throws RuntimeException Este método pode receber exceções advindas das classes {@link ControllerFornecedorSaga} e {@link Fornecedor}.
	 */
	public String editaFornecedor(String nome,String atributo,String novoValor) throws RuntimeException {
		
		return controleFornecedor.editarFornecedor(nome, atributo, novoValor);
	}
	
	/**
	 * Método que o preço de um determinado produto.
	 * 
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param fornecedor fornecedor do produto
	 * @param novoPreco novo preço do produto
	 * 
	 * @return Retorna uma mensagem confirmando a operação.
	 */
	public String editaProduto(String nome, String descricao, String fornecedor, double novoPreco) {
		
		return controleFornecedor.editarProduto(nome,descricao,fornecedor,novoPreco);
	}
	
	/**
	 * Método que edita um combo, devido as limitações impostas, somente o fator de desconto pode ser alterado.
	 * 
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fornecedor fornecedor do combo
	 * @param novoFator novo fator de desconto do combo
	 * 
	 */
	public void editaCombo(String nome,String descricao,String fornecedor,double novoFator) {
		
		controleFornecedor.editarCombo(nome,descricao,fornecedor,novoFator);
	}
	
	/**
	 * Método que remove determinado cliente do sistema.
	 * 
	 * @param cpf cpf do cliente a ser removido
	 * 
	 * @return Mensagem de confirmação ou não da remoção do cliente.
	 * 
	 * @throws NullPointerException Erro gerado caso houver uma tentativa de remover um cliente que não exista no sistema.
	 */
	public String removeCliente(String cpf) {
		
		return controleCliente.removerCliente(cpf);
	}
	
	/**
	 * Método que remove determinado fornecedor do sistema.
	 * 
	 * @param nome nome do fornecedor a ser removido
	 * 
	 * @return Mensagem de confirmação ou não da remoção do cliente.
	 * 
	 * @throws NullPointerException Erro gerado caso houver uma tentativa de remover um fornecedor que não exista no sistema.
	 */
	public String removeFornecedor(String nome) {
		
		return controleFornecedor.removerFornecedor(nome);
	}
	
	/**
	 * Método que remove um determinado produto de um determinado fornecedor.
	 * 
	 * @param nome nome do produto a ser removido
	 * @param descricao descrição do produto a ser removido
	 * @param fornecedor fornecedor do produto
	 * 
	 * @return Retorna uma mensagem confirmando a operação.
	 */
	public String removeProduto(String nome,String descricao,String fornecedor) {
		
		return controleFornecedor.removerProduto(nome,descricao,fornecedor);
	}
	
	public static void main(String[] args) {
		
		args = new String[] {"br.com.lab05.saga.facade.SagaFacade",
							 "src/br/com/lab05/saga/easyAccept/use_case_1.txt",
							 "src/br/com/lab05/saga/easyAccept/use_case_2.txt",
							 "src/br/com/lab05/saga/easyAccept/use_case_3.txt",
							 "src/br/com/lab05/saga/easyAccept/use_case_4.txt",
							 "src/br/com/lab05/saga/easyAccept/use_case_5.txt"};
		EasyAccept.main(args);
	}
}