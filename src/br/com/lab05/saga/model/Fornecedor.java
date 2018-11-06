package br.com.lab05.saga.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import br.com.lab05.saga.comparators.ProdutoComparator;

/** 
 * Representação de um fornecedor, possui como dados essenciais o nome, email e telefone dos fornecedores, 
 * como também todos os produtos que são comercializados pelos mesmos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class Fornecedor {

	/**
	 * Nome do fornecedor.
	 */
	private String nome;
	
	/**
	 * Email do fornecedor.
	 */
	private String email;
	
	/**
	 * Telefone do fornecedor.
	 */
	private String telefone;
	
	/**
	 * Coleção que armazena todos os produtos do fornecedor.
	 */
	private ArrayList<Produto> produtos;

	/**
	 * Construtor que instancia um novo fornecedor já recebendo seus dados principais. Caso algum dos 
	 * parametros seja inválido, o mesmo não gera um novo fornecedor.
	 * 
	 * @param nome nome do fornecedor
	 * @param email email do fornecedor
	 * @param telefone telefone do fornecedor
	 * 
	 * @throws NullPointerException Caso algum dos dados seja null, o construtor gera uma exceção impe
	 * dindo que um fornecedor inválido seja criado!
	 * 
	 * @throws IllegalArgumentException Caso algum dos dados esteja vazio, o construtor gera uma exceção 
	 * impedindo que um fornecedor inválido seja criado!
	 */
	public Fornecedor(String nome, String email, String telefone) {

		if(nome == null || nome.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		if(email == null || email.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		if(telefone == null || telefone.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		
		produtos = new ArrayList<>();
	}

	/** 
	 * Método que retorna o nome do fornecedor.
	 * 
	 * @return O nome do fornecedor.
	 */
	public String getNome() {
		return nome;
	}

	/** 
	 * Método que retorna o email do fornecedor.
	 * 
	 * @return O email do fornecedor.
	 */
	public String getEmail() {
		return email;
	}

	/** 
	 * Método que altera o email do fornecedor.
	 * 
	 * @param email O novo email do fornecedor.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 
	 * Método que retorna o telefone do fornecedor.
	 * 
	 * @return o telefone do fornecedor.
	 */
	public String getTelefone() {
		return telefone;
	}

	/** 
	 * Método que altera o telefone do fornecedor.
	 * 
	 * @param telefone O novo telefone do fornecedor.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/** 
	 * Método que transforma o fornecedor em uma representação em inteiro.
	 * 
	 * @return Retorna um inteiro representando o fornecedor.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/** 
	 * Método que verifica se dois fornecedores são iguais.
	 * 
	 * @param obj um objeto qualquer para ser comparado.
	 * 
	 * @return Retorna um booleando confirmando ou não a igualdade entre fornecedores.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(getClass() == obj.getClass()) {
			
			Fornecedor other = (Fornecedor) obj;
			if(this.nome.equals(other.getNome())) {
				
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Método que retorna a representação textual do Fornecedor.
	 * 
	 * @return Representação textual de um fornecedor.
	 */
	@Override
	public String toString() {
		return  nome + " - " + email + " - " + telefone;
	}

	/**
	 * Método que adiciona um novo produto para tal fornecedor.
	 * 
	 * @param nomeProduto nome do produto
	 * @param descricao descrição do produto
	 * @param preco preço do produto
	 * 
	 * @return Retorna uma confirmação caso o produto seja cadastrado com sucesso.
	 * 
	 * @throws RuntimeException Gera essa exceção caso o produto já exista para tal fornecedor.
	 * 
	 */
	public String adicionarProduto(String nomeProduto, String descricao, double preco) throws RuntimeException {
		
		if(produtos.contains(new ProdutoSimples(nomeProduto,descricao,preco))) 
			throw new RuntimeException("Erro no cadastro de produto: produto ja existe.");
		else {
			
			produtos.add(new ProdutoSimples(nomeProduto,descricao,preco));
			return "Produto cadastrado com sucesso!";
		}		
	}
	
	/**
	 * Método que verifica se há algum produto do fornecedor.
	 * 
	 * @param nomeProduto nome do produto
	 * @param descricao descricao do produto
	 * 
	 * @return Retorna um valor verificando a existência ou não de certo produto.
	 * 
	 */
	private ProdutoSimples verificaProduto(String nomeProduto,String descricao) {
		
		for (Produto produto : produtos) {
			
			if(produto.getClass() == ProdutoSimples.class) {
				if(produto.getNome().equals(nomeProduto) && produto.getDescricao().equals(descricao)) {
					
					return (ProdutoSimples) produto;
				}
			}
		}
		
		return null;
	}

	/**
	 * Método que busca se há determinado produto cadastrado em um fornecedor.
	 * 
	 * @param nomeProduto nome do produto
	 * @param descricao descricao do produto
	 * 
	 * @return Retorna um determinado {@link ProdutoSimples} de um fornecedor, se o mesmo existir.
	 * 
	 * @throws IllegalArgumentException Gera uma exceção caso algum dos parâmetros seja nulo ou vazio, finalizando assim
	 * o método.
	 * 
	 * @throws NullPointerException Essa excessão é gerada caso o produto não seja encontrado, indicando assim que o produto não existe.
	 */
	public Produto buscarProduto(String nomeProduto, String descricao) {
		
		if(nomeProduto.trim().isEmpty())
			throw new IllegalArgumentException("nome nao pode ser vazio ou nulo.");
		if(descricao.trim().isEmpty())
			throw new IllegalArgumentException("descricao nao pode ser vazia ou nula.");
		
		ProdutoSimples produto = verificaProduto(nomeProduto,descricao);
		Combo combo = verificaCombo(nomeProduto, descricao);
		
		if(produto != null)
			return produto;
		if(combo != null)
			return combo;
		
		throw new NullPointerException("produto nao existe.");
	}

	/**
	 * Método que edita algum produto recebendo como parâmetros o nome e descrição do produto desejada e o novo preço, 
	 * pois esse é o unico dado que se permite alterar.
	 * 
	 * @param nomeProduto nome do produto
	 * @param descricao descricao do produto
	 * @param novoPreco novo preço do produto
	 * 
	 * @return Retorna uma confirmação da edição do cliente
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso algum dos prametros passados seja nulo ou vazio.
	 * 
	 */
	public String editarProduto(String nomeProduto, String descricao, double novoPreco) {
		
		if(nomeProduto.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		}
		if(descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		}
		if(novoPreco < 0) {
			throw new IllegalArgumentException("Erro na edicao de produto: preco invalido.");
		}
		
		ProdutoSimples produto;
		
		try {
			
			produto = (ProdutoSimples) buscarProduto(nomeProduto, descricao);
		
		} catch (NullPointerException | IllegalArgumentException err) {
			
			throw new RuntimeException("Erro na edicao de produto: " + err.getMessage());
		}
		
		produtos.remove(produto);
		
		produto.setPreco(novoPreco);
		produtos.add(produto);
		
		return "Produto editado!";
	}
	
	/** 
	 * Método que edita um {@link Combo} no sistema, recebe os dados que identificam um combo assim como o 
	 * novo fator de desconto do mesmo.
	 * 
	 * @param nomeProduto nome do combo
	 * @param descricao descrição do combo
	 * @param novoFator novo fator de desconto do combo
	 */
	public void editarCombo(String nomeProduto, String descricao, double novoFator) {

		if(nomeProduto.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de combo: nome nao pode ser vazio ou nulo.");
		}
		if(descricao.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de combo: descricao nao pode ser vazia ou nula.");
		}
		if(novoFator <= 0 || novoFator >= 1) {
			throw new IllegalArgumentException("Erro na edicao de combo: fator invalido.");
		}
		
		Combo produto;
		
		try {
			
			produto = (Combo) buscarProduto(nomeProduto, descricao);
		
		} catch (NullPointerException | IllegalArgumentException err) {
			
			throw new RuntimeException("Erro na edicao de combo: " + err.getMessage());
		}
		
		produtos.remove(produto);
		
		produto.setFator(novoFator);
		produtos.add(produto);
	}
	
	/** 
	 * Método que lista todos os produtos do fornecedor em ordem alfabética.
	 * 
	 * @return Retorna uma String com as representações de todo so produtos do fornecedor.
	 * 
	 * @throws NullPointerException Gera essa exceção caso não exista produtos cadastrados.
	 */
	public String listarProdutos() {
		
		if(produtos.size() == 0) {
			return "";
		}
		
		ProdutoComparator comparador = new ProdutoComparator();
		Collections.sort(produtos,comparador);
		
		String retorno = nome + " - " + produtos.get(0).toString();
		
		for (int i = 1; i < produtos.size(); i++) {
			
			retorno += " | " + nome + " - " + produtos.get(i).toString();
		}
		
		return retorno;
	}

	/**
	 * Método que remove um produto que esteja cadastrado.
	 * 
	 * @param nomeProduto nome do produto
	 * @param descricao descrição do produto
	 * 
	 * @return Retorna uma mensagem de confirmação da exclusão de tal produto.
	 * 
	 * @throws IllegalArgumentException Gera essa exceção caso algum dos parâmetros seja vazio ou nulo.
	 * 
	 * @throws NullPointerException Gera essa exceção caso o produto não seja encontrado no sistema.
	 */
	public String removerProduto(String nomeProduto, String descricao) {

		if(nomeProduto == null || nomeProduto.trim().isEmpty())
			throw new IllegalArgumentException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		if(descricao == null || descricao.trim().isEmpty())
			throw new IllegalArgumentException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		
		for (int i = 0; i < produtos.size(); i++) {
			
			if(produtos.get(i).getNome().equals(nomeProduto) && produtos.get(i).getDescricao().equals(descricao)) {
				
				produtos.remove(i);
				return "Produto removido com sucesso!";
			}
		}
		
		throw new NullPointerException("Erro na remocao de produto: produto nao existe.");
	}

	/** 
	 * Método que verifica se há produtos cadastrados para o fornecedor.
	 * 
	 * @return Retorna o número de produtos cadastrados.
	 */
	public int existirProdutos() {

		return produtos.size();
	}

	/**
	 * Método que verifica se existe um combo no sistema.
	 * 
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * 
	 * @return Retorna um valor booleano indicando se há ou não um combo no sistema.
	 */
	private Combo verificaCombo(String nome, String descricao) {
		
		for (Produto produto : produtos) {
			
			if(produto.getClass() == Combo.class) {
				
				if(produto.getNome().equals(nome) && produto.getDescricao().equals(descricao))

					return (Combo) produto;
			}
				
		}
		
		return null;
	}

	/**
	 * Método que adiciona um novo {@link Combo} no sistema, o método recebe todos os dados essenciais do novo 
	 * combo assim como o {@link Fornecedor} que fornece tal combo.
	 * 
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fator fator de desconto do combo
	 * @param produtosDoCombo produtos que compõem o combo
	 */
	public void adicionarCombo(String nome, String descricao, double fator, String produtosDoCombo) {
		
		/*
		 * Falar com algum monitor ou professor sobre essa questão.
		 */
		if(nome == null || nome.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		
		if(descricao == null || descricao.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.");

		if(fator <= 0 || fator >= 1)
			throw new IllegalArgumentException("Erro no cadastro de combo: fator invalido.");
				
		if(verificaCombo(nome, descricao) != null)
			throw new RuntimeException("Erro no cadastro de combo: combo ja existe.");
		
		if(produtosDoCombo.trim().isEmpty())
			throw new IllegalArgumentException("Erro no cadastro de combo: combo deve ter produtos.");
		
		String[] produtos_separados = produtosDoCombo.split(", ");
		String[] produto1 = produtos_separados[0].split(" - ");
		String[] produto2 = produtos_separados[1].split(" - ");
			
		if(verificaCombo(produto1[0],produto1[1]) != null || verificaCombo(produto2[0],produto2[1]) != null)
			throw new IllegalArgumentException("Erro no cadastro de combo: um combo nao pode possuir combos na lista de produtos.");
		
		if(verificaProduto(produto1[0],produto1[1]) != null && verificaProduto(produto2[0],produto2[1]) != null)
			produtos.add(new Combo(nome,descricao,fator,(ProdutoSimples) buscarProduto(produto1[0],produto1[1]),(ProdutoSimples) buscarProduto(produto2[0],produto2[1])));
		else
			throw new NullPointerException("Erro no cadastro de combo: produto nao existe.");
		
	}
}