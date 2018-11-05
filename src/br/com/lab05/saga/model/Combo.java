package br.com.lab05.saga.model;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class Combo extends Produto {
	
	private double fator;
	
	private String produto1;
	private String produto2;
	
	public Combo(String nome,String descricao,double fator,String produto1,String produto2) {
		
		if(nome == null || nome.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		
		if(descricao == null || nome.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.");

		if(fator < 0 || fator > 1)
			throw new IllegalArgumentException("Erro no cadastro de combo: fator invalido.");
		
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
	public String getProduto1() {
		return produto1;
	}

	/**
	 * @param produto1 the produto1 to set
	 */
	public void setProduto1(String produto1) {
		this.produto1 = produto1;
	}

	/**
	 * @return the produto2
	 */
	public String getProduto2() {
		return produto2;
	}

	/**
	 * @param produto2 the produto2 to set
	 */
	public void setProduto2(String produto2) {
		this.produto2 = produto2;
	}
	
	
}
