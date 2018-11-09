package br.com.lab05.saga.comparators;

import java.util.Comparator;

import br.com.lab05.saga.model.Cliente;
/** 
 * Classe utilizada para comparar objetos do tipo {@link Cliente} com função principal de ordenar os clientes no momento 
 * da impressão dos mesmos em alguns métodos.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class ClienteComparator implements Comparator<Cliente> {
	
	/**
	 * Método que recebe dois clientes, e compara seus nomes para verificar qual dos dois deve vir primeiro na ordem alfabética.
	 * 
	 * @param arg0 Primeiro {@link Cliente} a ser comparado.
	 * @param arg1 Segundo {@link Cliente} a ser comparado.
	 * 
	 * @return Retorna um valor a ser utilizado na ordenação.
	 */
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