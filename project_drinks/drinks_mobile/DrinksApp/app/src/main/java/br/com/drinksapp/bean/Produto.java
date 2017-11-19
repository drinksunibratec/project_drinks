package br.com.drinksapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class Produto implements Serializable {
    public long codProduto;
    public String descricao;
    public String gelada;
    public String nome;
    public String preco;
    public String codEstabelecimento;
    public Estabelecimento estabelecimento;

    protected Produto(Parcel in) {
        codProduto = in.readLong();
        descricao = in.readString();
        gelada = in.readString();
        nome = in.readString();
        preco = in.readString();
        codEstabelecimento = in.readString();
    }

    public Produto() {

    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Produto(long codProduto, String descricao, String gelada, String nome, String preco, String codEstabelecimento) {
        this.codProduto = codProduto;
        this.descricao = descricao;
        this.gelada = gelada;
        this.nome = nome;
        this.preco = preco;
        this.codEstabelecimento = codEstabelecimento;
    }
    public Produto(String descricao, String gelada, String nome, String preco, String codEstabelecimento) {
        this(0, descricao, gelada, nome, preco, codEstabelecimento);
    }

    public long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(long codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGelada() {
        return gelada;
    }

    public void setGelada(String gelada) {
        this.gelada = gelada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getCodEstabelecimento() {
        return codEstabelecimento;
    }

    public void setCodEstabelecimento(String codEstabelecimento) {
        this.codEstabelecimento = codEstabelecimento;
    }

}