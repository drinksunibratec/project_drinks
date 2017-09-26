package br.com.drinks.basicas;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Endereco {
	
	@Column
	private String rua;
	
	@Column (length = 4)
	private Integer numero;
	
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Enumerated (EnumType.STRING)
	@Column(length = 2)
	private UF uf;
	
	@Column
	private String cep;
	
	@Column (length = 10)	
	private String latitude;	
	
	@Column (length = 10)
	private String longitude;
	

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
	
	
	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}