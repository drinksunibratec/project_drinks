package br.com.drinksapp.estabelecimento;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.database.Cursor;


import android.view.View;
import android.widget.ListView;

import br.com.drinksapp.estabelecimento.EstabelecimentoSQLHelper;
import br.com.drinksapp.estabelecimento.Estabelecimento;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Juliana on 08/11/2017.
 */

public class EstabelecimentoListFavoritoFragment extends ListFragment {


    List<Estabelecimento> mEstabelecimentos;
    EstabelecimentoCursorAdapter mAdapter;
    Bus mBus;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mEstabelecimentos = new ArrayList<Estabelecimento>();
        mBus = ((EstabelecimentoCursorAdapter)getActivity().getApplication().getBus());
    }

    @Override
    public void onDestroy() {
        mBus.unregister(this);
        super.onDestroy();
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mEstabelecimentos.isEmpty()) {
            mAdapter = new EstabelecimentoCursorAdapter(getActivity(), (Cursor) mEstabelecimentos);
            setListAdapter(mAdapter);
            carregarEstabelecimento;
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Estabelecimento estabelecimento = mEstabelecimentos.get(position);
        if (getActivity() instanceof AoClicarNoEstabelecimentoListener){
            ((AoClicarNoEstabelecimentoListener)getActivity()).clicouNoEstabelecimento(estabelecimento);


        }
    }

    @Subscribe
    public void ListadeFavoritosAtualizado(Estabelecimento estabelecimento) {carregarEstabelecimento();}

    @Override
    public void onResume() {
        super.onResume();
        carregarEstabelecimento();

    }

    public void carregarEstabelecimento() {
        EstabelecimentoSQLHelper sql = new EstabelecimentoSQLHelper(getActivity());
        mEstabelecimentos.clear();
        mEstabelecimentos.addAll(sql.listar());
        mAdapter.notifyDataSetChanged();
    }
}





