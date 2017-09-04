package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Cliente {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer codigo;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
		@Column(length = 100)
	private String eMail;
	
	@Column(length = 11, nullable = false)
	private String telefone;
	
	@Column(length = 15, nullable = false)
	private String senha;

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

	public Cliente(Integer codigo, String nome, String eMail, String telefone, String senha) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.eMail = eMail;
		this.telefone = telefone;
		this.senha = senha;
	}

}
