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
public class DataCompraComparator implements Comparator<Compra> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Compra compra01, Compra compra02) {
		
		String[] data01 = compra01.getDataCompra().split("/");
		String[] data02 = compra02.getDataCompra().split("/");
		
		int posicao = (data01[2] + data01[1] + data01[0]).compareTo(data02[2] + data02[1] + data02[0]);
		
		if(posicao > 0)
			return 1;
		if(posicao < 0)
			return -1;
		
		return 0;
	}
}
