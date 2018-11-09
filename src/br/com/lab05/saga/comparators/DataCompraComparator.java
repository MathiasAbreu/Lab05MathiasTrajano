package br.com.lab05.saga.comparators;

import java.util.Comparator;

import br.com.lab05.saga.model.Compra;

/**
 * Classe utilizada para comparar objetos do tipo {@link Compra} com função principal de ordenar as compras pelas suas datas no momento 
 * da impressão dos mesmos em alguns métodos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class DataCompraComparator implements Comparator<Compra> {

	/**
	 * Método que recebe duas compras, e compara as datas de suas compras para verificar qual das duas deve vir primeiro na ordem alfabética.
	 * 
	 * @param compra01 Primeira {@link Compra} a ser comparada.
	 * @param compra02 Segunda {@link Compra} a ser comparada.
	 * 
	 * @return Retorna um valor a ser utilizado na ordenação.
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
