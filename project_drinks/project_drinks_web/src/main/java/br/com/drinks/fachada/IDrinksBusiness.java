package br.com.drinks.fachada;

import br.com.drinks.basicas.Cliente;

public interface IDrinksBusiness {
	
	
	public void inserirCLiente(Cliente cliente);
	
	public boolean loginCliente(Cliente cliente);

}