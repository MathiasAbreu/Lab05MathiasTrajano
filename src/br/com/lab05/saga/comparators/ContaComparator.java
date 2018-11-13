package br.com.lab05.saga.comparators;

import java.util.Comparator;

import br.com.lab05.saga.model.Conta;

/**
 * Classe utilizada para comparar objetos do tipo {@link Conta} com função principal de ordenar as contas por seus fornecedores no momento 
 * da impressão dos mesmos em alguns métodos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class ContaComparator implements Comparator<Conta> {

	/**
	 * Método que recebe duas contas, e compara os nomes de seus fornecedores para verificar qual dos dois deve vir primeiro na ordem alfabética.
	 * 
	 * @param conta01 Primeira {@link Conta} a ser comparada.
	 * @param conta02 Segunda {@link Conta} a ser comparada.
	 * 
	 * @return Retorna um valor a ser utilizado na ordenação.
	 */
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
