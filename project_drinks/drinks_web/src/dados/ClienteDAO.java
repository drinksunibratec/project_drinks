package dados;

import javax.persistence.EntityManagerFactory;

import basicas.Cliente;
import dados.genericos.DAOGenerico;

public class ClienteDAO extends DAOGenerico<Cliente>{

	public ClienteDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
