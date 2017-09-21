package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.security.auth.login.LoginException;

import basicas.Estabelecimento;
import dados.genercio.DAOGenerico;


public class EstabelecimentoDAO extends DAOGenerico<Estabelecimento> implements IDaoEstabelecimento {

	public EstabelecimentoDAO(EntityManagerFactory emf) {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException {

		try {
			TypedQuery<Estabelecimento> query = ((EntityManager) getEntityManager()).createNamedQuery(
					"efetuarLogin", Estabelecimento.class);
			query.setParameter("eMail", eMail);
			query.setParameter("senha", senha);
			return query.getSingleResult();

		} catch (NoResultException noe) {
			System.out.println("Login/Senha Invalidos!");
			throw new LoginException();

		} catch (Exception e) {
			throw new LoginException();
		}
		
	}
	
	

}