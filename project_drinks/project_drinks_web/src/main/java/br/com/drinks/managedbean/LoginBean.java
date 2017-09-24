package br.com.drinks.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.fachada.DrinksBusiness;
import br.com.drinks.fachada.IDrinksBusiness;



@ManagedBean
@SessionScoped
public class LoginBean {

	private String eMail;
	private String senha;
	private Estabelecimento estabelecimentoLogado;
	private IDrinksBusiness fachada = DrinksBusiness.getInstancia();

	public String efetuarLogin(){

		try {
			//			Validação se é Admin!			
			//			if (estabelecimentoLogado.geteMail() != null && estabelecimentoLogado.geteMail() == "admin@admin.com"){
			//				return "/pages/home_admin.xhtml?faces-redirect=true";
			//			}else
			estabelecimentoLogado = fachada.efetuarLogin(eMail, senha);		
			return "/pages/home_admin.xhtml?faces-redirect=true";
		} catch (LoginException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login ou Senha Inexistente!"));
		}
		return null;
	}

	public String efetuarLogoff(){
		estabelecimentoLogado = null;
		return "/index.xhtml?faces-redirect=true";
	}

	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Estabelecimento getEstabelecimentoLogado() {
		return estabelecimentoLogado;
	}
	public void setEstabelecimentoLogado(Estabelecimento estabelecimentoLogado) {
		this.estabelecimentoLogado = estabelecimentoLogado;
	}

}