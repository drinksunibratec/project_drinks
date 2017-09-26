package br.com.drinks.managedbean;

import java.util.List;

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
	
	private List<Produto> listaDeProdutos;
	
	private Produto produto;
	
	
	
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
	public void init() {
		super.init();
		listaDeProdutos = getBoPadrao().list();
	}
	

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
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
		produto.getEstabelecimento().setCodEstabelecimento(getEstabelecimentoLogado().getCodEstabelecimento());
		setBean(produto);
	}

	@Override
	public void beforeEdit() {
		
	}
	
	

}
