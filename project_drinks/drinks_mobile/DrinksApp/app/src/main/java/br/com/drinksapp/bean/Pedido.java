package br.com.drinksapp.bean;

import java.util.List;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class Pedido {

    long codPedido;

    Usuarios usuario;

    Estabelecimento estabelecimento;

    List<PedidoProdutos> pedidoProdutos;

    long codEstabelecimento;

    long codUsuario;

    String dataPedido;

    double valorTotal;

    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCodEstabelecimento() {
        return codEstabelecimento;
    }

    public void setCodEstabelecimento(long codEstabelecimento) {
        this.codEstabelecimento = codEstabelecimento;
    }

    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Pedido(long codPedido, Usuarios usuario, Estabelecimento estabelecimento, String dataPedido, double preco) {
        this.codPedido = codPedido;
        this.usuario = usuario;
        this.estabelecimento = estabelecimento;
        this.dataPedido = dataPedido;
        this.valorTotal = preco;
    }

    public Pedido() {

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

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
