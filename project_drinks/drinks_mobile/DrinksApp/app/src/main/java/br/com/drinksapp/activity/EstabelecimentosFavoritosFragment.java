package br.com.drinksapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.adapter.EstabelecimentosFavoritosAdapter;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.db.DAODrinks;

/**
 * Created by Silvio Cedrim on 19/11/2017.
 */

public class EstabelecimentosFavoritosFragment extends Fragment{

    List<Estabelecimento> mEstabelecimentos;

    ListView mListView;

    DAODrinks mDAO;

    EstabelecimentosFavoritosAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDAO = new DAODrinks(getActivity());

        mEstabelecimentos = mDAO.consultarEstabelecimentosFavoritos();
        mAdapter = new EstabelecimentosFavoritosAdapter(getActivity(), mEstabelecimentos);
        EventBus.getDefault().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_lista_estabelecimentos_favoritos, container, false);
        mListView = (ListView)layout.findViewById(R.id.listaEstabelecimentosFavoritos);
        mListView.setAdapter(mAdapter);

        return  layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void atualizar(Estabelecimento estabelecimento){
        mEstabelecimentos.clear();
        mEstabelecimentos.addAll(mDAO.consultarEstabelecimentosFavoritos());
        mAdapter.notifyDataSetChanged();
    }
}
