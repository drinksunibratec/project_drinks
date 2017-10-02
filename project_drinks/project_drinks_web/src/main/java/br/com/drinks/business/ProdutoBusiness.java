package br.com.drinks.business;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.drinks.basicas.Produto;
import br.com.drinks.erro.GeralException;
import br.com.drinks.fachada.DrinksBusiness;

public class ProdutoBusiness extends BasicBusiness<Produto> {

		private static ProdutoBusiness instancia;
		
		public static ProdutoBusiness getInstancia() {
			if(instancia == null) {
				instancia = new ProdutoBusiness();
			}
			
			return instancia;
		}
		
		@Override
		public boolean insert (Produto entity) {
			try {
				DrinksBusiness.getInstancia().salvarProduto(entity);
			} catch (GeralException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não Foi possível Cadastrar Produto!"));
				e.printStackTrace();
			}
			return true;
			
		}
		
		@Override
		public void remove (Produto entity) {
			try {
				DrinksBusiness.getInstancia().excluirProduto(entity);
			} catch (GeralException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public List<Produto> list() {
			return DrinksBusiness.getInstancia().consultarTodosOsProdutos();
		}
		
		@Override
		
		public void update(Produto entity) {
			try {
				DrinksBusiness.getInstancia().alterarProduto(entity);
			}catch(GeralException e ){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não Foi possível Alterar Produto!"));
				e.printStackTrace();
			}
		}
		
}
