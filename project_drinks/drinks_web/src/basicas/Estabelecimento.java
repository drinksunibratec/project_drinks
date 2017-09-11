package basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import seguranca.TipoUsuario;

@Entity
@NamedQuery(name="efetuarLogin", query="Select e from Estabelecimento e where e.eMail = :eMail and e.senha = :senha")
public class Estabelecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codEstabelecimento;
	
	@Column (nullable = false, length = 100)
	private String razaoSocial;	
	
	@Column (nullable = false, length = 100)
	private String nomeFantasia;
	
	@Column (nullable = false, length = 100)
	private String eMail;
	
	@Column (nullable = false, length = 16)
	private String senha;
	
	@Column (nullable = false, length = 14)
	private String cnpj;
	
	private TipoUsuario tipo;
	
	private Endereco endereco;
	
	@Column (length = 14)	
	private String latitude;	
	
	@Column (length = 14)
	private String longitude;

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
	
	public TipoUsuario getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Estabelecimento(Integer codEstabelecimento, String razaoSocial, String nomeFantasia, String eMail,
			String senha, String cnpj, Endereco endereco, TipoUsuario tipo, String latitude, String longitude) {
		super();
		this.codEstabelecimento = codEstabelecimento;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.eMail = eMail;
		this.senha = senha;
		this.cnpj = cnpj;
		this.tipo = tipo;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Estabelecimento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}