package br.com.drinks.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.drinks.basicas.Endereco;
import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.basicas.UF;
import br.com.drinks.business.BasicBusiness;
import br.com.drinks.business.EstabelecimentoBusiness;
import br.com.drinks.utils.SessionContext;


@ManagedBean
@SessionScoped
public class EstabelecimentoBean extends ManagedBeanGenerico<Estabelecimento>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3409525747446470904L;

	private Estabelecimento estabelecimento;

	private Estabelecimento estabelecimentoLogado;

	private List<Estabelecimento> estabelecimentos;

	@PostConstruct
	public void init() {
		super.init();
		estabelecimentoLogado = SessionContext.getInstance().getEstabelecimentoLogado();

		estabelecimentos = getBoPadrao().list();

		estabelecimento = new Estabelecimento();
		estabelecimento.setEndereco(new Endereco());
		estabelecimento.getEndereco().setBairro("");
		estabelecimento.getEndereco().setCep("");
		estabelecimento.getEndereco().setCidade("");
		estabelecimento.getEndereco().setLatitude("");
		estabelecimento.getEndereco().setLongitude("");
		estabelecimento.getEndereco().setNumero(0);
		estabelecimento.getEndereco().setRua("");
		estabelecimento.getEndereco().setUf(UF.PE);

	}



	public Estabelecimento getEstabelecimentoLogado() {
		return estabelecimentoLogado;
	}



	public void setEstabelecimentoLogado(Estabelecimento estabelecimentoLogado) {
		this.estabelecimentoLogado = estabelecimentoLogado;
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

	public UF[] getUF(){
		return UF.values();
	}


	@Override
	public void beforeSave() {
		String cnpj = estabelecimento.getCnpj().replace(".", "").replace("/", "").replace("-", "");
		estabelecimento.setCnpj(cnpj);

		String cep = estabelecimento.getEndereco().getCep().replace("-", "");
		estabelecimento.getEndereco().setCep(cep);

		String telefone = estabelecimento.getTelefone().replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
		estabelecimento.setTelefone(telefone);

		setBean(estabelecimento);

	}

	public void editarEstabelecimento(){
		getBoPadrao().update(estabelecimento);
	}

	public void excluirEstabelecimento(){
		getBoPadrao().remove(estabelecimento);
	}	




	@Override
	public BasicBusiness<Estabelecimento> getBoPadrao() {
		// TODO Auto-generated method stub
		return EstabelecimentoBusiness.getInstancia();
	}



	@Override
	public void setBoPadrao(BasicBusiness<Estabelecimento> boPadrao) {

	}



	@Override
	public void afterSave() {
		this.estabelecimento = new Estabelecimento();
		this.setList(getList());
	}



	@Override
	public void beforeEdit() {

	}
}
