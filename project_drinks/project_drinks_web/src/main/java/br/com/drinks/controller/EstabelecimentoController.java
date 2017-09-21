package business;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import basicas.Estabelecimento;
import erro.GeralException;
import fachada.DrinksBusiness;
import seguranca.TipoUsuario;

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
	
	public TipoUsuario[] getTiposUsuario(){
		return TipoUsuario.values();
	}

}
