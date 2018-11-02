/**
 * 
 */
package br.com.lab05.saga.model;

import java.util.Comparator;

/** Classe utilizada para comparar objetos do tipo {@link Produto} com função principal de ordenar os produtos no momento 
 * da impressão dos mesmos em alguns métodos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 * 
 */
public class ProdutoComparator implements Comparator<Produto> {

	/**
	 * Método responsável por comparar os nomes e descrições dos produtos para determinar quais devem vir primeiro na ordem alfabética.
	 * 
	 * @param arg0 Primeiro {@link Produto} a ser comparado.
	 * @param arg1 Segundo {@link Produto} a ser comparado.
	 * 
	 * @return Retorna um número que será utilizado para ordenar corretamente os produtos em ordem alfabética.
	 */
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
