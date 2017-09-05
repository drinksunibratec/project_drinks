package dados;

import javax.security.auth.login.LoginException;

import basicas.Estabelecimento;
import dados.genericos.IDaoGenerico;

public interface IDaoEstabelecimento extends IDaoGenerico<Estabelecimento> {
	
	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException;	

}