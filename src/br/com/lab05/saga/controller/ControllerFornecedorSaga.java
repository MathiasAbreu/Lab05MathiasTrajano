package br.com.lab05.saga.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import br.com.lab05.saga.comparators.FornecedorComparator;
import br.com.lab05.saga.model.Cliente;
import br.com.lab05.saga.model.Combo;
import br.com.lab05.saga.model.Fornecedor;
import br.com.lab05.saga.model.Produto;
import br.com.lab05.saga.model.ProdutoSimples;

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
	private final HashMap<String, Fornecedor> fornecedores;
	
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
	 * @return Retorna o nome do {@link Fornecedor} recém cadastrado.
	 * 
	 * @throws RuntimeException Gera uma exceção caso haja tentativa de cadastrar um fornecedor que já exista, 
	 * como também repassa exceções do tipo {@link NullPointerException} ou do tipo {@link IllegalArgumentException} 
	 * que podem ser geradas no momento da criação do objeto do tipo {@link Fornecedor}.
	 */
	public String adicionarFornecedor(String nome,String email,String telefone) {
		
		if(!fornecedores.containsKey(nome)) {
			
			fornecedores.put(nome,new Fornecedor(nome,email,telefone));
			return nome;
		}
		
		throw new NullPointerException("Erro no cadastro de fornecedor: fornecedor ja existe.");
	}

	/**
	 * Método responsável pela busca de um possivel fornecedor no sistema.
	 * 
	 * @param nome nome do fornecedor
	 * 
	 * @return Retorna uma representação do {@link Fornecedor}.
	 * 
	 * @throws NullPointerException Gera essa exceção caso o fornecedor não seja encontrado.
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
	 */
	public String listarFornecedores() {
		
		if(fornecedores.isEmpty()) {
			throw new NullPointerException("Não há fornecedores cadastrados!");
		}
		Set<String> chaves = fornecedores.keySet();
		ArrayList<Fornecedor> fornecedors = new ArrayList<>();
		
		for (String chave : chaves) {
			
			fornecedors.add(fornecedores.get(chave));
		}
		
		FornecedorComparator comparador = new FornecedorComparator();
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
	 * @param atributo novo dado a ser alterado
	 * @param novoDado novo valor do dado a ser alterado
	 * 
	 * @return Retorna uma confirmação da edição do {@link Fornecedor}.
	 * 
	 * @throws NullPointerException Caso tentem editar algum dado de um fornecedor que não esteja cadastrado,
	 * esta exceção é gerada.
	 * 
	 * @throws IllegalArgumentException Caso tentem editar algum dado inexistente em um cliente, ou algum
	 * dos parâmetros passados sejam nulos ou vazios, está exceção é gerada.
	 * 
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
	 * @return Retorna o nome do {@link Fornecedor} recém removido.
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
				
		throw new NullPointerException("Impossível remover um fornecedor que não estava cadastrado!");
	}

	/** Método responsável pelo cadastramento de um novo produto no sistema. Ele recebe todo os dados do novo produto
	 * assim como o fornecedor de tal {@linkplain ProdutoSimples}. Caso algum dos dados seja inválido, ou o fornecedor em questão 
	 * não exista, exceções são geradas.
	 * 
	 * @param fornecedor nome do fornecedor do produto.
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @param preco preço do produto
	 * 
	 * @return Repassa o retorno vindo do método de cadastro da classe {@link Fornecedor}.
	 * 
	 * @throws IllegalArgumentException Exceção gerada caso o nome do fornecedor seja vazio ou nulo.
	 * @throws NullPointerException Exceção gerada caso o fornecedor não seja encontrado.
	 * 
	 */
	public String adicionarProduto(String fornecedor,String nome, String descricao, double preco) {
		
		if(fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		
		if(fornecedores.containsKey(fornecedor)) {
			
			return fornecedores.get(fornecedor).adicionarProduto(nome,descricao,preco);
		}
		
		throw new NullPointerException("Erro no cadastro de produto: fornecedor nao existe.");
	}

	/**
	 * Método que busca determinado produto de determinado {@link Produto}. Este método pode gerar exceções advindas da passagem 
	 * inválida dos parâmetros.
	 * 
	 * @param nome nome do produto a ser buscado
	 * @param descricao descrição do produto a ser buscado
	 * @param fornecedor fornecedor de tal produto
	 * 
	 * @return Retorna a representação, em String, do produto.
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o nome do fornecedor seja vazio ou nulo.
	 * @throws NullPointerException Essa exceção é gerada caso o fornecedor de tal produto não seja encontrado.
	 */
	public String buscarProduto(String nome, String descricao, String fornecedor) {
		
		if(fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		
		if(fornecedores.containsKey(fornecedor)) {
			
			try {
				
				return fornecedores.get(fornecedor).buscarProduto(nome,descricao).toString();
			
			} catch (NullPointerException | IllegalArgumentException err) {
				
				throw new RuntimeException("Erro na exibicao de produto: " + err.getMessage());
			}
		}
		
		throw new NullPointerException("Erro na exibicao de produto: fornecedor nao existe.");
	}

	/** Método responsável pela edição de determinado produto de um determinado fornecedor.
	 * 
	 * @param nome nome do produto a ser editado.
	 * @param descricao descricao do produto a ser editado.
	 * @param fornecedor fornecedor do produto
	 * @param novoPreco novo preço do produto
	 * 
	 * @return Repassa o retorno advindo do metodo de edição do produto na classe {@linkplain Cliente}.
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o nome do fornecedor seja vazio ou nulo.
	 * @throws NullPointerException Essa exceção é gerada caso o fornecedor de tal produto não seja encontrado.
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

	/**
	 * Método que lista todos os produtos de um determinado fornecedor, em ordem alfabética.
	 * 
	 * @param fornecedor produtos do fornecedor a ser retornado
	 * 
	 * @return Retorna uma representação de todos os produtos de um fornecedor, ordenados alfabeticamente.não ex
	 * 
	 * @throws NullPointerException Esta exceção é gerada caso o fornecedor não seja encontrado.
	 */
	public String exibirProdutos(String fornecedor) {

		if(fornecedores.containsKey(fornecedor)) {
			
			return fornecedores.get(fornecedor).listarProdutos();
		}
		
		throw new NullPointerException("Fornecedor não encontrado!");
	}
	
	/**
	 * Método reponsável pela listagem de todos os produtos cadastrados no sistema, de todos os fornecedores que existirem.
	 * 
	 * @return Retorna um representação de todos os produtos do sistema.
	 */
	public String exibirProdutos() {
		
		Set<String> chaves = fornecedores.keySet();
		ArrayList<Fornecedor> fornecedors = new ArrayList<>();
		
		for (String chave : chaves) {
			
			fornecedors.add(fornecedores.get(chave));
		}
		
		FornecedorComparator comparador = new FornecedorComparator();
		Collections.sort(fornecedors,comparador);
		
		String retorno = fornecedors.get(0).listarProdutos();
		
		for(int i = 1;i < fornecedors.size();i++) {
			if(fornecedors.get(i).existirProdutos() == 0) {
				continue;
			}
			retorno += " | " + fornecedors.get(i).listarProdutos();
		}
		
		return retorno;
	}

	/** Método responsável pela remoção de determinado produto do sistema.
	 * 
	 * @param nome nome do produto a ser removido
	 * @param descricao descrição do produto a ser removido
	 * @param fornecedor fornecedor do produto a ser removido
	 * 
	 * @return Repassa o retorno advindo do método de remoção da classe {@link Fornecedor}.
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o nome do fornecedor seja vazio ou nulo.
	 * @throws NullPointerException Essa exceção é gerada caso o fornecedor de tal produto não seja encontrado.
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

	/** Método responsável por adicionar um novo combo no sistema. Ele recebe todos os parametros necessários para 
	 * criação de tal combo, tais como nome, descricao e o fornecedor que fornece tal produto. Também pode gerar ex
	 * ceções caso algum irregularidade seja encontrada.
	 * 
	 * @param fornecedor fornecedor do combo
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fator fator de desconto do combo
	 * @param produtos produtos que compõe o combo
	 * 
	 * @throws IllegalArgumentException Esta exceção é gerada caso algum dos parâmetros seja nulo ou inválido.
	 * @throws NullPointerException Exceção gerada pela inserção de um fornecedor inexistente no sistema.
	 * 
	 */
	public void adicionarCombo(String fornecedor, String nome, String descricao, double fator, String produtos) {
		if(fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.");
		
		if(fornecedores.containsKey(fornecedor)) {
			
			fornecedores.get(fornecedor).adicionarCombo(nome,descricao,fator,produtos);
			
		} else 
			throw new NullPointerException("Erro no cadastro de combo: fornecedor nao existe.");
		
	}

	/** Método responsável pela edição de um combo no sistema, devido as limitações impostas, somente o fator de 
	 * desconto pode ser alterado.
	 * 
	 * @param nome nome do combo
	 * @param descricao descrição do combo
	 * @param fornecedor fornecedor do combo
	 * @param novoFator novo fator de desconto
	 * 
	 * @throws IllegalArgumentException Esta exceção é gerada caso algum dos parâmetros inseridos seja nulo ou inválido.
	 * @throws NullPointerException Esta exceção é gerada caso seja inserido um {@link Fornecedor} que não existe.
	 */
	public void editarCombo(String nome, String descricao, String fornecedor, double novoFator) {

		if(fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro na edicao de combo: fornecedor nao pode ser vazio ou nulo.");
		
		if(fornecedores.containsKey(fornecedor)) {
			
			fornecedores.get(fornecedor).editarCombo(nome, descricao, novoFator);
		}
		else
			throw new NullPointerException("Erro na edicao de combo: fornecedor nao existe.");
		
	}
	
	public double getPrecoProduto(String fornecedor,String nome,String descricao) {
		
		Produto produto = fornecedores.get(fornecedor).buscarProduto(nome, descricao);
		
		if(produto.getClass() == ProdutoSimples.class) {
			ProdutoSimples produtoSimples = (ProdutoSimples) produto;
			return produtoSimples.getPreco();
		}
		
		if(produto.getClass() == Combo.class) {
			Combo combo = (Combo) produto;
			return combo.calculaPreco();
		}
		
		throw new NullPointerException("Produto não encontrado!");
	}
}