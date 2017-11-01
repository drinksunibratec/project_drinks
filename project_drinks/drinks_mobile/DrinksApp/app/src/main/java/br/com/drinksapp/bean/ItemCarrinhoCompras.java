package br.com.drinksapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class ItemCarrinhoCompras implements Parcelable {

    long codProduto;

    long codPedido;

    long codEstabelcimento;

    double preco;

    int quantidade;

    double precoTotal;

    Produto produto;


    public ItemCarrinhoCompras(Parcel in) {
        codProduto = in.readLong();
        codPedido = in.readLong();
        codEstabelcimento = in.readLong();
        preco = in.readDouble();
        quantidade = in.readInt();
        precoTotal = in.readDouble();
        produto = in.readParcelable(Produto.class.getClassLoader());
    }

    public ItemCarrinhoCompras() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(codProduto);
        dest.writeLong(codPedido);
        dest.writeLong(codEstabelcimento);
        dest.writeDouble(preco);
        dest.writeInt(quantidade);
        dest.writeDouble(precoTotal);
        dest.writeParcelable(produto, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemCarrinhoCompras> CREATOR = new Creator<ItemCarrinhoCompras>() {
        @Override
        public ItemCarrinhoCompras createFromParcel(Parcel in) {
            return new ItemCarrinhoCompras(in);
        }

        @Override
        public ItemCarrinhoCompras[] newArray(int size) {
            return new ItemCarrinhoCompras[size];
        }
    };

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
