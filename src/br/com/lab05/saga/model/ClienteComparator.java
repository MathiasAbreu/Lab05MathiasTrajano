package br.com.lab05.saga.model;

import java.util.Comparator;

/**
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class ClienteComparator implements Comparator<Cliente> {
	
	@Override
	public int compare(Cliente arg0, Cliente arg1) {
		
		int posicao = arg0.getNome().compareTo(arg1.getNome());
		
		if(posicao > 0) {
			return 1;
		}
		if(posicao < 0) {
			return -1;
		}
		
		return 0;
	}

}