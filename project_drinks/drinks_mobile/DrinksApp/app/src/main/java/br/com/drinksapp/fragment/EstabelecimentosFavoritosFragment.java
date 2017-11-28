package br.com.drinksapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.adapter.EstabelecimentosFavoritosAdapter;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.interfaces.OnBackPressedListener;

/**
 * Created by Silvio Cedrim on 19/11/2017.
 */

public class EstabelecimentosFavoritosFragment extends Fragment implements OnBackPressedListener {

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
        mListView = (ListView) layout.findViewById(R.id.listaEstabelecimentosFavoritos);

        mListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 1, Menu.NONE, "Compartilhar");
            }
        });

        mListView.setAdapter(mAdapter);

        return layout;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        switch (item.getItemId()) {
            case 1:
                compartilhar(position);
                break;
        }
        return super.onContextItemSelected(item);
    }


    void compartilhar(int position) {

        Estabelecimento e = (Estabelecimento)mAdapter.getItem(position);

        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Conheça o: " + e.getNomeFantasia() + " no Drinks!");
        startActivity(whatsappIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void atualizar(Estabelecimento estabelecimento) {
        mEstabelecimentos.clear();
        mEstabelecimentos.addAll(mDAO.consultarEstabelecimentosFavoritos());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void doBack() {
        getActivity().finish();
    }

}
