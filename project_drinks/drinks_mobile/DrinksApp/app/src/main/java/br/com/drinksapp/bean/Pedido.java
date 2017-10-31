package br.com.drinksapp.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class Pedido {

    long codPedido;

    Usuarios usuario;

    Estabelecimento estabelecimento;

    List<PedidoProdutos> pedidoProdutos;

    Date data;

    double preco;

    public Pedido(long codPedido, Usuarios usuario, Estabelecimento estabelecimento, Date data, double preco) {
        this.codPedido = codPedido;
        this.usuario = usuario;
        this.estabelecimento = estabelecimento;
        this.data = data;
        this.preco = preco;
    }

    public List<PedidoProdutos> getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(List<PedidoProdutos> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

    public long getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(long codPedido) {
        this.codPedido = codPedido;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
