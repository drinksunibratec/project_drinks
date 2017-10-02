package br.com.drinks.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginException;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.business.BasicBusiness;
import br.com.drinks.business.EstabelecimentoBusiness;
import br.com.drinks.utils.SessionContext;



@ManagedBean
@SessionScoped
public class LoginBean extends ManagedBeanGenerico<Estabelecimento>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6881455116163052253L;
	private String login;
	private String senha;
	private Estabelecimento estabelecimentoLogado;




	public String doLogin(){

		try {
			estabelecimentoLogado = EstabelecimentoBusiness.getInstancia().efeturarLogin(login, senha);		
			if(estabelecimentoLogado != null) {
				SessionContext.getInstance().setAttribute("estabelecimentoLogado", estabelecimentoLogado);
				return "/pages/home_admin.xhtml?faces-redirect=true";

			}else{
				return "/pages/index.xhtml?faces-redirect=true";
			}
		} catch (LoginException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login ou Senha Inexistente!"));
		}
		return null;
	}
	
	

	public String doLogout(){
		SessionContext.getInstance().encerrarSessao();
		return "/pages/login/index.xhtml?faces-redirect=true";
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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

	@Override
	public BasicBusiness<Estabelecimento> getBoPadrao() {
		return EstabelecimentoBusiness.getInstancia();
	}

	@Override
	public void setBoPadrao(BasicBusiness<Estabelecimento> boPadrao) {

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