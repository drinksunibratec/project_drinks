package dados.geral;

import javax.persistence.EntityManagerFactory;

import basicas.Cliente;

public class ClienteDAO extends DaoGenerico<Cliente>{

	public ClienteDAO(EntityManagerFactory emf) {
		super(emf);
	}

}