package br.com.drinksapp.bean;

import java.util.List;

/**
 * Created by Silvio Cedrim on 14/11/2017.
 */

public class ListPedido {

    List<Pedido> pedidos;

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
