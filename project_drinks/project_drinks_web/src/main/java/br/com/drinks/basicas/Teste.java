package basicas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAOFactory;
import dao.IDaoEstabelecimento;



public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		IDaoEstabelecimento daoEstabelecimento = null;
		Estabelecimento estabelecimento = new Estabelecimento (null,"SuperAdmin","SuperAdmin","admin@admin.com",
				"admin123","24582745000174",null,null);

		try {
			emf = Persistence.createEntityManagerFactory("unitPSC");
			em = emf.createEntityManager();
			
			DAOFactory.getEstabelecimentoDAO().inserir(estabelecimento);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if (em != null){
			em.close();
		} 
		if (emf != null){
			emf.close();
		}		
	
	}
}
