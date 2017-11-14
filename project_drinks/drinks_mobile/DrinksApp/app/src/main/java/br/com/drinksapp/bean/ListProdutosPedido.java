package br.com.drinksapp.bean;

import java.util.List;

/**
 * Created by Silvio Cedrim on 14/11/2017.
 */

public class ListProdutosPedido {

    List<PedidoProdutos> produtos;

    public List<PedidoProdutos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<PedidoProdutos> produtos) {
        this.produtos = produtos;
    }
}
