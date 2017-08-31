package model;

public class Estabelecimento {
	
	private Integer codEstabelecimento; 
	
	private String cnpj;
	
	private String razaoSocial;
	
	private Endereco endereco;
	
	private String latitude;
	
	private String longetude;

	public Integer getCodEstabelecimento() {
		return codEstabelecimento;
	}

	public void setCodEstabelecimento(Integer codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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

	public String getLongetude() {
		return longetude;
	}

	public void setLongetude(String longetude) {
		this.longetude = longetude;
	}
	
	

}
