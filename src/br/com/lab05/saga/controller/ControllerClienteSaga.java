package br.com.lab05.saga.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import br.com.lab05.saga.comparators.ClienteComparator;
import br.com.lab05.saga.comparators.ClienteCompraComparator;
import br.com.lab05.saga.comparators.DataCompraComparator;
import br.com.lab05.saga.comparators.FornecedorCompraComparator;
import br.com.lab05.saga.model.Cliente;
import br.com.lab05.saga.model.Compra;

/**
 * Representação do controle de clientes, esta classe possui a coleção responsável pelo armazenamento 
 * e os metodos que gerenciam tais clientes.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br - 118111726
 *
 */
public class ControllerClienteSaga {
		
	/**
	 * Comparador estratégico utilizado para ordenar de formas diversas em tempo de execução.
	 */
	private Comparator<Compra> comparador;
	
	/**
	 * Coleção responsável pelo armazenamento dos clientes.
	 */
	private static HashMap<String,Cliente> clientes;
	
	/**
	 * Construtor responsável pela inicialização da classe.
	 */
	public ControllerClienteSaga() {
		
		clientes = new HashMap<>();
	}
	
	/**
	 * Método responsável pelo cadastro de clientes novos, este método recebe todos os dados
	 * do novo cliente, gera o cliente e por fim armazena no sistema. Pode haver falhas no método geradas 
	 * por passagem incorreta de parametros do cliente.
	 * 
	 * @param cpf cpf do cliente.
	 * @param nome nome do cliente.
	 * @param email email do cliente.
	 * @param localTrabalho local de trabalho do cliente.
	 * 
	 * @return Retorna o cpf do cliente recém cadastrado.
	 * 
	 * @throws RuntimeException Este método pode repassar exceções geradas na classe cliente no momento da
	 *  criação de um novo cliente. A passagem de parametros vazios ou inválidos pode gerar tais exceções. 
	 *  Essas exceções podem ser do tipo {@link NullPointerException} caso algum dos parametros seja nulo, 
	 *  ou do tipo {@link IllegalArgumentException} caso algum parâmetro seja vazio. A exceção também pode ser 
	 *  gerada caso o cliente já esteja cadastrado.
	 *  
	 */
	public String cadastrarCliente(String cpf,String nome,String email,String localTrabalho) throws RuntimeException {
		
		if(!clientes.containsKey(cpf)) {
			
			clientes.put(cpf,new Cliente(cpf,nome,email,localTrabalho));
			return cpf;
		}
		
		throw new RuntimeException("Erro no cadastro do cliente: cliente ja existe.");
	}
	
	/**
	 * Este método faz uma busca e retorna algum cliente que esteja cadastrado no sistema. 
	 * 
	 * @param cpf cpf do cliente a ser buscado
	 * 
	 * @return Retorna a representação do cliente, se for encontrado.
	 * 
	 * @throws NullPointerException Gera essa exceção caso o {@link Cliente} não esteja cadastrado.
	 */
	public String buscarCliente(String cpf) {
		
		if(clientes.containsKey(cpf)) {
			
			return clientes.get(cpf).toString();
		}
		
		throw new NullPointerException("Erro na exibicao do cliente: cliente nao existe.");
	}
	
	/**
	 * Método que realiza uma busca, e depois altera algum dos dados do cliente, 
	 * tanto o cpf, como o dado a ser alterado e seu novo conteudo são passados no 
	 * momento da chamado do método. Caso tentem editar algum dado inexistente ou de algum 
	 * cliente não cadastrado, são geradas exceções.
	 * 
	 * @param cpf cpf do cliente a ser editado
	 * @param atributo dado a ser alterado
	 * @param novoDado novo valor do dado a ser alterado.
	 * 
	 * @return Retorna uma confirmação da edição do cliente.
	 * 
	 * @throws NullPointerException Caso tentem editar algum dado de um cliente que não está cadastrado, 
	 * esta exceção é gerada.
	 * 
	 * @throws IllegalArgumentException Caso algum dos dados seja vazio ou nulo, ou seja um dado inexistente 
	 * esta exceção é gerada.
	 */
	public String editarCliente(String cpf,String atributo,String novoDado) {
		
		if(clientes.containsKey(cpf)) {
			
			Cliente cliente = clientes.get(cpf);
			
			if(atributo.trim().isEmpty())
				throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
			if(novoDado.trim().isEmpty())
				throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
			
			if(atributo.equals("nome") || atributo.equals("email") || atributo.equals("localizacao")) {
				
				if(atributo.equals("nome")) {
					cliente.setNome(novoDado);
				}
				if(atributo.equals("email")) {
					cliente.setEmail(novoDado);
				}
				if(atributo.equals("localizacao")) {
					cliente.setLocalTrabalho(novoDado);
				}
				
				clientes.put(cpf,cliente);
				return "Dado alterado com sucesso!";
			}
			
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");
		}
		else
			throw new NullPointerException("Erro na edicao do cliente: cliente nao existe.");
	}
	
	/** 
	 * Método que lista todos os clientes cadastrados em ordem alfabetica.
	 * 
	 * @return Retorna uma representação de todos os clientes em ordem alfabética.
	 * 
	 * @throws NullPointerException Gera um erro caso não haja clientes cadastrados para serem listados.
	 */
	public String listarClientes() {
	
		if(clientes.isEmpty())
			throw new NullPointerException("Não há clientes cadastrados no sistema!");
		
		Set<String> chaves = clientes.keySet();
		ArrayList<Cliente> clients = new ArrayList<>();
		
		for (String chave : chaves) {
			
			clients.add(clientes.get(chave));
		}
		
		ClienteComparator comparador = new ClienteComparator();
		Collections.sort(clients,comparador);
		
		String retorno = clients.get(0).toString();
		
		for (int i = 1; i < clients.size(); i++) {
			
			retorno += " | " + clients.get(i).toString();

		}
		
		return retorno;
	}

	/**
	 * Este método remove algum cliente que esteja cadastrado no sistema.
	 * 
	 * @param cpf cpf do cliente a ser removido.
	 * 
	 * @return Retorna o cpf do cliente recém removido.
	 * 
	 * @throws NullPointerException Caso o cliente não esteja cadastrado no sistema, um erro é gerado pois não há como excluir um 
	 * cliente não cadastrado!
	 * 
	 */
	public String removerCliente(String cpf) {
		
		if(clientes.containsKey(cpf)) {
			
			clientes.remove(cpf);
			return cpf;
		}
		
		throw new NullPointerException("Impossível remover um cliente que não estava cadastrado!");
	}

	/**
	 * Método responsável pelo cadastro de uma nova compra no sistema, este método recebe todos os dados essenciais para a 
	 * nova compra. O método verifica se já existe alguma conta para colocar a nova compra, se não existir uma nova conta 
	 * deverá ser gerada.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor fornecedor do produto
	 * @param data data da compra
	 * @param nome_prod nome do produto
	 * @param desc_prod descrição do produto
	 * @param preco preço do produto
	 * 
	 * @throws NullPointerException Está exceção é gerada caso o cliente não seja localizado no sistema.
	 */
	public void adicionarConta(String cpf, String fornecedor, String data, String nome_prod, String desc_prod,double preco) {
		
		if(clientes.containsKey(cpf)) {
			
			clientes.get(cpf).adicionarCompra(fornecedor,data,nome_prod,desc_prod,preco);
		}
		else
			throw new NullPointerException("Erro ao cadastrar compra: cliente nao existe.");
		
	}

	/**
	 * Método que retorna o débito total de determinada conta.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor fornecedor do cliente
	 * 
	 * @return Retorna o valor total do débito da conta.
	 * 
	 * @throws IllegalArgumentException Está exceção é gerada caso o cpf inserido seja inválido.
	 * @throws NullPointerException Essa exceção é gerada caso o cliente não seja encontrado.
	 */
	public String getDebito(String cpf, String fornecedor) {
		
		if(cpf == null || cpf.trim().isEmpty() || cpf.length() != 11)
			throw new IllegalArgumentException("Erro ao recuperar debito: cpf invalido.");
		
		if(clientes.containsKey(cpf)) {
			
			return clientes.get(cpf).getDebito(fornecedor);

		}
		else
			throw new NullPointerException("Erro ao recuperar debito: cliente nao existe.");
	}

	/**
	 * Método responsável pela exibição das compras de determinada conta.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor fornecedor do produto
	 * 
	 * @return Retorna um representação textual de todas as compras de determinada conta.
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o cpf informado seja considerado inválido.
	 * @throws NullPointerException Essa exceção é gerada caso o cliente não seja encontrado no sistema.
	 */
	public String exibirContas(String cpf, String fornecedor) {
		
		if(cpf == null || cpf.trim().isEmpty() || cpf.length() != 11)
			throw new IllegalArgumentException("Erro ao exibir conta do cliente: cpf invalido.");
		
		if(clientes.containsKey(cpf)) {
			
			return "Cliente: " + clientes.get(cpf).getNome() + " | " + clientes.get(cpf).exibirContas(fornecedor);
		}
		else
			throw new NullPointerException("Erro ao exibir conta do cliente: cliente nao existe.");
	}

	/**
	 * Este método exibe todas as contas de um cliente, de todos os fornecedores.
	 * 
	 * @param cpf cpf do cliente
	 * 
	 * @return Retorna uma representação textual de todas as contas de determinado cliente.
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o cpf informado seja considerado inválido.
	 * @throws NullPointerException Essa exceção é gerada caso o cliente não seja encontrado no sistema.
	 */
	public String exibirContas(String cpf) {
		
		if(cpf == null || cpf.trim().isEmpty() || cpf.length() != 11)
			throw new IllegalArgumentException("Erro ao exibir contas do cliente: cpf invalido.");
		
		if(clientes.containsKey(cpf)) {
			
			return "Cliente: " + clientes.get(cpf).getNome() + clientes.get(cpf).exibirContas();
		}
		else
			throw new NullPointerException("Erro ao exibir contas do cliente: cliente nao existe.");
	}

	/**
	 * Esse método é responsável pela quitação de um débito de uma conta, onde o processo consiste em deletar a 
	 * conta do sistema, excluindo assim, todos os seus dados.
	 * 
	 * @param cpf cpf do cliente
	 * @param fornecedor fornecedor do produto
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o cpf seja nulo ou inválido.
	 * @throws NullPointerException Essa exceção é gerada caso o cliente não seja encontrado no sistema.
	 */
	public void quitarDebito(String cpf, String fornecedor) {
		
		if(cpf == null || cpf.trim().isEmpty())
			throw new IllegalArgumentException("Erro no pagamento de conta: cpf nao pode ser vazio ou nulo.");
		if(cpf.length() != 11)
			throw new IllegalArgumentException("Erro no pagamento de conta: cpf invalido.");
	
		if(clientes.containsKey(cpf)) {
			
			clientes.get(cpf).quitarDebito(fornecedor);
		}
		else
			throw new NullPointerException("Erro no pagamento de conta: cliente nao existe.");
	}

	/**
	 * Método responsável por setar o comparador utilizado na ordenação das compras do sistema.
	 * 
	 * @param criterio criteiro de ordenação desejado
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o critério seja vazio, nulo ou não esteja disponível 
	 * no sistema.
	 */
	public void ordenarPor(String criterio) {
		
		if(criterio == null || criterio.trim().isEmpty())
			throw new IllegalArgumentException("Erro na listagem de compras: criterio nao pode ser vazio ou nulo.");
		
		if(criterio.equals("Cliente") || criterio.equals("Fornecedor") || criterio.equals("Data")) {
			if(criterio.equals("Cliente"))
				comparador = new ClienteCompraComparator();
			
			if(criterio.equals("Fornecedor"))
				comparador = new FornecedorCompraComparator();
				
			if(criterio.equals("Data"))
				comparador = new DataCompraComparator();
		}
		else
			throw new IllegalArgumentException("Erro na listagem de compras: criterio nao oferecido pelo sistema.");
	}

	/**
	 * Método que lista todas as compras cadastradas no sistema.
	 * 
	 * @return Retorna uma representação de todas as compras cadastradas no sistema.
	 * 
	 * @throws NullPointerException Exceção gerada caso o comparador não esteja instanciado 
	 * corretamente.
	 */
	public String listarCompras() {
		
		Set<String> chaves = clientes.keySet();

		ArrayList<Compra> listaCompras = new ArrayList<>();
		
		for (String chave : chaves) {
			
			listaCompras.addAll(clientes.get(chave).getCompras());
		}
		
		Collections.sort(listaCompras,comparador);
				
		if(comparador.getClass() == ClienteCompraComparator.class)
			return listarPorCliente(listaCompras);
		
		if(comparador.getClass() == FornecedorCompraComparator.class)
			return listarPorFornecedor(listaCompras);
		
		if(comparador.getClass() == DataCompraComparator.class)
			return listarPorData(listaCompras);
		
		throw new NullPointerException("Ocorreu um erro na listagem!");
	}

	/**
	 * Método que lista todas as compras pelas suas datas.
	 * 
	 * @param listaCompras Coleção com todas as compras já ordenadas.
	 * 
	 * @return Retorna um representação de todas as compras, ordenadas pelas datas.
	 */
	private String listarPorData(ArrayList<Compra> listaCompras) {
		
		String retorno = String.format("%s, %s, %s, %s",listaCompras.get(0).getDataCompra(),listaCompras.get(0).getNomeCliente(),listaCompras.get(0).getNomeFornecedor(),listaCompras.get(0).getDescricaoProduto());

		for (int i = 1; i < listaCompras.size(); i++) {
			
			retorno += " | " + String.format("%s, %s, %s, %s",listaCompras.get(i).getDataCompra(),listaCompras.get(i).getNomeCliente(),listaCompras.get(i).getNomeFornecedor(),listaCompras.get(i).getDescricaoProduto());

		}
		
		return retorno;
	}

	/**
	 * Método que lista todas as compras pelos seus fornecedores.
	 * 
	 * @param listaCompras Coleção com todas as compras já ordenadas.
	 * 
	 * @return Retorna uma representação de todas as compras, ordenadas pelos nomes dos fornecedores.
	 */
	private String listarPorFornecedor(ArrayList<Compra> listaCompras) {
		
		String retorno = String.format("%s, %s, %s, %s",listaCompras.get(0).getNomeFornecedor(),listaCompras.get(0).getNomeCliente(),listaCompras.get(0).getDescricaoProduto(),listaCompras.get(0).getDataCompra());

		for (int i = 1; i < listaCompras.size(); i++) {
			
			retorno += " | " + String.format("%s, %s, %s, %s",listaCompras.get(i).getNomeFornecedor(),listaCompras.get(i).getNomeCliente(),listaCompras.get(i).getDescricaoProduto(),listaCompras.get(i).getDataCompra());

		}
		
		return retorno;
	}

	/**
	 * Método que lista todas as compras pelos seus clientes.
	 * 
	 * @param listaCompras Coleção com todas as compras já ordenadas.
	 * 
	 * @return Retorna uma representação de todas as compras, ordenadas pelos nomes de seus clientes.
	 */
	private String listarPorCliente(ArrayList<Compra> listaCompras) {
		
		String retorno = String.format("%s, %s, %s, %s",listaCompras.get(0).getNomeCliente(),listaCompras.get(0).getNomeFornecedor(),listaCompras.get(0).getDescricaoProduto(),listaCompras.get(0).getDataCompra());

		for (int i = 1; i < listaCompras.size(); i++) {
			
			retorno += " | " + String.format("%s, %s, %s, %s",listaCompras.get(i).getNomeCliente(),listaCompras.get(i).getNomeFornecedor(),listaCompras.get(i).getDescricaoProduto(),listaCompras.get(i).getDataCompra());

		}
		
		return retorno;
	}
}