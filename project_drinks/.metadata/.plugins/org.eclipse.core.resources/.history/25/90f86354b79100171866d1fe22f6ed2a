package dados;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
