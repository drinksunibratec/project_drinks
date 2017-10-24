package br.com.drinksapp.estabelecimento;

import java.io.Serializable;

public class Estabelecimento implements Serializable {
    public long id;
    public String nomeFantasia;
    public String rua;
    public String numero;
    public String bairro;
    public String cidade;
    public String uf;
    public String cep;
    public String latitude;
    public String longetude;
    public Status status;
    public long idServidor;


    public Estabelecimento(long id, String nomeFantasia, String rua, String numero, String bairro, String cidade, String uf, String cep,
                           String latitude, String longetude, long idServidor, Status status) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.latitude = latitude;
        this.longetude = longetude;
        this.idServidor = idServidor;
        this.status = status;
    }
    public Estabelecimento(String nomeFantasia, String rua, String numero, String bairro, String cidade, String uf, String cep,
                           String latitude, String longetude) {
        this(0, nomeFantasia, rua, numero, bairro, cidade, uf, cep, latitude, longetude, 0, Status.INSERIR);
    }
    @Override
    public String toString() {
        return nomeFantasia;
    }

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}
