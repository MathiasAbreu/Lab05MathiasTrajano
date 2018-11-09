/**
 * 
 */
package br.com.lab05.saga.model;

/**
 * Classe que representa uma conta, possui seus dados essenciais de identificação e os métodos capazes de controlar 
 * e manipular tais dados.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class Compra {

	/**
	 * Nome do cliente
	 */
	private String nomeCliente;
	
	/**
	 * Nome do fornecedor
	 */
	private String nomeFornecedor;
	
	/**
	 * Data da compra
	 */
	private String dataCompra;
	
	/**
	 * Nome do Produto
	 */
	private String nomeProduto;
	
	/**
	 * Descrição do Produto
	 */
	private String descricaoProduto;
	
	/**
	 * Preço do Produto
	 */
	private double preco;
	
	/**
	 * Construtor responsável pela instanciação de uma nova compra, ele recebe todos os dados necessários para 
	 * criar um novo objeto do tipo conta.
	 * 
	 * @param nomeCliente nome do cliente
	 * @param descricaoProduto descrição do produto
	 * @param dataCompra deta da compra
	 * @param preco preço da compra
	 * @param produto produto da compra
	 */
	public Compra(String nomeCliente,String nomeFornecedor,String dataCompra,String nomeProduto,String descricaoProduto, double preco) {
		
		this.nomeCliente = nomeCliente;
		this.nomeFornecedor = nomeFornecedor;
		
		this.dataCompra = dataCompra;
		this.nomeProduto = nomeProduto;
		this.descricaoProduto = descricaoProduto;
		this.preco = preco;
	}

	/**
	 * Método que retorna o nome do cliente.
	 * 
	 * @return O nome do Cliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * Método que retorna o nome do fornecedor.
	 * 
	 * @return O nome do Fornecedor.
	 */
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	/**
	 * Método que retorna a Data da Compra.
	 * 
	 * @return A data da compra.
	 */
	public String getDataCompra() {
		return dataCompra;
	}

	/**
	 * Método que retorna o nome do Produto.
	 * 
	 * @return O nome do produto.
	 */
	public String getNomeProduto() {
		return nomeProduto;
	}

	/**
	 * Método que retorna a descrição do Produto.
	 * 
	 * @return A descrição do Produto.
	 */
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	/**
	 * Método que retorna o Preço da Compra.
	 * 
	 * @return O preço da compra.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * Método responsável por retornar uma representação de um compra, informando seus dados principais.
	 * 
	 * @return Retorna uma representação, em String, da compra.
	 */
	@Override
	public String toString() {
		
		String[] data = dataCompra.split("/");
		return nomeProduto + " - " + String.format("%s-%s-%s",data[0],data[1],data[2]);
	}
	
	
}
