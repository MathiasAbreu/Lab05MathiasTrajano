package br.com.lab05.saga.model;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class Combo extends Produto {
	
	private double fator;
	
	private Produto produto1;
	private Produto produto2;
	
	public Combo(String nome,String descricao,double fator,Produto produto1,Produto produto2) {
		
		this.nome = nome;
		this.descricao = descricao;
		
		this.fator = fator;
		
		this.produto1 = produto1;
		this.produto2 = produto2;
		
	}

	/**
	 * @return the fator
	 */
	public double getFator() {
		return fator;
	}

	/**
	 * @param fator the fator to set
	 */
	public void setFator(double fator) {
		this.fator = fator;
	}

	/**
	 * @return the produto1
	 */
	public Produto getProduto1() {
		return produto1;
	}

	/**
	 * @param produto1 the produto1 to set
	 */
	public void setProduto1(Produto produto1) {
		this.produto1 = produto1;
	}

	/**
	 * @return the produto2
	 */
	public Produto getProduto2() {
		return produto2;
	}

	/**
	 * @param produto2 the produto2 to set
	 */
	public void setProduto2(Produto produto2) {
		this.produto2 = produto2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - R$%.2f",nome,descricao,calculaPreco());
	}

	/**
	 * @return
	 */
	private double calculaPreco() {
		return (produto1.getPreco() + produto2.getPreco()) * (1 - fator);
	}
	
	
}
