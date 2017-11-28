package br.com.drinksapp.fragment;


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
import br.com.drinksapp.adapter.ProdutoAdapter;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.interfaces.OnBackPressedListener;

public class ProdutosFavoritosFragment extends Fragment implements OnBackPressedListener{

    List<Produto> mProdutos;

    ListView mListView;

    DAODrinks mDAO;

    ProdutoAdapter mAdapter;


    public ProdutosFavoritosFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDAO = new DAODrinks(getActivity());

        mProdutos = mDAO.consultarProdutosFavoritos();
        mAdapter = new ProdutoAdapter(getActivity(), mProdutos);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_produtos_favoritos, container, false);
        mListView = (ListView)layout.findViewById(R.id.listaProdutosFavoritos);
        mListView.setAdapter(mAdapter);

        return  layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void atualizar(Produto produto){
        mProdutos.clear();
        mProdutos.addAll(mDAO.consultarProdutosFavoritos());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void doBack() {
        getActivity().getIntent().putExtra("EXIT", true);
        getActivity().onBackPressed();
    }
}
