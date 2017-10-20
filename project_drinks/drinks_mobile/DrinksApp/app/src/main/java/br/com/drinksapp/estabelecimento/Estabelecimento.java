package br.com.drinksapp.estabelecimento;

import java.io.Serializable;

public class Estabelecimento implements Serializable {
    public long id;
    public String nome;
    public String logradouro;
    public String numero;
    public String bairro;
    public String cidade;
    public String uf;
    public String cep;
    public String latitude;
    public String longetude;
    public Status status;
    public long idServidor;


    public Estabelecimento(long id, String nome, String logradouro, String numero, String bairro, String cidade, String uf, String cep,
                           String latitude, String longetude, long idServidor, Status status) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
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
    public Estabelecimento(String nome, String logradouro, String numero, String bairro, String cidade, String uf, String cep,
                           String latitude, String longetude) {
        this(0, nome, logradouro, numero, bairro, cidade, uf, cep, latitude, longetude, 0, Status.INSERIR);
    }
    @Override
    public String toString() {
        return nome;
    }

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}
