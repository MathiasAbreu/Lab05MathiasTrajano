/**
 * 
 */
package br.com.lab05.saga.comparators;

import java.util.Comparator;

import br.com.lab05.saga.model.Compra;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class FornecedorCompraComparator implements Comparator<Compra> {
	
	@Override
	public int compare(Compra compra01, Compra compra02) {
		
		int posicao = compra01.getNomeFornecedor().compareTo(compra02.getNomeFornecedor());
		
		if(posicao > 0)
			return 1;
		if(posicao < 0)
			return -1;
		
		return 0;
	}

}
