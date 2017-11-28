package br.com.drinksapp.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.interfaces.OnBackPressedListener;
import br.com.drinksapp.interfaces.OnPedidoClick;

/**
 * Created by Silvio Cedrim on 14/11/2017.
 */

public class PedidosListFragment extends Fragment implements OnBackPressedListener {

    private ListView mListView;

    List<Pedido> mPedidos;

    private ProgressDialog pDialog;


    public PedidosListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saListViewvedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_list_pedidos, container, false);
        mListView = (ListView) layout.findViewById(R.id.listPedidos);

        mPedidos = new ArrayList<Pedido>();

        Usuarios usuario = new Usuarios(MySaveSharedPreference.getUserId(getActivity()));

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        initTask(usuario);

        return layout;
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    void initTask(Usuarios usuario) {
        new TaskPedidos().execute(usuario);
    }

    @Override
    public void doBack() {
        getActivity().getIntent().putExtra("EXIT", true);
        getActivity().finish();
    }

    class TaskPedidos extends AsyncTask<Usuarios, Void, Void> {
        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Carregando... aguarde!");
            showDialog();
        }

        @Override
        protected Void doInBackground(Usuarios... params) {
            mPedidos = DBConnectParser.listarPedidosdeCliente(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(mPedidos != null){

                mListView.setAdapter(new PedidosAdapter(getActivity(), mPedidos));
                mListView.setOnItemClickListener(new ListViewPedido());
            }
            hideDialog();
        }
    }


    class ListViewPedido implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (getActivity() instanceof OnPedidoClick) {
                Pedido pedido = (Pedido) parent.getItemAtPosition(position);
                ((OnPedidoClick) getActivity()).clicouNoPedido(pedido);
            }


        }

    }
}
