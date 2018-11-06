/**
 * 
 */
package br.com.lab05.saga.controller;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class ControllerSaga {

	private ControllerClienteSaga controlerCliente;
	private ControllerFornecedorSaga controlerFornecedor;
	
	public ControllerSaga() {
		
		controlerCliente = new ControllerClienteSaga();
		controlerFornecedor = new ControllerFornecedorSaga();
	}

	/**
	 * @param cpf
	 * @param nome
	 * @param email
	 * @param localizacao
	 * @return
	 */
	public String cadastrarCliente(String cpf, String nome, String email, String localizacao) {
		
		return controlerCliente.cadastrarCliente(cpf, nome, email, localizacao);
	}

	/**
	 * @param nome
	 * @param email
	 * @param telefone
	 * @return
	 */
	public String adicionarFornecedor(String nome, String email, String telefone) {
		
		return controlerFornecedor.adicionarFornecedor(nome, email, telefone);
	}

	/**
	 * @param fornecedor
	 * @param nome
	 * @param descricao
	 * @param preco
	 * @return
	 */
	public String adicionarProduto(String fornecedor, String nome, String descricao, double preco) {
		
		return controlerFornecedor.adicionarProduto(fornecedor, nome, descricao, preco);
	}

	/**
	 * @param fornecedor
	 * @param nome
	 * @param descricao
	 * @param fator
	 * @param produtos
	 */
	public void adicionarCombo(String fornecedor, String nome, String descricao, double fator, String produtos) {
		
		controlerFornecedor.adicionarCombo(fornecedor, nome, descricao, fator, produtos);
		
	}

	/**
	 * @param cpf
	 * @param fornecedor
	 * @param data
	 * @param nome_prod
	 * @param desc_prod
	 */
	public void adicionarConta(String cpf, String fornecedor, String data, String nome_prod, String desc_prod) {
		
		if(cpf.trim().isEmpty() || cpf.length() != 11)
			throw new IllegalArgumentException("Erro ao cadastrar compra: cpf invalido.");
		if(nome_prod == null || nome_prod.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
		if(desc_prod == null || desc_prod.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula");
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
	 * @param dataCompra2
	 */
	private void verificaData(String dataCompra) {
		
		String[] data = dataCompra.split("/");
		
		if(data[1].length() > 2)
			throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
		if(Integer.parseInt(data[0]) > 31)
			throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");

	}


	/**
	 * @param cpf
	 * @return
	 */
	public String buscarCliente(String cpf) {
		
		return controlerCliente.buscarCliente(cpf);
		
	}

	/**
	 * @param nome
	 * @return
	 */
	public String buscarFornecedor(String nome) {
		
		return controlerFornecedor.buscarFornecedor(nome);
		
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @return
	 */
	public String buscarProduto(String nome, String descricao, String fornecedor) {
		
		return controlerFornecedor.buscarProduto(nome, descricao, fornecedor);
		
	}

	/**
	 * @param fornecedor
	 * @return
	 */
	public String exibirProdutos(String fornecedor) {
		
		return controlerFornecedor.exibirProdutos(fornecedor);
		
	}

	/**
	 * @return
	 */
	public String exibirProdutos() {
		
		return controlerFornecedor.exibirProdutos();
		
	}

	/**
	 * @return
	 */
	public String listarClientes() {
		
		return controlerCliente.listarClientes();
		
	}

	/**
	 * @return
	 */
	public String listarFornecedores() {
		
		return controlerFornecedor.listarFornecedores();
		
	}

	/**
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 * @return
	 */
	public String editarFornecedor(String nome, String atributo, String novoValor) {
		
		return controlerFornecedor.editarFornecedor(nome, atributo, novoValor);
	}

	/**
	 * @param cpf
	 * @param atributo
	 * @param novoValor
	 */
	public void editarCliente(String cpf, String atributo, String novoValor) {
		
		controlerCliente.editarCliente(cpf, atributo, novoValor);
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @param novoPreco
	 * @return
	 */
	public String editarProduto(String nome, String descricao, String fornecedor, double novoPreco) {
		
		return controlerFornecedor.editarProduto(nome, descricao, fornecedor, novoPreco);
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @param novoFator
	 */
	public void editarCombo(String nome, String descricao, String fornecedor, double novoFator) {
		
		controlerFornecedor.editarCombo(nome, descricao, fornecedor, novoFator);
	}

	/**
	 * @param cpf
	 * @return
	 */
	public String removerCliente(String cpf) {
		
		return controlerCliente.removerCliente(cpf);
	}

	/**
	 * @param nome
	 * @return
	 */
	public String removerFornecedor(String nome) {
		
		return controlerFornecedor.removerFornecedor(nome);
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @return
	 */
	public String removerProduto(String nome, String descricao, String fornecedor) {
		
		return controlerFornecedor.removerProduto(nome, descricao, fornecedor);
	}

	/**
	 * @param cpf
	 * @param fornecedor
	 * @return
	 */
	public String getDebito(String cpf, String fornecedor) {
		
		return controlerCliente.getDebito(cpf,fornecedor);
	}
	
	
}
