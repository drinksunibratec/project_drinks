package dao;

import javax.security.auth.login.LoginException;

import basicas.Estabelecimento;
import dados.genercio.IDAOGenerico;


public interface IDaoEstabelecimento extends IDAOGenerico<Estabelecimento> {
	
	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException;
	
}