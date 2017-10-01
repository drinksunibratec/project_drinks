package br.com.drinks.basicas;



import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="efetuarLogin", query="Select e from Estabelecimento e where e.login = :login and e.senha = :senha")
public class Estabelecimento {
	
	public static final String LOGIN = "login";

	public static final String SENHA = "senha";

	public static final String CNPJ = "cnpj";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codEstabelecimento;
	
	@Column (nullable = false)
	private String razaoSocial;	
	
	@Column (nullable = false)
	private String nomeFantasia;
	
	@Column (nullable = false)
	private String eMail;
	
	@Column (nullable = false)
	private String login;
	
	@Column (nullable = false)
	private String senha;
	
	@Column (nullable = false)
	private String cnpj;
	
	@Column (nullable = false)
	private String telefone; 
	
	private Endereco endereco;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "CodProduto")// insertable = true, updatable = true
	private List<Produto> produtos;
	
	public Integer getCodEstabelecimento() {
		return codEstabelecimento;
	}

	public void setCodEstabelecimento(Integer codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Estabelecimento(Integer codEstabelecimento, String razaoSocial, String nomeFantasia, String eMail,
			String login, String senha, String cnpj, String telefone, Endereco endereco, List<Produto> produtos) {
		super();
		this.codEstabelecimento = codEstabelecimento;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.eMail = eMail;
		this.login = login;
		this.senha = senha;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.endereco = endereco;
		this.produtos = produtos;
	}
	
	public Estabelecimento() {
		super();
	}
	
}