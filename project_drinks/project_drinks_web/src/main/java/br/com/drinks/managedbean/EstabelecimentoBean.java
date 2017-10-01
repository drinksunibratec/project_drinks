package br.com.drinks.managedbean;

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


	@PostConstruct
	public void init() {
		super.init();

		estabelecimento = new Estabelecimento();
		estabelecimento.setEndereco(new Endereco());
		
	}



	public Estabelecimento getEstabelecimentoLogado() {
		return (Estabelecimento) SessionContext.getInstance().getEstabelecimentoLogado();
	}


	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
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
		return EstabelecimentoBusiness.getInstancia();
	}



	@Override
	public void setBoPadrao(BasicBusiness<Estabelecimento> boPadrao) {

	}



	@Override
	public void afterSave() {
		this.estabelecimento = new Estabelecimento();
		estabelecimento.setEndereco(new Endereco());
		this.setList(getList());
	}



	@Override
	public void beforeEdit() {

	}
}