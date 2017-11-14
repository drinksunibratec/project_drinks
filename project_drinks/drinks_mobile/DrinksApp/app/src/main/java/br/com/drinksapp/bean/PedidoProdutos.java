package br.com.drinksapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class PedidoProdutos implements Parcelable {

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

    public static Creator<PedidoProdutos> getCREATOR() {
        return CREATOR;
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

    public static final Creator<PedidoProdutos> CREATOR = new Creator<PedidoProdutos>() {
        @Override
        public PedidoProdutos createFromParcel(Parcel in) {
            return new PedidoProdutos(in);
        }

        @Override
        public PedidoProdutos[] newArray(int size) {
            return new PedidoProdutos[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(codProduto);
        dest.writeLong(codPedido);
        dest.writeDouble(preco);
        dest.writeInt(quantidade);
        dest.writeDouble(precoTotal);
        dest.writeParcelable(produto, flags);
        dest.writeString(nome);
        dest.writeString(descricao);
    }
}

