package dados.geral;

import javax.persistence.EntityManagerFactory;

import basicas.Produto;
import dados.genericos.DAOGenerico;

public class ProdutoDAO extends DAOGenerico<Produto> {

	public ProdutoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
