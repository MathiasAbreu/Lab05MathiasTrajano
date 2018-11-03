package br.com.lab05.saga.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import br.com.lab05.saga.model.Cliente;
import br.com.lab05.saga.model.ClienteComparator;

/**
 * Representação do controle de clientes, esta classe possui a coleção responsável pelo armazenamento 
 * e os metodos que gerenciam tais clientes.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br - 118111726
 *
 */
public class ControllerClienteSaga {
	
	/**
	 * Coleção responsável pelo armazenamento dos clientes.
	 */
	private HashMap<String,Cliente> clientes;
	
	/**
	 * Cosntrutor responsável pela inicialização da classe.
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
	
	/** Método que lista todos os clientes cadastrados em ordem alfabetica.
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
}