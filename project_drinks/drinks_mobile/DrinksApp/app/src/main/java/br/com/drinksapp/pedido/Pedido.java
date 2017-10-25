package br.com.drinksapp.pedido;

import java.io.Serializable;

public class Pedido implements Serializable {
    public long id;
    public String nomepedido;
    public String valor;
    public String id_usuario;
    public String id_produto;
    public Status status;
    public long idServidor;


    public Pedido(long id, String nomepedido, String valor, String id_usuario, String id_produto, long idServidor, Status status) {
        this.id = id;
        this.nomepedido = nomepedido;
        this.valor = valor;
        this.id_usuario = id_usuario;
        this.id_produto = id_produto;
        this.idServidor = idServidor;
        this.status = status;
    }
    public Pedido(String nomepedido, String valor, String id_usuario, String id_produto) {
        this(0, nomepedido, valor, id_usuario, id_produto, 0, Status.INSERIR);
    }
    @Override
    public String toString() {
        return nomepedido;
    }

    public enum Status {
        OK, INSERIR, ATUALIZAR, EXCLUIR
    }
}