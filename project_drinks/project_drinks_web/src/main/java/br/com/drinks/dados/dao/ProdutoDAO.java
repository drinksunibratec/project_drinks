package br.com.drinks.dados.dao;

import javax.persistence.EntityManagerFactory;

import br.com.drinks.basicas.Produto;
import br.com.drinks.dados.genericos.DAOGenerico;



public class ProdutoDAO extends DAOGenerico<Produto>{

	public ProdutoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
