package br.com.drinksapp.basicas;

import java.util.List;

/**
 * Created by Silvio Cedrim on 28/09/2017.
 */

public class Estabelecimento {

    private Integer codEstabelecimento;

    private String razaoSocial;

    private String nomeFantasia;

    private String eMail;

    private String senha;

    private String cnpj;

    private String telefone;

    private Endereco endereco;

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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
