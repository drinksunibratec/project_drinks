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
		public void insert (Produto entity) {
			try {
				DrinksBusiness.getInstancia().salvarProduto(entity);
			} catch (GeralException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N�o Foi poss�vel Cadastrar Produto!")
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void remove (Produto entity) {
			fachada.DrinksBusiness.getInstancia().excluirProduto(entity);
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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N�o Foi poss�vel Alterar Produto!")
				e.printStackTrace();
			}
		}
		
}
