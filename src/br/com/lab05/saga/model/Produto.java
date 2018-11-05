package br.com.lab05.saga.model;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class Produto {

	protected String nome;
	protected String descricao;
	protected double preco;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * Método que transforma um Produto em uma representação em inteiro.
	 * 
	 * @return Retorna um número inteiro representando um Produto.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	
	/**
	 * Método que verifica se dois produtos são iguais ou não.
	 * 
	 * @param obj Qualquer objeto que possa ser comparado com um produto.
	 * 
	 * @return Retorna um valor booleando representando a igualdade ou não de dois produtos.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(getClass() == obj.getClass()) {
			
			Produto other = (Produto) obj;
			
			return (this.nome.equals(other.getNome()) && this.descricao.equals(other.getDescricao()));
		}
		
		return false;
	}
	
	/**
	 * Método responsável pela impressão dos dados de um produto.
	 * 
	 * @return Retorna uma representação de um produto.
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - R$%.2f",nome,descricao,preco);
	}

}
