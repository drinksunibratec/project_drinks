package br.com.drinks.dados.genericos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.drinks.dados.dao.ClienteDAO;
import br.com.drinks.dados.dao.EstabelecimentoDAO;


public class DAOFactory {
	
	private static final EntityManagerFactory factory;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		factory = Persistence.createEntityManagerFactory("drinks");		
	}
	
	public static ClienteDAO getClienteDAO(){
		ClienteDAO dao = new ClienteDAO(factory);
		return dao;
	}
	
	public static EstabelecimentoDAO getEstabelecimentoDAO() {
		EstabelecimentoDAO dao = new EstabelecimentoDAO(factory);
		return dao;
	}
	
	
}
