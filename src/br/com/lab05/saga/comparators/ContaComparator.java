/**
 * 
 */
package br.com.lab05.saga.comparators;

import java.util.Comparator;

import br.com.lab05.saga.model.Conta;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class ContaComparator implements Comparator<Conta> {

	@Override
	public int compare(Conta conta01, Conta conta02) {
		
		int posicao = conta01.getFornecedor().compareTo(conta02.getFornecedor());
		
		if(posicao > 0)
			return 1;
		if(posicao < 0)
			return -1;
		
		return 0;
	}

}
