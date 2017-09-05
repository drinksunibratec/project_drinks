package seguranca;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginException;

import fachada.Fachada;
import fachada.IFachada;
import basicas.Estabelecimento;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	private String eMail;
	private String senha;
	private Estabelecimento estabelecimentoLogado;
	private IFachada fachada = Fachada.getInstancia();
	
public String efetuarLogin(){
		
		try {
			estabelecimentoLogado = fachada.efetuarLogin(eMail, senha);
			//return "/home.xhtml?faces-redirect=true";
			return "/cadastro_estab.xhtml?faces-redirect=true";
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
	public void setLogin(String eMail) {
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