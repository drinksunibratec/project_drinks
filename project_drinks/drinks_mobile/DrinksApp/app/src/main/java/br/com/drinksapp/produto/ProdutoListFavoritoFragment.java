package br.com.drinksapp.produto;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliana on 08/11/2017.
 */

public class ProdutoListFavoritoFragment extends ListFragment{

    List<Produto> mProdutos;
    ProdutoCursorAdapter mAdapter;
    Bus mBus;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mProdutos = new ArrayList<Produto>();
        mBus = ((ProdutoCursorAdapter)getActivity().getApplication().getBus());
    }

    @Override
    public void onDestroy() {
        mBus.unregister(this);
        super.onDestroy();
    }

}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mProdutos.isEmpty()) {
            mAdapter = new ProdutoCursorAdapter(getActivity(), (Cursor) mProdutos);
            setListAdapter(mAdapter);
            carregarProduto;
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Produto produto = mProdutos.get(position);
        if (getActivity() instanceof AoClicarNaProdutoListener){
            ((AoClicarNaProdutoListener)getActivity()).clicouNaProduto(produto);


        }
    }

@Subscribe
public void ListadeFavoritosAtualizado(Produto produto) {carregarProduto();}

@Override
public void onResume() {
    super.onResume();
    carregarProduto();

}

public void carregarProduto() {
    ProdutoSQLHelper sql = new ProdutoSQLHelper(getActivity());
    mProdutos.clear();
    mProdutos.addAll(sql.listar());
    mAdapter.notifyDataSetChanged();
}
}



