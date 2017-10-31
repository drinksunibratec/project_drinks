package br.com.drinksapp.bean;

import android.os.Parcel;
import android.os.Parcelable;


public class Produto implements Parcelable {
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

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(codProduto);
        dest.writeString(descricao);
        dest.writeString(gelada);
        dest.writeString(nome);
        dest.writeString(preco);
        dest.writeString(codEstabelecimento);
    }
}