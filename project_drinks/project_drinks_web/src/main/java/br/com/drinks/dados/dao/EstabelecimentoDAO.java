package br.com.drinks.dados.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.dados.genericos.DAOGenerico;



public class EstabelecimentoDAO extends DAOGenerico<Estabelecimento> {

	public EstabelecimentoDAO(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	public Estabelecimento efetuarLogin(String login, String senha)
			throws LoginException {
		
		EntityManager em = this.entityManagerFactory.createEntityManager();
		Estabelecimento result = null;
		try {
			String sql = "FROM " + getPersistentClass().getName() + " E WHERE E."+ Estabelecimento.LOGIN +" = :L AND E." +Estabelecimento.SENHA + " = :S";
			Query query =  em.createQuery(sql, Estabelecimento.class);
			query.setParameter("L", login);
			query.setParameter("S", senha);

			result = (Estabelecimento)query.getSingleResult();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return result;
	}
	
	public boolean existeEstabelecimentoPorCNPJ(Estabelecimento estabelecimento) {
	
		EntityManager em = this.entityManagerFactory.createEntityManager();
		Estabelecimento result = null;
		try {
			String sql = "FROM " + getPersistentClass().getName() + " E WHERE E."+ Estabelecimento.CNPJ + " = :C";
			Query query =  em.createQuery(sql, Estabelecimento.class);
			query.setParameter("C", estabelecimento.getCnpj());

			result = (Estabelecimento)query.getSingleResult();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		if(result != null) {
			return false;
		}else {
			return true;
		}
	}
	
	

}