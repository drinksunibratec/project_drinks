package br.com.drinks.dados.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.dados.genericos.DAOGenerico;



public class EstabelecimentoDAO extends DAOGenerico<Estabelecimento> {

	public EstabelecimentoDAO(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException {

		try {
			TypedQuery<Estabelecimento> query = ((EntityManager) getEntityManagerFactory()).createNamedQuery(
					"efetuarLogin", Estabelecimento.class);
			query.setParameter("eMail", eMail);
			query.setParameter("senha", senha);
			return (Estabelecimento)query.getSingleResult();

		} catch (NoResultException noe) {
			System.out.println("Login/Senha Invalidos!");
			throw new LoginException();

		} catch (Exception e) {
			throw new LoginException();
		}
		
	}
	
	

}