package br.com.drinks.managedbean;

import javax.annotation.PostConstruct;

import br.com.drinks.basicas.Estabelecimento;
import br.com.drinks.basicas.Produto;
import br.com.drinks.business.BasicBusiness;
import br.com.drinks.business.ProdutoBusiness;
import br.com.drinks.utils.SessionContext;

public class ProdutoBean extends ManagedBeanGenerico<Produto>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4896108785300749122L;
	
	private Produto produto;
	
	@Override
	@PostConstruct
	public void init() {
		super.init();
		produto = new Produto();
		produto.setEstabelecimento(getEstabelecimentoLogado());
	}
	
	public Estabelecimento getEstabelecimentoLogado() {
		return (Estabelecimento) SessionContext.getInstance().getEstabelecimentoLogado();
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public BasicBusiness<Produto> getBoPadrao() {
		return ProdutoBusiness.getInstancia();
	}

	@Override
	public void setBoPadrao(BasicBusiness<Produto> boPadrao) {
		
	}

	@Override
	public void afterSave() {
		produto = new Produto();
		this.setList(getList());
	}

	@Override
	public void beforeSave() {
		setBean(produto);
	}

	@Override
	public void beforeEdit() {
		
	}
	
	

}
