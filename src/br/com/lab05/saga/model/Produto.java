/**
 * 
 */
package br.com.lab05.saga.model;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class Produto {

	private String nome;
	private String descricao;
	private double preco;
	
	/**
	 * @param nome
	 * @param descricao
	 * @param preco
	 */
	public Produto(String nome, String descricao, double preco) {

		if(nome == null || nome.trim().isEmpty()) 
			throw new RuntimeException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		if(descricao == null || descricao.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		
		if(preco < 0)
			throw new IllegalArgumentException("Erro no cadastro de produto: preco invalido.");
		
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(getClass() == obj.getClass()) {
			
			Produto other = (Produto) obj;
			
			return (this.nome.equals(other.getNome()) && this.descricao.equals(other.getDescricao()));
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - R$%.2f",nome,descricao,preco);
	}
	
	
}