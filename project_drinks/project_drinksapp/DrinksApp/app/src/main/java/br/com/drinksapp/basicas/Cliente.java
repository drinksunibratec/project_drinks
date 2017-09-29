package br.com.drinksapp.basicas;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Silvinho Cedrim on 06/09/2017.
 */

public class Cliente implements Serializable {


    private Integer codigo;

    private String nome;

    private String eMail;

    private String telefone;

    private String senha;

    private List<Produto> produtosFavoritos;

    private List<Estabelecimento> estabelecimentosFavoritos;

    public Cliente(String nome, String eMail, String telefone, String senha) {
        this.nome = nome;
        this.eMail = eMail;
        this.telefone = telefone;
        this.senha = senha;
    }
    public List<Estabelecimento> getEstabelecimentosFavoritos() {
        return estabelecimentosFavoritos;
    }

    public void setEstabelecimentosFavoritos(List<Estabelecimento> estabelecimentosFavoritos) {
        this.estabelecimentosFavoritos = estabelecimentosFavoritos;
    }


    public List<Produto> getProdutosFavoritos() {
        return produtosFavoritos;
    }

    public void setProdutosFavoritos(List<Produto> produtosFavoritos) {
        this.produtosFavoritos = produtosFavoritos;
    }

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
}
