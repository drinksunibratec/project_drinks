package dados;

import javax.security.auth.login.LoginException;

import basicas.Estabelecimento;
import dados.genericos.IDAOGenerico;

public interface IDaoEstabelecimento extends IDAOGenerico<Estabelecimento> {
	
	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException;	

}