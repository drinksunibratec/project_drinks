package dados.geral;

import javax.persistence.EntityManagerFactory;

import basicas.Produto;
import dados.genericos.DAOGenerico;

public class ProdutoDao extends DAOGenerico<Produto> {

	public ProdutoDao(EntityManagerFactory emf) {
		super(emf);
	}

}
