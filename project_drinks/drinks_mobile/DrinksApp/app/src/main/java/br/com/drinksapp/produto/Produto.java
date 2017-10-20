package br.com.drinksapp.produto;

import java.io.Serializable;

public class Produto implements Serializable {
    public long id;
    public String nome;
    public String estabelecimento;
    public String endereco;
    public String bairro;
    public String preco;
    public Status status;
    public long idServidor;


    public Produto(long id, String nome, String estabelecimento, String endereco, String bairro, String preco,
                   long idServidor, Status status) {
        this.id = id;
        this.nome = nome;
        this.estabelecimento = estabelecimento;
        this.endereco = endereco;
        this.bairro = bairro;
        this.preco = preco;
        this.idServidor = idServidor;
        this.status = status;
    }
    public Produto(String nome, String estabelecimento, String endereco, String bairro, String preco) {
        this(0, nome, estabelecimento, endereco, bairro, preco, 0, Status.INSERIR);
    }
    @Override
    public String toString() {
        return nome;
    }

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}