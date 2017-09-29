package br.com.drinks.business;

import java.util.List;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.fachada.DrinksBusiness;


public class ClienteBusiness extends BasicBusiness<Cliente> {
	
	private static ClienteBusiness instancia;
	
	public static ClienteBusiness getInstancia() {
		if(instancia == null){
			instancia = new ClienteBusiness();
		}
		return instancia;
	}

	@Override
	public void insert(Cliente entity) {
		DrinksBusiness.getInstancia().inserirCLiente(entity);
		
	}

	@Override
	public void remove(Cliente entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> list() {
		return DrinksBusiness.getInstancia().consultarTodosOsClientes();
	}

	@Override
	public void update(Cliente entity) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean login(Cliente cliente) {
		
		boolean loginOK = false;
		
		if((cliente.geteMail() == null || cliente.geteMail().equals(""))){
			//TODO lançar exceção
			return loginOK;
		}
		if(cliente.getSenha() == null || cliente.getSenha().equals("")) {
			//TODO lançar exceção
			return loginOK;
		}
		
		loginOK = DrinksBusiness.getInstancia().loginCliente(cliente);
		return loginOK;
	}

}
