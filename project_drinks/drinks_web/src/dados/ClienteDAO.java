package dados;

import javax.persistence.EntityManagerFactory;

import basicas.Cliente;
import dados.genericos.DaoGenerico;

public class ClienteDAO extends DaoGenerico<Cliente>{

	public ClienteDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
