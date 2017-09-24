import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

import basicas.Estabelecimento;
import basicas.Produto;
import dados.DaoEstabelecimento;
import erro.GeralException;
import fachada.IDrinksBusiness;

@ManagedBean (name = "mbEstabelecimento")
@ViewScoped
public class EstabelecimentoBean {
	
	private Estabelecimento estabelecimento;
	private List<Estabelecimento> estabelecimntos;
	private Produto produto;
	private List<Produto> produtos;
	private IDrinksBusiness fachada = IDrinksBusiness.getInstacia();
	
	public EstabelecimentoBean() {
		super();
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public List<Estabelecimento> getEstabelecimntos() {
		return estabelecimntos;
	}

	public void setEstabelecimntos(List<Estabelecimento> estabelecimntos) {
		this.estabelecimntos = estabelecimntos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

//	public IDrinksBusiness getFachada() {
//		return fachada;
//	}
//
//	public void setFachada(IDrinksBusiness fachada) {
//		this.fachada = fachada;
//	}
	
	public void salvarEstabelecimento() throws GeralException {
		try{
		EntityManagerFactory emf = null;
		DaoEstabelecimento dao = new DaoEstabelecimento(emf);
				dao.inserir(estabelecimento);
				this.msnSucessoAoCadastrar();
		}catch (Exception e) {
			this.msnErroAoCadastrar();
		}		
		
	}
	
	// MENSSAGENS
		public void msnSucessoAoCadastrar() {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Estabelecimento adicionado com sucesso!", ""));
		}

		public void msnErroAoCadastrar() {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao cadastrar Estabelecimento!", ""));
		}

		public void msnErroNaConsulta() {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao consultar Estabelecimento!", ""));
		}

}
