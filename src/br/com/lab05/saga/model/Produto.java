package br.com.lab05.saga.model;

/** Classe que representa um produto no sistema, contém todos os atributos de um produto, assim como os métodos 
 * de controle e manipulação de tais dados.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class Produto {

	/**
	 * Nome do produto
	 */
	private String nome;
	
	/**
	 * Descrição do produto
	 */
	private String descricao;
	
	/**
	 * Preço do produto
	 */
	private double preco;
	
	/** Construtor responsável pela construção de um novo objeto do tipo {@link Produto}, contém clausulas de verificação 
	 * para evitar dados inválidos no momento da criação de um novo produto.
	 * 
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param preco preço do produto
	 * 
	 * @throws RuntimeException Exceção gerada pela passagem errada de algum dos parâmetros do produto.
	 * @throws IllegalArgumentException Essa exceção é gerada caso o preço passado para o produto seja negativo ou igual a 0.
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

	/** Método que retorna o nome do produto.
	 * 
	 * @return O nome do produto.
	 */
	public String getNome() {
		return nome;
	}

	/**Método que retorna a descrição do produto.
	 * 
	 * @return A descrição do produto.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**Método que retorna o preço do produto.
	 * 
	 * @return O preço do produto.
	 */
	public double getPreco() {
		return preco;
	}

	/** Método que altera o preço do produto.
	 * 
	 * @param preco novo preço do produto.
	 */
	public void setPreco(double preco) {
		this.preco = preco;
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