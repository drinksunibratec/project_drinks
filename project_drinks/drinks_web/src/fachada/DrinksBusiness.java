package fachada;

import java.util.List;

import javax.security.auth.login.LoginException;

import basicas.Cliente;
import basicas.Estabelecimento;
import dados.DAOFactory;
import erro.GeralException;


public class DrinksBusiness implements IDrinksBusiness{

	private static IDrinksBusiness fachada;

	public static IDrinksBusiness getInstancia() {
		if (fachada == null) {
			fachada = new DrinksBusiness();
		}
		return fachada;
	}

	//Estabelecimento	
	@Override
	public void salvarEstabelecimento(Estabelecimento entidade) throws GeralException {
		DAOFactory.getEstabelecimentoDAO().inserir(entidade);
		
	}

	@Override
	public void alterarEstabelecimento(Estabelecimento entidade) throws GeralException {
		DAOFactory.getEstabelecimentoDAO().alterar(entidade);
		
	}

	@Override
	public List<Estabelecimento> consultarTodosOsEstabelecimentos() {
		return DAOFactory.getEstabelecimentoDAO().consultarTodos();
	}

	
	@Override
	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException {
		return DAOFactory.getEstabelecimentoDAO().efetuarLogin(eMail, senha);
	}	
	

	//Cliente
	@Override
	public void inserirCLiente(Cliente entidade) {
		DAOFactory.getClienteDAO().inserir(entidade);
		
	}

	@Override
	public void excluirEstabelecimento(Estabelecimento entidade) throws GeralException {
		// TODO Auto-generated method stub
		
	}

	
	

	


}