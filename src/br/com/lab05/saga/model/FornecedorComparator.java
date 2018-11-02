/**
 * 
 */
package br.com.lab05.saga.model;

import java.util.Comparator;

public class FornecedorComparator<T> implements Comparator<Fornecedor> {

	@Override
	public int compare(Fornecedor fornecedor01, Fornecedor fornecedor02) {
		
		int posicao = fornecedor01.getNome().compareTo(fornecedor02.getNome());
		
		if(posicao > 0)
			return 1;
		if(posicao < 0)
			return -1;
		
		return 0;
	}
}