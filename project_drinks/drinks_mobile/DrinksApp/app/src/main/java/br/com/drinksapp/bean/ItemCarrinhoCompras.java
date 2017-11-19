package br.com.drinksapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class ItemCarrinhoCompras implements Serializable {

    long codProduto;

    long codPedido;

    long codEstabelcimento;

    double preco;

    int quantidade;

    double precoTotal;

    Produto produto;


    public ItemCarrinhoCompras() {

    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public long getCodEstabelcimento() {
        return codEstabelcimento;
    }

    public void setCodEstabelcimento(long codEstabelcimento) {
        this.codEstabelcimento = codEstabelcimento;
    }

}
