/**
 * 
 */
package br.com.lab05.saga.model;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class Compra {

	private String dataCompra;
	private String nomeProduto;
	private String descricaoProduto;
	
	private double preco;
	
	/**
	 * @param dataCompra
	 * @param preco
	 * @param produto
	 */
	public Compra(String dataCompra,String nomeProduto,String descricaoProduto, double preco) {
		
		if(dataCompra == null || dataCompra.trim().isEmpty())
			throw new IllegalArgumentException("");
		if(nomeProduto == null || nomeProduto.trim().isEmpty())
			throw new IllegalArgumentException("");
		if(descricaoProduto == null || descricaoProduto.trim().isEmpty())
			throw new IllegalArgumentException("");
		
		if(preco <= 0)
			throw new IllegalArgumentException("");
		
		this.dataCompra = dataCompra;
		this.nomeProduto = nomeProduto;
		this.descricaoProduto = descricaoProduto;
		this.preco = preco;
	}

	/**
	 * @return the dataCompra
	 */
	public String getDataCompra() {
		return dataCompra;
	}

	/**
	 * @param dataCompra the dataCompra to set
	 */
	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	/**
	 * @return the nomeProduto
	 */
	public String getNomeProduto() {
		return nomeProduto;
	}

	/**
	 * @param nomeProduto the nomeProduto to set
	 */
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	/**
	 * @return the descricaoProduto
	 */
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	/**
	 * @param descricaoProduto the descricaoProduto to set
	 */
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	/**
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
