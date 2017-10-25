package br.com.drinksapp.produto;

import java.io.Serializable;

public class Produto implements Serializable {
    public long id;
    public String descricao;
    public String gelada;
    public String nome;
    public String preco;
    public String codEstabelecimento;
    public Status status;
    public long idServidor;


    public Produto(long id, String descricao, String gelada, String nome, String preco, String codEstabelecimento,
                   long idServidor, Status status) {
        this.id = id;
        this.descricao = descricao;
        this.gelada = gelada;
        this.nome = nome;
        this.preco = preco;
        this.codEstabelecimento = codEstabelecimento;
        this.idServidor = idServidor;
        this.status = status;
    }
    public Produto(String descricao, String gelada, String nome, String preco, String codEstabelecimento) {
        this(0, descricao, gelada, nome, preco, codEstabelecimento, 0, Status.INSERIR);
    }
    @Override
    public String toString() {
        return nome;
    }

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}