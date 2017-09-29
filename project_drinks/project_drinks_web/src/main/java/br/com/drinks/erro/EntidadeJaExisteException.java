package br.com.drinks.erro;

public class EntidadeJaExisteException extends Exception {
	
	/**
	 * @author Silvio Cedrim
	 * 
	 */
	private static final long serialVersionUID = 714664893568320470L;

	public EntidadeJaExisteException(String mensagem) {
		super(mensagem);
	}

}
