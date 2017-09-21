package dao;

import javax.persistence.EntityManagerFactory;

import basicas.Produto;
import dados.genercio.DAOGenerico;


public class ProdutoDAO extends DAOGenerico<Produto>{

	public ProdutoDAO(EntityManagerFactory emf) {
		super();
	}

}
