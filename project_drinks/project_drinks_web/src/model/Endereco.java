package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Endereco {
	
	@Column(length = 150, nullable = false)
	private String rua;
	
	private Integer numero;
	
	@Column(length = 50, nullable = false)
	private String bairro;
	
	@Column(length = 50, nullable = false)
	private String cidade;
	
	@Enumerated
	@Column(length = 2, nullable = false)
	private UF uf;
	
	@Column(length = 9, nullable = false)
	private String cep;

	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}

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
}
