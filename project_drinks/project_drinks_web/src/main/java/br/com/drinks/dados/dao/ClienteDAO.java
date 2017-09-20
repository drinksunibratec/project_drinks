package br.com.drinks.dados.dao;

import javax.persistence.EntityManagerFactory;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.dados.genericos.DAOGenerico;


public class ClienteDAO extends DAOGenerico<Cliente>{

	public ClienteDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
