package dados.geral;

import javax.persistence.EntityManagerFactory;

import basicas.Produto;

public class ProdutoDao extends DaoGenerico<Produto> {

	public ProdutoDao(EntityManagerFactory emf) {
		super(emf);
	}

}
