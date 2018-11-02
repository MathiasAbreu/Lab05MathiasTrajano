package br.com.lab05.saga.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import br.com.lab05.saga.model.Fornecedor;
import br.com.lab05.saga.model.FornecedorComparator;

/**
 * Representação do controle de fornecedores, onde se encontra todos os metodos de controle e modelagem, 
 * tal como a coleção que armazena os fornecedores.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class ControllerFornecedorSaga {

	/**
	 * Coleção que armazena todos os fornecedores do sistema.
	 */
	private HashMap<String, Fornecedor> fornecedores;
	
	/**
	 * Construtor que inicia uma nova instância de um Controle de Fornecedor.
	 */
	public ControllerFornecedorSaga() {
		
		fornecedores = new HashMap<>();
	}
	
	/**
	 * Método que cadastra novos fornecedores no sistema, recebe os dados essenciais de um fornecedor, 
	 * verifica se pode houve duplicidade de fornecedores e somente depois gera um novo fornecedor. Pode também 
	 * gerar e repassar possiveis exceções.
	 *  
	 * @param nome nome do fornecedor
	 * @param email email do fornecedor
	 * @param telefone telefone do fornecedor
	 * 
	 * @return Retorna uma mensagem com o sucesso da operação.
	 * 
	 * @throws RuntimeException Gera uma exceção caso haja tentativa de cadastrar um fornecedor que já exista, 
	 * como também repassa exceções do tipo {@link NullPointerException} ou do tipo {@link IllegalArgumentException} 
	 * que podem ser geradas no momento da criação do objeto do tipo {@link Fornecedor}.
	 */
	public String adicionarFornecedor(String nome,String email,String telefone) throws RuntimeException {
		
		if(!fornecedores.containsKey(nome)) {
			
			fornecedores.put(nome,new Fornecedor(nome,email,telefone));
			return nome;
		}
		
		throw new RuntimeException("Erro no cadastro de fornecedor: fornecedor ja existe.");
	}

	/**
	 * Método responsável pela busca de um possivel fornecedor no sistema.
	 * 
	 * @param nome nome do fornecedor
	 * 
	 * @return Retorna uma representação do {@link Fornecedor}.
	 */
	public String buscarFornecedor(String nome) {
		
		if(fornecedores.containsKey(nome))
			return fornecedores.get(nome).toString();
		
		throw new NullPointerException("Erro na exibicao do fornecedor: fornecedor nao existe.");
	}

	/** Método que lista todos os fornecedores cadastrados no sistema, em ordem alfabetica.
	 * 
	 * @return Retorna uma representação de todos os clientes em ordem alfabética.
	 * 
	 * @throws RuntimeException Gera um erro caso não haja fornecedores cadastrados.
	 */
	public String listarFornecedores() throws NullPointerException{
		
		Set<String> chaves = fornecedores.keySet();
		ArrayList<Fornecedor> fornecedors = new ArrayList<>();
		
		for (String chave : chaves) {
			
			fornecedors.add(fornecedores.get(chave));
		}
		
		FornecedorComparator<?> comparador = new FornecedorComparator();
		Collections.sort(fornecedors,comparador);
		
		String retorno = fornecedors.get(0).toString();
		
		for (int i = 1; i < fornecedors.size(); i++) {
			retorno += " | " + fornecedors.get(i).toString();
		}
		
		return retorno;
	}

	/** Método que realiza uma busca, e depois altera algum dos dados do fornecedor, 
	 * tanto o nome, como o dado a ser alterado e seu novo conteudo são passados no 
	 * momento da chamado do método. Caso tentem editar algum dado inexistente ou de algum 
	 * fornecedor não cadastrado, são geradas exceções.
	 * 
	 * @param nome nome do fornecedor a ser editado
	 * @param novoDado novo dado a ser alterado
	 * @param valor novo valor do dado a ser alterado
	 * 
	 * @return Retorna uma confirmação ou não do sucesso da edição do {@link Fornecedor}.
	 * 
	 * @throws NullPointerException Caso tentem editar algum dado de um fornecedor que não esteja cadastrado,
	 * esta exceção é gerada.
	 * 
	 * @throws IllegalArgumentException Caso tentem editar algum dado inexistente em um cliente, esta 
	 * exceção é gerada!
	 */
	public String editarFornecedor(String nome, String atributo,String novoDado) {
	
		if(fornecedores.containsKey(nome)) {
			
			Fornecedor fornecedor = fornecedores.get(nome);
			
			if(atributo.trim().isEmpty())
				throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
			if(novoDado.trim().isEmpty())
				throw new IllegalArgumentException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
			if(atributo.equals("nome") || atributo.equals("email") || atributo.equals("telefone")) {
				
				if(atributo.equals("nome"))
					throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
				
				if(atributo.equals("email")) {
					fornecedor.setEmail(novoDado);
				}
				if(atributo.equals("telefone")) {
					fornecedor.setTelefone(novoDado);
				}
				
				fornecedores.put(nome,fornecedor);
				return "Dado alterado com sucesso!";
				
			}else
				throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
		}
		
		throw new NullPointerException("Erro na edicao do fornecedor: fornecedor nao existe.");
	}

	/** Este método remove algum fornecedor que esteja cadastrado no sistema.
	 * 
	 * @param nome nome do fornecedor a ser removido.
	 * 
	 * @return Retorna uma mensagem de confirmação ou não da exclusão de algum fornecedor.
	 * 
	 * @throws NullPointerException Caso o fornecedor não esteja cadastrado no sistema, um erro é gerado pois não há como excluir um 
	 * fornecedor não cadastrado!
	 */
	public String removerFornecedor(String nome) {
		
		if(nome.trim().isEmpty())
			throw new IllegalArgumentException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.");
		if(fornecedores.containsKey(nome)) {
					
			fornecedores.remove(nome);
			return nome;
		}
				
		throw new NullPointerException("Impossível remover um cliente que não estava cadastrado!");
	}

	/**
	 * 
	 * @param fornecedor
	 * @param nome
	 * @param descricao
	 * @param preco
	 * @return
	 * @throws RuntimeException
	 */
	public String adicionarProduto(String fornecedor,String nome, String descricao, double preco) throws RuntimeException {
		
		if(fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		
		if(fornecedores.containsKey(fornecedor)) {
			
			return fornecedores.get(fornecedor).adicionarProduto(nome,descricao,preco);
		}
		
		throw new NullPointerException("Erro no cadastro de produto: fornecedor nao existe.");
	}

	public String buscarProduto(String nome, String descricao, String fornecedor) {
		
		if(fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		
		if(fornecedores.containsKey(fornecedor)) {
			
			return fornecedores.get(fornecedor).buscarProduto(nome,descricao).toString();
		}
		
		throw new NullPointerException("Erro na exibicao de produto: fornecedor nao existe.");
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @param novoPreco
	 * @return
	 */
	public String editarProduto(String nome, String descricao, String fornecedor, double novoPreco) {
		
		if(fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		
		if(fornecedores.containsKey(fornecedor)) {
			
			return fornecedores.get(fornecedor).editarProduto(nome,descricao,novoPreco);
		}
		else
			throw new NullPointerException("Erro na edicao de produto: fornecedor nao existe.");
	}

	public String exibirProdutos(String fornecedor) {

		if(fornecedores.containsKey(fornecedor)) {
			
			return fornecedores.get(fornecedor).listarProdutos();
		}
		
		throw new NullPointerException();
	}
	
	public String exibirProdutos() {
		
		Set<String> chaves = fornecedores.keySet();
		ArrayList<Fornecedor> fornecedors = new ArrayList<>();
		
		for (String chave : chaves) {
			
			fornecedors.add(fornecedores.get(chave));
		}
		
		FornecedorComparator<?> comparador = new FornecedorComparator<>();
		Collections.sort(fornecedors,comparador);
		
		String retorno = fornecedors.get(0).listarProdutos();
		
		for(int i = 1;i < fornecedors.size();i++) {
			
			retorno += " | " + fornecedors.get(i).listarProdutos();
		}
		
		return retorno;
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param fornecedor
	 * @return
	 */
	public String removerProduto(String nome, String descricao, String fornecedor) {
		
		if(fornecedor == null || fornecedor.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		}
		
		if(fornecedores.containsKey(fornecedor)) {
			
			return fornecedores.get(fornecedor).removerProduto(nome,descricao);
		}
		
		throw new NullPointerException("Erro na remocao de produto: fornecedor nao existe.");
	}
}