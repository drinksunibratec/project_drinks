package managebean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import basicas.Estabelecimento;
import controller.EstabelecimentoController;
import dao.EstabelecimentoDAO;
import erro.GeralException;
import fachada.DrinksBusiness;
import fachada.IDrinksBusiness;

@ManagedBean (name = "mbEstabelecimento")
@SessionScoped
public class EstabelecimentoBean {
	
	private Estabelecimento estabelecimento;
	private List<Estabelecimento> estabelecimentos;
	private IDrinksBusiness fachada = DrinksBusiness.getInstancia();
	
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
	
	public EstabelecimentoBean(){
		//super();
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
