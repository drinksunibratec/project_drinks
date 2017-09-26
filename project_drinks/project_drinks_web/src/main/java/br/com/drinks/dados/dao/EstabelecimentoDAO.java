package br.com.drinks.dados.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.dados.genericos.DAOGenerico;



public class EstabelecimentoDAO extends DAOGenerico<Estabelecimento> {

	public EstabelecimentoDAO(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	public Estabelecimento efetuarLogin(String eMail, String senha)
			throws LoginException {
		
		EntityManager em = this.entityManagerFactory.createEntityManager();
		Estabelecimento result = null;
		try {
			String sql = "FROM " + getPersistentClass().getName() + " E WHERE E."+ Estabelecimento.EMAIL +" = :L AND E." +Estabelecimento.SENHA + " = :S";
			Query query =  em.createQuery(sql, Estabelecimento.class);
			query.setParameter("L", eMail);
			query.setParameter("S", senha);

			result = (Estabelecimento)query.getSingleResult();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return result;


//		try {
//			TypedQuery<Estabelecimento> query = ((EntityManagerFactory) getEntityManagerFactory()).createNamedQuery(
//					"efetuarLogin", Estabelecimento.class);
//			query.setParameter("eMail", eMail);
//			query.setParameter("senha", senha);
//			return (Estabelecimento)query.getSingleResult();
//
//		} catch (NoResultException noe) {
//			System.out.println("Login/Senha Invalidos!");
//			throw new LoginException();
//
//		} catch (Exception e) {
//			throw new LoginException();
//		}
		
	}
	
	

}