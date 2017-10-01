package br.com.drinks.business;

import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.dados.genericos.DAOFactory;
import br.com.drinks.erro.LoginInvalidoException;


public class SegurancaBusiness {
	
	
	public Estabelecimento efetuarLogin (String login, String senha)
		throws LoginInvalidoException, LoginException {
			return  DAOFactory.getEstabelecimentoDAO().efetuarLogin(login, senha);		
	}

	public void inserirEstabelecimento (Estabelecimento entidade){
		 DAOFactory.getEstabelecimentoDAO().inserir(entidade);
	} 
}
