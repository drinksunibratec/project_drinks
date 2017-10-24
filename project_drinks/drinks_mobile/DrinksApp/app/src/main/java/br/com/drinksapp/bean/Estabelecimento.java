package br.com.drinksapp.bean;

import java.io.Serializable;

public class Estabelecimento implements Serializable {
    long codEstabelecimento;
    String nomeFantasia;
    String rua;
    String numero;
    String bairro;
    String cidade;
    String uf;
    String cep;
    String latitude;
    String longetude;



    public Estabelecimento(long codEstabelecimento, String nomeFantasia, String rua, String numero, String bairro, String cidade, String uf, String cep,
                           String latitude, String longetude) {
        this.codEstabelecimento = codEstabelecimento;
        this.nomeFantasia = nomeFantasia;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.latitude = latitude;
        this.longetude = longetude;
    }

    @Override
    public String toString() {
        return nomeFantasia;
    }

    public long getCodEstabelecimento() {
        return codEstabelecimento;
    }

    public void setCodEstabelecimento(long codEstabelecimento) {
        this.codEstabelecimento = codEstabelecimento;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nome) {
        this.nomeFantasia = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongetude() {
        return longetude;
    }

    public void setLongetude(String longetude) {
        this.longetude = longetude;
    }
}
