package br.com.drinksapp.basicas;

public class Produto {

	private Integer codProduto;
	
	private String nome;
	
	private String descricao;
	
	private Double preco;
	
	private boolean gelada;
		
	private Estabelecimento estabelecimento;

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public boolean isGelada() {
		return gelada;
	}

	public void setGelada(boolean gelada) {
		this.gelada = gelada;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Produto(Integer codProduto, String nome, String descricao, Double preco, boolean gelada,
			Estabelecimento estabelecimento) {
		super();
		this.codProduto = codProduto;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.gelada = gelada;
		this.estabelecimento = estabelecimento;
	}

	public Produto() {
		super();
	}

}
