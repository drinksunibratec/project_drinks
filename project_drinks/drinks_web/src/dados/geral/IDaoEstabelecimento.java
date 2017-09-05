package dados.geral;

import javax.security.auth.login.LoginException;

import basicas.Estabelecimento;

public interface IDaoEstabelecimento extends IDaoGenerico<Estabelecimento> {
	
	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException;	

}