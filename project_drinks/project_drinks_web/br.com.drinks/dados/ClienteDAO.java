package dados;

import javax.persistence.EntityManagerFactory;

import model.Cliente;

public class ClienteDAO extends DaoGenerico<Cliente>{

	public ClienteDAO(EntityManagerFactory emf) {
		super(emf);
	}

}