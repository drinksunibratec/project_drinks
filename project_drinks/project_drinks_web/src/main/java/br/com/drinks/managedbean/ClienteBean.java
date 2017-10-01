package br.com.drinks.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.drinks.basicas.Cliente;
import br.com.drinks.business.BasicBusiness;
import br.com.drinks.business.ClienteBusiness;


@ManagedBean
@SessionScoped
public class ClienteBean extends ManagedBeanGenerico<Cliente>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5790080825042039608L;
	
	
	@Override
	public BasicBusiness<Cliente> getBoPadrao() {
		return ClienteBusiness.getInstancia();
	}

	@Override
	public void setBoPadrao(BasicBusiness<Cliente> boPadrao) {
		
	}

	@Override
	public void afterSave() {
		
	}

	@Override
	public void beforeSave() {
		
	}

	@Override
	public void beforeEdit() {
		
	}
	
	

}