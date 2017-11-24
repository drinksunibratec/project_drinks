package br.com.drinksapp.bean;

import java.io.Serializable;


public class Produto implements Serializable {

    public long codProduto;
    public String descricao;
    public String gelada;
    public String nome;
    public String preco;
    public String codEstabelecimento;
    public Estabelecimento estabelecimento;
    public String ean;
    public String ref_img;


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

    public Produto(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome().toString();
    }

    public String getRef_img() {
        return ref_img;
    }

    public void setRef_img(String ref_img) {
        this.ref_img = ref_img;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
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