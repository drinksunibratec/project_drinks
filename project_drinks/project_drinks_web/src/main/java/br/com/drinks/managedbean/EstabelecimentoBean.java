package br.com.drinks.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.basicas.UF;
import br.com.drinks.erro.DaoException;
import br.com.drinks.erro.GeralException;
import br.com.drinks.fachada.DrinksBusiness;
import br.com.drinks.fachada.IDrinksBusiness;
import br.com.drinks.utils.SessionContext;


@ManagedBean (name = "mbEstabelecimento")
@SessionScoped
public class EstabelecimentoBean {
	
	private Estabelecimento estabelecimento;
	private List<Estabelecimento> estabelecimentos;
	private IDrinksBusiness fachada = DrinksBusiness.getInstancia();
	
	@PostConstruct
	public void init() {
	
		estabelecimento = SessionContext.getInstance().getEstabelecimentoLogado();
		
	}
	
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	public List<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}
	
	public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}
	
	public UF [] getUF(){
		return UF.values();
	}
	
	public EstabelecimentoBean() throws DaoException{
		super();
		estabelecimentos = fachada.consultarTodosOsEstabelecimentos();		
	}

	public void cadastrarEstabelecimento(){
		
		try {
			fachada.salvarEstabelecimento(estabelecimento);
		} catch (GeralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.msnSucessoAoCadastrar();
		}
	}
	
	public void editarEstabelecimento(){
		try {
			fachada.alterarEstabelecimento(estabelecimento);
		} catch (GeralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluirEstabelecimento(){
		try {
			fachada.excluirEstabelecimento(estabelecimento);
		} catch (GeralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	// Mensagens!
		private void msnErroNaConsulta() {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao consultar Estabelecimento!", ""));

		}

		private void msnSucessoAoCadastrar() {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Estabelecimento cadastrado com sucesso!", ""));

		}
}
