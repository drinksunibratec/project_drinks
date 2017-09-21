package business;

import javax.security.auth.login.LoginException;

import basicas.Estabelecimento;
import dao.DAOFactory;
import dao.IDaoEstabelecimento;
import erro.LoginInvalidoException;

public class SegurancaBusiness {
	
	private IDaoEstabelecimento estabelecimentoDAO;
	
	public SegurancaBusiness(){
		estabelecimentoDAO = DAOFactory.getEstabelecimentoDAO();
	}
	
	public Estabelecimento efetuarLogin (String eMail, String senha)
		throws LoginInvalidoException, LoginException {
			return estabelecimentoDAO.efetuarLogin(eMail, senha);		
	}

	public void inserirEstabelecimento (Estabelecimento entidade){
		estabelecimentoDAO.inserir(entidade);
	} 
}
