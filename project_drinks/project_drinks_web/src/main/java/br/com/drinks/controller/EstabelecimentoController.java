package br.com.drinks.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.erro.GeralException;
import br.com.drinks.fachada.DrinksBusiness;


public class EstabelecimentoController {
	
	private Estabelecimento estabelecimento;
	
	public String salvarEstabelecimnto() throws GeralException{
		DrinksBusiness.getInstancia().salvarEstabelecimento(estabelecimento);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário cadastrado com sucesso!"));
		return "/index.xhtml?faces-redirect=true";
		
	}
	
	public Estabelecimento getEstabelecimento(){
		return estabelecimento;
	}
	
	public void setEstabelecimento (Estabelecimento estabelecimento){
		this.estabelecimento = estabelecimento;
	}
	
}
