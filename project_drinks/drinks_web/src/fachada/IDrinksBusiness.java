package fachada;

import java.util.List;

import javax.security.auth.login.LoginException;

import erro.GeralException;
import basicas.Cliente;
import basicas.Estabelecimento;

public interface IDrinksBusiness {
	
	//Estabelecimento
	public void salvarEstabelecimento(Estabelecimento entidade) throws GeralException;

	public void alterarEstabelecimento(Estabelecimento entidade) throws GeralException;
	
	public void excluirEstabelecimento(Estabelecimento entidade) throws GeralException;

	public List<Estabelecimento> consultarTodosOsEstabelecimentos();

	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException;	
	
	public void inserirCLiente(Cliente cliente);

	public static IDrinksBusiness getInstacia() {
		// TODO Auto-generated method stub
		return null;
	}

}