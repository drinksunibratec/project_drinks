package br.com.drinksapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class PedidoProdutos implements Serializable {

    long codProduto;

    long codPedido;

    double preco;

    int quantidade;

    double precoTotal;

    Produto produto;

    /*
    Produto
     */

    String nome;

    String descricao;

    String ean;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public PedidoProdutos() {
    }

    protected PedidoProdutos(Parcel in) {
        codProduto = in.readLong();
        codPedido = in.readLong();
        nome = in.readString();
        descricao = in.readString();
        preco = in.readDouble();
        quantidade = in.readInt();
        precoTotal = in.readDouble();
        produto = in.readParcelable(Produto.class.getClassLoader());
    }


    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(long codProduto) {
        this.codProduto = codProduto;
    }

    public long getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(long codPedido) {
        this.codPedido = codPedido;
    }


}

