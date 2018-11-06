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
}
