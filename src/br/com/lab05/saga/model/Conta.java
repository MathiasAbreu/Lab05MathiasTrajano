package br.com.lab05.saga.model;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class Conta {

	private String fornecedor;

	private ArrayList<Compra> compras;
	
	public Conta(String fornecedor) {
		
		if(fornecedor == null || fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
		
		this.fornecedor = fornecedor;
		
		compras = new ArrayList<>();
	}

	/**
	 * @return the fornecedor
	 */
	public String getFornecedor() {
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	/**
	 * @param data
	 * @param nome_prod
	 * @param desc_prod
	 * @param preco
	 */
	public void adicionarCompra(String data, String nome_prod, String desc_prod, double preco) {

		compras.add(new Compra(data,nome_prod,desc_prod,preco));
	}
	
	
}
