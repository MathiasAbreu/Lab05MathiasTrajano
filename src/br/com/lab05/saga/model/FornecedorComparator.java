package br.com.lab05.saga.model;

import java.util.Comparator;

/**
 * Classe utilizada para comparar objetos do tipo {@link Fornecedor} com função principal de ordenar os fornecedores no momento 
 * da impressão dos mesmos em alguns métodos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 * 
 *
 */
public class FornecedorComparator<T> implements Comparator<Fornecedor> {

	/**
	 * Método com a função de comparar dois fornecedores para saber qual deve vir primeiro na ordem alfabética. O método 
	 * utiliza os nomes dos mesmos para ordena-lós nas coleções.
	 * 
	 * @param fornecedor01 Primeiro {@link Fornecedor} a ser comparado.
	 * @param fornecedor02 Segundo {@link Fornecedor} a ser comparado.
	 * 
	 * @return Retorna um número utilizado para determinar a ordem de ordenação.
	 */
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