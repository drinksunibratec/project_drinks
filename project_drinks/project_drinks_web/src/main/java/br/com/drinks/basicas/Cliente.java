package br.com.drinks.basicas;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	public static final String EMAIL = "eMail";

	public static final String SENHA = "senha";

	public Cliente() {
		super();
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "codcliente")
	private Integer codigo;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(length = 100)
	private String eMail;
	
	@Column(length = 11, nullable = false)
	private String telefone;
	
	@Column(length = 15, nullable = false)
	private String senha;
	
//	@ManyToOne
//	@JoinColumn(name = "CodProduto")
//	private List<Produto> prodsFavorito;
//	
//	@ManyToOne
//	@JoinColumn(name = "CodEstabelecimento")
//	private List<Estabelecimento> estabsFavorito;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public List<Produto> getProdsFavorito() {
//		return prodsFavorito;
//	}
//	
//	public void setProdsFavorito(List<Produto> prodsFavorito) {
//		this.prodsFavorito = prodsFavorito;
//	}
//	
//	public List<Estabelecimento> getEstabsFavorito() {
//		return estabsFavorito;
//	}
//	
//	public void setEstabsFavorito(List<Estabelecimento> estabsFavorito) {
//		this.estabsFavorito = estabsFavorito;
//	}
//	
	public Cliente(Integer codigo, String nome, String eMail, String telefone, String senha,
			List<Produto> prodsFavorito, List<Estabelecimento> estabsFavorito) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.eMail = eMail;
		this.telefone = telefone;
		this.senha = senha;
//		this.prodsFavorito = prodsFavorito;
//		this.estabsFavorito = estabsFavorito;
	}

}
