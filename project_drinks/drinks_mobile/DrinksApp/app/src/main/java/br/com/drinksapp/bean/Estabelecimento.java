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
    String longitude;
    String cnpj;
    String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Estabelecimento() {
    }

    public Estabelecimento(long codEstabelecimento, String nomeFantasia, String rua, String numero, String bairro, String cidade, String uf, String cep, String latitude, String longitude, String cnpj, String telefone) {
        this.codEstabelecimento = codEstabelecimento;
        this.nomeFantasia = nomeFantasia;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cnpj = cnpj;
        this.telefone = telefone;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
