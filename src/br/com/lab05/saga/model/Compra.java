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
			throw new IllegalArgumentException("Erro ao cadastrar compra: data invalida.");
		
		verificaData(dataCompra);
		
		this.dataCompra = dataCompra;
		this.nomeProduto = nomeProduto;
		this.descricaoProduto = descricaoProduto;
		this.preco = preco;
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
