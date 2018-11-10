package br.com.lab05.saga.comparators;

import java.util.Comparator;

import br.com.lab05.saga.model.Compra;

/**
 * Classe utilizada para comparar objetos do tipo {@link Compra} com função principal de ordenar as compras pelos nomes dos seus fornecedores no momento 
 * da impressão dos mesmos em alguns métodos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class FornecedorCompraComparator implements Comparator<Compra> {
	
	/**
	 * Método que recebe duas compras, e compara os nomes de seus fornecedores para verificar qual das duas deve vir primeiro na ordem alfabética.
	 * 
	 * @param compra01 Primeira {@link Compra} a ser comparada.
	 * @param compra02 Segunda {@link Compra} a ser comparada.
	 * 
	 * @return Retorna um valor a ser utilizado na ordenação.
	 */
	@Override
	public int compare(Compra compra01, Compra compra02) {
		
		int posicao = compra01.getNomeFornecedor().compareTo(compra02.getNomeFornecedor());
		
		if(posicao == 0) {
			
			posicao = compra01.getNomeCliente().compareTo(compra02.getNomeCliente());
			
			if(posicao == 0) {
				return compra01.getDescricaoProduto().compareTo(compra02.getDescricaoProduto());
			}
			
			return posicao;
		}
		
		return posicao;
	}

}
