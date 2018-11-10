package br.com.lab05.saga.controller;

import br.com.lab05.saga.model.Combo;
import br.com.lab05.saga.model.ProdutoSimples;

/**
 * Classe responsável por controlar e repassar os comandos e tarefas para as classes capazes de realizar tais tarefas. 
 * Está classe é responsável também pelo controle principal do sistema.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class ControllerSaga {

	/**
	 * Instância de um controlador de clientes.
	 */
	private ControllerClienteSaga controlerCliente;
	
	/**
	 * Instânica de um controlador de fornecedores.
	 */
	private ControllerFornecedorSaga controlerFornecedor;
	
	/**
	 * Construtor responsável pela instanciação dos controladores de clientes e fornecedores.
	 */
	public ControllerSaga() {
		
		controlerCliente = new ControllerClienteSaga();
		controlerFornecedor = new ControllerFornecedorSaga();
	}

	/**
	 * Método responsável por cadastrar um novo cliente no sistema.
	 * 
	 * @param cpf cpf do cliente
	 * @param nome nome do cliente
	 * @param email email do cliente
	 * @param localizacao local de trabalho do cliente
	 * 
	 * @return Retorna o cpf do cliente recém cadastrado.
	 */
	public String cadastrarCliente(String cpf, String nome, String email, String localizacao) {
		
		return controlerCliente.cadastrarCliente(cpf, nome, email, localizacao);
	}

	/**
	 * Método responsável pelo cadastro de um novo fornecedor no sistema.
	 * 
	 * @param nome nome do fornecedor
	 * @param email email do fornecedor
	 * @param telefone telefone do fornecedor
	 * 
	 * @return Retorna o nome do fornecedor recém cadastrado.
	 */
	public String adicionarFornecedor(String nome, String email, String telefone) {
		
		return controlerFornecedor.adicionarFornecedor(nome, email, telefone);
	}

	/**
	 * Método responsável por cadastrar um novo produto no sistema.
	 * 
	 * @param fornecedor fornecedor do produto
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param preco preço do produto
	 * 
	 * @return Retorna o nome do produto recém cadastrado.
	 */
	public String adicionarProduto(String fornecedor, String nome, String descricao, double preco) {
		
		return controlerFornecedor.adicionarProduto(fornecedor, nome, descricao, preco);
	}

	/**
	 * Método responsável por cadastrar um novo combo no sistema.
	 * 
	 * @param fornecedor fornecedor do combo
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fator fator de desconto do combo
	 * @param produtos produtos que integram o combo
	 */
	public void adicionarCombo(String fornecedor, String nome, String descricao, double fator, String produtos) {
		
		controlerFornecedor.adicionarCombo(fornecedor, nome, descricao, fator, produtos);
		
	}

	/**
	 * Método responsável por cadastrar uma nova compra no sistema.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor fornecedor do produto
	 * @param data data da compra
	 * @param nome_prod nome do produto
	 * @param desc_prod descrição do produto
	 * 
	 * @throws IllegalArgumentException Esssa exceção é gerada caso algum dos dados passados seja nulo ou inválido.
	 * @throws NullPointerException Exceção gerada caso o cliente ou fornecedor não seja localizado no sistema.
	 * @throws RuntimeException Exceção gerada caso algum dos produtos que integram o combo não seja localizado.
	 */
	public void adicionarConta(String cpf, String fornecedor, String data, String nome_prod, String desc_prod) {
		
		if(cpf.trim().isEmpty() || cpf.length() != 11)
			throw new IllegalArgumentException("Erro ao cadastrar compra: cpf invalido.");
		if(nome_prod == null || nome_prod.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
		if(desc_prod == null || desc_prod.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");
		if(data == null || data.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: data nao pode ser vazia ou nula.");
		
		verificaData(data);
		
		if(fornecedor == null || fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");

		double preco;
		
		try {
			
			controlerCliente.buscarCliente(cpf);
			controlerFornecedor.buscarFornecedor(fornecedor);
			controlerFornecedor.buscarProduto(nome_prod, desc_prod, fornecedor);
			
			preco = controlerFornecedor.getPrecoProduto(fornecedor, nome_prod, desc_prod);
			
		} catch (NullPointerException err) {
		
			throw new NullPointerException(err.getMessage().contains("cliente") ? "Erro ao cadastrar compra: cliente nao existe." : "Erro ao cadastrar compra: fornecedor nao existe.");
		
		} catch (RuntimeException err) {
			
			throw new RuntimeException("Erro ao cadastrar compra: produto nao existe.");
		}
		
		controlerCliente.adicionarConta(cpf, fornecedor, data, nome_prod, desc_prod,preco);
	}
	
	/**
	 * Método que verifica se a data informada é válida.
	 * 
	 * @param dataCompra data da compra
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso a data inserida esteja em um formato inválido.
	 */
	private void verificaData(String dataCompra) {
		
		String[] data = dataCompra.split("/");
		
		if(data[1].length() > 2)
			throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
		if(Integer.parseInt(data[0]) > 31)
			throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");

	}

	/**
	 * Método que busca determinado cliente no sistema.
	 * 
	 * @param cpf cpf do cliente
	 * 
	 * @return Retorna a representação, em String, do cliente, se ele for localizado.
	 */
	public String buscarCliente(String cpf) {
		
		return controlerCliente.buscarCliente(cpf);
		
	}

	/**
	 * Método que busca determinado fornecedor no sistema.
	 * 
	 * @param nome nome do fornecedor
	 * 
	 * @return Retorna a representação, em String, do fornecedor, caso ele seja encontrado.
	 */
	public String buscarFornecedor(String nome) {
		
		return controlerFornecedor.buscarFornecedor(nome);
		
	}

	/**
	 * Método que busca determinado produto no sistema.
	 * 
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param fornecedor fornecedor do produto
	 * 
	 * @return Retorna a representação, em String, do produto, caso ele seja encontrado.
	 */
	public String buscarProduto(String nome, String descricao, String fornecedor) {
		
		return controlerFornecedor.buscarProduto(nome, descricao, fornecedor);
		
	}

	/**
	 * Método que exibe todos os produtos de determinado fornecedor cadastrado no sistema.
	 * 
	 * @param fornecedor fornecedor dos produtos
	 * 
	 * @return Retorna uma representação textual de todos os produtos do fornecedor.
	 */
	public String exibirProdutos(String fornecedor) {
		
		return controlerFornecedor.exibirProdutos(fornecedor);
		
	}

	/**
	 * Método que exibe todos os produtos cadastrados no sistema.
	 * 
	 * @return Retorna todos os produtos do sistema.
	 */
	public String exibirProdutos() {
		
		return controlerFornecedor.exibirProdutos();
		
	}

	/**
	 * Método que lista todos os clientes cadastrados no sistema.
	 * 
	 * @return Retorna uma representação textual de todos os clientes cadastrados.
	 */
	public String listarClientes() {
		
		return controlerCliente.listarClientes();
		
	}

	/**
	 * Método que lista todos os fornecedores no sistema.
	 * 
	 * @return Retorna uma representação textual de todos os fornecedores cadastrados.
	 */
	public String listarFornecedores() {
		
		return controlerFornecedor.listarFornecedores();
		
	}

	/**
	 * Método que edita um fornecedor, esse método recebe o nome do fornecedor, o atributo a ser editado, e o seu 
	 * respectivo valor.
	 * 
	 * @param nome nome do fornecedor
	 * @param atributo atributo a ser editado
	 * @param novoValor novo valor do atributo a ser editado
	 * 
	 * @return Retorna uma mensagem confirmando a operação.
	 */
	public String editarFornecedor(String nome, String atributo, String novoValor) {
		
		return controlerFornecedor.editarFornecedor(nome, atributo, novoValor);
	}

	/**
	 * Método que edita um cliente, esse método recebe o cpf do cliente, o atributo a ser editado, e o seu respectivo 
	 * valor novo.
	 * 
	 * @param cpf cpf do cliente
	 * @param atributo atributo a ser editado
	 * @param novoValor novo valor do atributo a ser editado
	 */
	public void editarCliente(String cpf, String atributo, String novoValor) {
		
		controlerCliente.editarCliente(cpf, atributo, novoValor);
	}

	/**
	 * Método que edita um produto, esse método recebe o o nome, a descrição do produto, o atributo a ser editado, e o seu respectivo 
	 * valor novo.
	 * 
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param fornecedor fornecedor do produto
	 * @param novoPreco novo preço do produto
	 * 
	 * @return Retorna uma mensagem confirmando a execução da edição do produto.
	 */
	public String editarProduto(String nome, String descricao, String fornecedor, double novoPreco) {
		
		return controlerFornecedor.editarProduto(nome, descricao, fornecedor, novoPreco);
	}

	/**
	 * Método responsável pela edição de um combo no sistema, ele recebe como parâmetros o nome, a descrição 
	 * e o fornecedor do combo para localiza-lo no sistema, assim como o novo fator a ser setado, já que o fator é o unico 
	 * atributo que pode ser alterado.
	 * 
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fornecedor fornecedor do combo
	 * @param novoFator novo fator de desconto do combo
	 */
	public void editarCombo(String nome, String descricao, String fornecedor, double novoFator) {
		
		controlerFornecedor.editarCombo(nome, descricao, fornecedor, novoFator);
	}

	/**
	 * Método que remove um cliente do sistema.
	 * 
	 * @param cpf cpf do cliente a ser removido
	 * 
	 * @return Retorna uma mensagem de confirmação da exclusão do cliente.
	 */
	public String removerCliente(String cpf) {
		
		return controlerCliente.removerCliente(cpf);
	}

	/**
	 * Método que remove um fornecedor do sistema.
	 * 
	 * @param nome nome do fornecedor
	 * 
	 * @return Retorna uma mensagem de confirmação da exclusão do cliente.
	 */
	public String removerFornecedor(String nome) {
		
		return controlerFornecedor.removerFornecedor(nome);
	}

	/**
	 * Método responsável pela remoção de um {@link ProdutoSimples} ou um {@link Combo} do sistema.
	 * 
	 * @param nome nome do produto ou combo
	 * @param descricao descrição do produto ou combo
	 * @param fornecedor fornecedor do produto ou combo
	 * 
	 * @return Retorna uma mensagem de confirmação da operação.
	 */
	public String removerProduto(String nome, String descricao, String fornecedor) {
		
		return controlerFornecedor.removerProduto(nome, descricao, fornecedor);
	}

	/**
	 * Método responsável pela captura do débito total de uma determinada conta.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor nome do fornecedor
	 * 
	 * @return Retorna o debito da conta, se a mesma existir.
	 * 
	 * @throws IllegalArgumentException Gera esta exceção caso o nome do fornecedor seja vazio ou inválido.
	 * @throws NullPointerException Exceção gerada caso o fornecedor não seja encontrado no sistema.
	 */
	public String getDebito(String cpf, String fornecedor) {
		
		if(fornecedor == null || fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		
		try {
			
			controlerFornecedor.buscarFornecedor(fornecedor);
			
		} catch (NullPointerException err) {
			
			throw new NullPointerException("Erro ao recuperar debito: fornecedor nao existe.");
		}
		return controlerCliente.getDebito(cpf,fornecedor);
	}

	/**
	 * Método responsável por exibir todas as compras feitas com um determinado fornecedor.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor fornecedor dos produtos
	 * 
	 * @return Retorna uma representação de todas as compras do cliente e fornecedor informados.
	 * 
	 * @throws IllegalArgumentException Esta exceção é gerada caso o nome do fornecedor seja inválido ou nulo.
	 * @throws NullPointerException Exceção gerada caso o fornecedor não seja encontrado no sistema.
	 */
	public String exibirContas(String cpf, String fornecedor) {
		
		if(fornecedor == null || fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");
		
		try {
			
			controlerFornecedor.buscarFornecedor(fornecedor);
			
		} catch (NullPointerException err) {
			
			throw new NullPointerException("Erro ao exibir conta do cliente: fornecedor nao existe.");
		}
		
		return controlerCliente.exibirContas(cpf,fornecedor);
	}

	/**
	 * Método que lista todas as contas de um determinado cliente.
	 * 
	 * @param cpf cpf do cliente
	 * 
	 * @return Retorna uma representação com todas as contas do determinado cliente.
	 */
	public String exibirContas(String cpf) {
		
		return controlerCliente.exibirContas(cpf);
	}

	/**
	 * Método reponsável por quitar os debitos de uma detemrinada conta.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor fornecedor dos produtos
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o nome do fornecedor seja nulo ou inválido.
	 * @throws NullPointerException Exceção gerada caso o fornecedor não seja encontrado.
	 */
	public void quitarDebito(String cpf, String fornecedor) {
		if(fornecedor == null || fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro no pagamento de conta: fornecedor nao pode ser vazio ou nulo.");
		
		try {
			
			controlerFornecedor.buscarFornecedor(fornecedor);
		
		} catch (NullPointerException err) {
		
			throw new NullPointerException("Erro no pagamento de conta: fornecedor nao existe.");
		}
		
		controlerCliente.quitarDebito(cpf,fornecedor);
		
	}

	/**
	 * Método que lista todas as compras cadastradas no sistema.
	 * 
	 * @return Retorna uma representação de todas as compras do sistema, ordenadas à critério do 
	 * administrador do sistema.
	 * 
	 */
	public String listarCompras() {
		
		return controlerCliente.listarCompras();
	}

	/**
	 * Método responsável por selecionar o tipo de ordenação especifico das compras.
	 * 
	 * @param criterio criterio de ordenação
	 */
	public void ordenarPor(String criterio) {
		controlerCliente.ordenarPor(criterio);
		
	}
	
	
}
