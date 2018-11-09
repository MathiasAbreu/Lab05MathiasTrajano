package br.com.lab05.saga.model;

/** 
 * Classe que representa um produto simples do sistema, contém todos os atributos de um produto, assim como os métodos 
 * de controle e manipulação de tais dados.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 */
public class ProdutoSimples extends Produto {

	/**
	 * Preço do Produto
	 */
	private double preco;
	
	/** 
	 * Construtor responsável pela construção de um novo objeto do tipo {@link ProdutoSimples}, contém clausulas de verificação 
	 * para evitar dados inválidos no momento da criação de um novo produto.
	 * 
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param preco preço do produto
	 * 
	 * @throws RuntimeException Exceção gerada pela passagem errada de algum dos parâmetros do produto.
	 * @throws IllegalArgumentException Essa exceção é gerada caso o preço passado para o produto seja negativo ou igual a 0.
	 */
	public ProdutoSimples(String nome, String descricao, double preco) {
		
		super(nome,descricao);

		if(nome == null || nome.trim().isEmpty()) 
			throw new RuntimeException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		if(descricao == null || descricao.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
				
		if(preco < 0)
			throw new IllegalArgumentException("Erro no cadastro de produto: preco invalido.");
		
		this.preco = preco;
	}
	
	/**
	 * Método que retorna o preço do produto.
	 * 
	 * @return Retorna o preço do produto.
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * Método que altera o preço de um produto simples.
	 * 
	 * @param preco preço do produto
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	/**
	 * Método que retorna um representação do produto.
	 * 
	 * @return Retorna uma representação, em String, de um produto.
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - R$%.2f",nome,descricao,preco);
	}
}