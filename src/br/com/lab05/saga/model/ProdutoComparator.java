/**
 * 
 */
package br.com.lab05.saga.model;

import java.util.Comparator;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class ProdutoComparator implements Comparator<Produto> {

	@Override
	public int compare(Produto arg0, Produto arg1) {
		
		int posicao = arg0.getNome().compareTo(arg1.getNome());
		
		if(posicao == 0) {
			
			posicao = arg0.getDescricao().compareTo(arg1.getDescricao());
			
			if(posicao > 0) {
				return 1;
			}
			if(posicao < 0) {
				return -1;
			}
			return 0;
		}
		
		if(posicao > 0) {
			return 1;
		}
		if(posicao < 0) {
			return -1;
		}
		
		return 0;
	}

}
