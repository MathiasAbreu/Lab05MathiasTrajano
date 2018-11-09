package br.com.lab05.saga.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Classe que representa um conta no sistema, cada {@link Cliente} possui uma Conta de vínculo com um {@link Fornecedor}, 
 * nestas contas ficam armazenados dados de identificação tal como os registros de todas as compras.
 * 
 * @author Mathias Abreu Trajano - mathias.trajano@ccc.ufcg.edu.br
 */
public class Conta {

	/**
	 * Nome do Cliente
	 */
	private String cliente;
	
	/**
	 * Fornecedor do Produto
	 */
	private String fornecedor;
	
	/**
	 * Débito total acumulado.
	 */
	private double debitoTotal;

	/**
	 * Coleção responsável pelo armazenamento das contas.
	 */
	private ArrayList<Compra> compras;
	
	/**
	 * Construtor responsável pela instanciação de uma nova conta, recebe como parâmetro apenas o nome do cliente e 
	 * o nome do fornecedor.
	 * 
	 * @param fornecedor fornecedor do produto
	 * @param cliente cliente
	 * 
	 * @throws IllegalArgumentException Essa exceção é gerada caso o nome do fornecedor informado seja vazio ou nulo.
	 */
	public Conta(String fornecedor,String cliente) {
		
		if(fornecedor == null || fornecedor.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
		
		this.fornecedor = fornecedor;
		this.cliente = cliente;
		
		compras = new ArrayList<>();
	}

	/**
	 * Método que retorna o nome do cliente.
	 * 
	 * @return O nome do cliente.
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * Método que retorna o nome do fornecedor do produto.
	 * 
	 * @return O nome do fornecedor.
	 */
	public String getFornecedor() {
		return fornecedor;
	}

	/**
	 * Método responsável pelo cadastro de uma nova compra, ele também atualiza o valor do débito total. Ele recebe 
	 * como parâmetros todos os dados essenciais de uma compra.
	 * 
	 * @param data data da compra
	 * @param nome_prod nome do produto comprado
	 * @param desc_prod descrição do produto comprado
	 * @param preco preço do produto comprado
	 */
	public void adicionarCompra(String data, String nome_prod, String desc_prod, double preco) {

		compras.add(new Compra(cliente,fornecedor,data,nome_prod,desc_prod,preco));
		debitoTotal += preco;
	}

	/**
	 * Método que retorna o Debito total acumulado da conta
	 * 
	 * @return Retorna o débito total da conta.
	 */
	public String getDebito() {
		
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.00",formatSymbols);
		
		return df.format(debitoTotal);
	}

	/**
	 * Método que lista todas as compras desta conta.
	 * 
	 * @return Retorna uma representação com todos os dados das compras.
	 */
	public String listarCompras() {
		
		String retorno = "";
		for (Compra compra : compras) {
			
			retorno += " | " + compra.toString();
		}
		
		return retorno;
	}

	/**
	 * Método que retorna a coleção com todas as compras cadastradas até o momento.
	 * 
	 * @return Retorna a coleção de armazenamento das compras.
	 */
	public ArrayList<Compra> getCompras() {
		
		return compras;
	}
}
