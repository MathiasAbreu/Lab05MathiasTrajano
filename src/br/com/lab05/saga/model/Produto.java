package br.com.lab05.saga.model;

/**
 * Classe que representa um modelo primordial de um produto, está classe é utilizada para modelar outros tipos
 * de produtos que possam vir a existir.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class Produto {

	/**
	 * Nome do Produto.
	 */
	protected String nome;
	
	/**
	 * Descrição do Produto.
	 */
	protected String descricao;

	public Produto(String nome,String descricao) {
		
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * Método que retorna o nome do produto.
	 * 
	 * @return O nome do produto.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Método que retorna a descrição do produto.
	 * 
	 * @return A descrição do produto.
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Método que retorna o preço do produto.
	 * 
	 * @return O preço do produto.
	 */
	
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
		return String.format("%s - %s",nome,descricao);
	}
}
