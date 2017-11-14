package br.com.drinksapp.interfaces;

import java.util.List;

import br.com.drinksapp.bean.Produto;

/**
 * Created by Silvio Cedrim on 13/11/2017.
 */

public interface OnEstabelecimentoClick {

    void clicouNoEstabelecimento(List<Produto> produtos);
}
