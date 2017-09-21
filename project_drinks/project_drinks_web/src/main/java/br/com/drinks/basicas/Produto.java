package basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produto {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer codProduto;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(length = 400, nullable = false)
	private String descricao;
	
	@Column(length = 4, nullable = false)
	private Double preco;
	
	@Column
	private boolean gelada;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CodEstabelecimento", insertable = true, updatable = true)
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
