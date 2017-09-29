package br.com.drinks.dados.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.dados.genericos.DAOGenerico;


public class ClienteDAO extends DAOGenerico<Cliente>{

	public ClienteDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	public Cliente existeUsuario(Cliente cliente) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		Cliente result = null;
		try {
			String sql = "FROM " + getPersistentClass().getName() + " C WHERE C."+ Cliente.EMAIL +" = :E ";
			Query query =  em.createQuery(sql, Cliente.class);
			query.setParameter("E", cliente.geteMail());

			result = (Cliente)query.getSingleResult();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		
		return result;
	}
	
	public boolean login(Cliente cliente) {
		Cliente resultado = existeUsuario(cliente);
		if(resultado!= null) {
			if(cliente.getSenha().equals(resultado.getSenha())) {
				return true;
			}
		}
		return false;
	}

}
