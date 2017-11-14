package br.com.drinksapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.adapter.PedidosAdapter;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.http.PedidosTask;
import br.com.drinksapp.interfaces.OnPedidoClick;

/**
 * Created by Silvio Cedrim on 14/11/2017.
 */

public class PedidosListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Pedido>> {

    private ListView mListView;

    LoaderManager mLoader;

    List<Pedido> mPedidos;

    List<PedidoProdutos> mPedidosProdutos;

    TextView mTxtListaVazia;


    public PedidosListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saListViewvedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_list_pedidos, container, false);
        mListView = (ListView) layout.findViewById(R.id.listPedidos);
        mTxtListaVazia = (TextView) layout.findViewById(R.id.txtListaVazia);

        mPedidos = new ArrayList<Pedido>();

        mLoader = getLoaderManager();
        mLoader.initLoader(0, null, this);

        if (mPedidos.size() == 0) {
            mListView.setEmptyView(mTxtListaVazia);
        } else {
            mListView.setAdapter(new PedidosAdapter(getActivity(), mPedidos));
            mListView.setOnItemClickListener(new ListViewHeroi());
        }

        // Inflate the layout for this fragment
        return layout;
    }

    @Override
    public Loader<List<Pedido>> onCreateLoader(int id, Bundle args) {
        long codUusario = MySaveSharedPreference.getUserId(getActivity());
        Usuarios usuario = new Usuarios(codUusario);
        return new PedidosTask(getActivity(), usuario);
    }

    @Override
    public void onLoadFinished(Loader<List<Pedido>> loader, List<Pedido> data) {
        if (data != null) {
            mPedidos = data;
            mListView.setAdapter(new PedidosAdapter(getActivity(), data));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Pedido>> loader) {

    }

    class ListViewHeroi implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (getActivity() instanceof OnPedidoClick) {
                Pedido pedido = (Pedido) parent.getItemAtPosition(position);
                ((OnPedidoClick) getActivity()).clicouNoPedido(pedido);
            }


        }

    }
}
