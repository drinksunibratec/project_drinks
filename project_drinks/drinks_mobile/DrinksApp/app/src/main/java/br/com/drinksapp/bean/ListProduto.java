package br.com.drinksapp.bean;

import java.util.List;

/**
 * Created by Silvio Cedrim on 25/10/2017.
 */

public class ListProduto {

    List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
