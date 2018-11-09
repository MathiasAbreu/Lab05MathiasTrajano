package br.com.lab05.saga.model;

/**
 * Representação de um combo de produtos, possui todos os dados essenciais e os métodos que controlam e manipulam tais dados.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class Combo extends Produto {
	
	/**
	 * Fator de desconto do combo.
	 */
	private double fator;
	
	/**
	 * Produto Primário que compõe o combo.
	 */
	private ProdutoSimples produto1;
	
	/**
	 * Produto Secundário que compõe o combo.
	 */
	private ProdutoSimples produto2;
	
	/**
	 * Construtor responsável pela criação de um novo Combo no sistema, eles recebe todos os dados essenciais para a 
	 * construção desse tipo de objeto.
	 * 
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fator fator de desconto do combo
	 * @param produto1 produto primário do combo
	 * @param produto2 produto secundário do combo
	 */
	public Combo(String nome,String descricao,double fator,ProdutoSimples produto1,ProdutoSimples produto2) {
		
		super(nome,descricao);
		
		this.fator = fator;
		
		this.produto1 = produto1;
		this.produto2 = produto2;
		
	}

	/**
	 * Método que retorna o fator de desconto do combo.
	 * 
	 * @return O fator de desconto do combo.
	 */
	public double getFator() {
		return fator;
	}

	/**
	 * Método que altera o fator de desconto do combo.
	 * 
	 * @param fator o novo fator do combo
	 */
	public void setFator(double fator) {
		this.fator = fator;
	}

	/**
	 * Método que retorna o primeiro produto do combo.
	 * 
	 * @return O produto primário do combo.
	 */
	public Produto getProduto1() {
		return produto1;
	}

	/**
	 * Método que retorna o segundo produto do combo.
	 * 
	 * @return O produto secundário do combo.
	 */
	public Produto getProduto2() {
		return produto2;
	}

	/**
	 * Método que retorna a representação de um combo.
	 * 
	 * @return Retorna um representação textual do combo.
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - R$%.2f",nome,descricao,calculaPreco());
	}

	/**
	 * Método que calcula o preço do combo baseando-se nos preços unitários dos produtos que o compõem, 
	 * juntamente com o fator de desconto.
	 * 
	 * @return Retorna o preço do combo.
	 */
	public double calculaPreco() {
		return (produto1.getPreco() + produto2.getPreco()) * (1 - fator);
	}
}
