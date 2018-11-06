package br.com.lab05.saga.model;

import java.util.HashMap;

/**
 * Classe que contém a representação de um cliente, possui atributos de identificação única dos mesmos, assim como métodos de controle sobre os mesmos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br - 118111726
 *
 */
public class Cliente {
	
	/**
	 * CPF do Cliente.
	 */
	private String cpf;
	
	/**
	 * Nome do cliente.
	 */
	private String nome;
	
	/**
	 * Email do cliente.
	 */
	private String email;
	
	/**
	 * Local de Trabalho do cliente.
	 */
	private String localTrabalho;
	
	private HashMap<String, Conta> contas;
	
	/** 
	 * Construtor que recebe todos os dados essenciais de um cliente, verifica alguma irregularidade em tais dados e por fim instancia um novo cliente.
	 * 
	 * @param cpf cpf do cliente
	 * @param nome nome do cliente
	 * @param email email do cliente
	 * @param localTrabalho local de trabalho do cliente
	 * 
	 * @throws IllegalArgumentException Exceção gerada na tentativa de uma crição de cliente com um CPF de tamanho inválido!.
	 * @throws RuntimeException Exceção gerada na tentativa da criação de um cliente com algum dos dados nulos ou inválidos.
	 * 
	 */
	public Cliente(String cpf, String nome, String email, String localTrabalho) {

		if(cpf == null || cpf.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro do cliente: cpf nao pode ser vazio ou nulo.");
		if(nome == null || nome.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		if(email == null || email.trim().isEmpty()) 
			throw new RuntimeException("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		if(localTrabalho == null || localTrabalho.trim().isEmpty())
			throw new RuntimeException("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		
		if(cpf.length() != 11)
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf invalido.");
		
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localTrabalho = localTrabalho;
		
		contas = new HashMap<>();
	}

	/**
	 * Método que retorna o CPF do cliente.
	 * 
	 * @return cpf do cliente
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Método que retorna o Nome do Cliente.
	 * 
	 * @return o nome do cliente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método que altera o nome do cliente.
	 * 
	 * @param nome nome do cliente
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método que retorna o email do cliente.
	 * 
	 * @return email do cliente
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método que altera o email do cliente.
	 * 
	 * @param email email do cliente
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método que retorna o Local de Trabalho do cliente.
	 * 
	 * @return o local de trabalho do cliente
	 */
	public String getLocalTrabalho() {
		return localTrabalho;
	}

	/**
	 * Método que altera o local de trabalho do cliente.
	 * 
	 * @param localTrabalho local de trabalho do cliente
	 */
	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	/**
	 * Método que retorna uma representação em String de um cliente.
	 * 
	 * @return representação de um cliente
	 */
	@Override
	public String toString() {
		return  nome + " - " + localTrabalho + " - " + email;
	}

	/**
	 * Método responsável por gerar um numero utilizado como indexador em coleções hashs.
	 * 
	 * @return um inteiro gerado a partir do cpf do cliente
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	/**
	 * Método que verifica se dois clientes são iguais analisando se os mesmos possuem CPF's iguais.
	 * 
	 * @param obj um objeto qualquer
	 * 
	 * @return um valor booleano verificando se os clientes são iguais ou não.
	 */
	@Override
	public boolean equals(Object obj) {
		if(getClass().equals(obj.getClass())) {
			
			Cliente other = (Cliente) obj;
			
			if(this.cpf.equals(other.cpf))
				return true;
		}
		return false;
	}

	/**
	 * @param fornecedor
	 * @param data
	 * @param nome_prod
	 * @param desc_prod
	 */
	public void adicionarCompra(String fornecedor, String data, String nome_prod, String desc_prod,double preco) {

		if(contas.containsKey(fornecedor)) {
			contas.get(fornecedor).adicionarCompra(data,nome_prod,desc_prod,preco);
		}
		else {
			contas.put(fornecedor,new Conta(fornecedor));
			contas.get(fornecedor).adicionarCompra(data, nome_prod, desc_prod, preco);
		}
	}

	/**
	 * @param fornecedor
	 * @return
	 */
	public String getDebito(String fornecedor) {
		
		if(contas.containsKey(fornecedor)) {
			
			return contas.get(fornecedor).getDebito();
		}
		else
			throw new NullPointerException("Erro ao recuperar debito: cliente nao tem debito com fornecedor.");
	}

	/**
	 * @return
	 */
	public boolean contemDebitos() {
		if(contas.isEmpty())
			return false;
		return true;
	}
}