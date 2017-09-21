package br.com.drinks.fachada;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.dados.genericos.DAOFactory;



public class DrinksBusiness implements IDrinksBusiness{

	private static IDrinksBusiness fachada;

	public static IDrinksBusiness getInstancia() {
		if (fachada == null) {
			fachada = new DrinksBusiness();
		}
		return fachada;
	}

	
	//---------Cliente-------
	@Override
	public void inserirCLiente(Cliente entidade) {
		DAOFactory.getClienteDAO().inserir(entidade);
		
	}


	@Override
	public boolean loginCliente(Cliente cliente) {
		return DAOFactory.getClienteDAO().login(cliente);
	}

	
	

	


}