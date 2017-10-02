package br.com.drinks.business;

import java.util.List;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.erro.EntidadeJaExisteException;
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
	public boolean insert(Cliente entity) {
		boolean inseriu = false;

		try {
			Cliente cliente = DrinksBusiness.getInstancia().consultarClientePorEmail(entity);
			if(cliente != null) {
				throw new EntidadeJaExisteException("Cliente com esse email já existe");
			}else {
				DrinksBusiness.getInstancia().inserirCLiente(entity);
				cliente = DrinksBusiness.getInstancia().consultarClientePorEmail(entity);
				if(cliente != null) {
					inseriu = true;
				}
			}
		} catch (EntidadeJaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return inseriu;
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
