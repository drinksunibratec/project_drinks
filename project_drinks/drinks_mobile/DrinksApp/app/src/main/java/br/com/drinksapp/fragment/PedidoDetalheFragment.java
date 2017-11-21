package br.com.drinksapp.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.activity.ProdutoDetalheActivity;
import br.com.drinksapp.adapter.PedidoProdutosAdapter;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.util.Constantes;

public class PedidoDetalheFragment extends Fragment {

    TextView mTxtNomeEstabelecimentoDetalhe;

    TextView mTxtDataPedidoDetalhe;

    TextView mTxtValorTotalDetalhe;

    TextView mTxtStatusDetalhe;

    Pedido mPedido;

    List<PedidoProdutos> mProdutos;

    ListView mListProdutos;

    FloatingActionButton fab;

    private ProgressDialog pDialog;

    public PedidoDetalheFragment() {
    }

    public static PedidoDetalheFragment novaInstancia(Pedido pedido) {
        PedidoDetalheFragment fragment = new PedidoDetalheFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constantes.EXTRA_PEDIDO, pedido);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPedido = (Pedido)getArguments().getSerializable(Constantes.EXTRA_PEDIDO);
        pDialog = new ProgressDialog(getActivity(), R.style.MyDialogTheme);
        pDialog.setCancelable(false);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout =  inflater.inflate(R.layout.fragment_pedido_detalhe, container, false);

        if(mPedido != null){

            mTxtNomeEstabelecimentoDetalhe = (TextView)layout.findViewById(R.id.txtNomeEstabelecimentoDetalhe);
            mTxtDataPedidoDetalhe = (TextView)layout.findViewById(R.id.txtDataPedidoDetalhe);
            mTxtValorTotalDetalhe = (TextView)layout.findViewById(R.id.txtValorTotalDetalhe);
            mTxtStatusDetalhe = (TextView)layout.findViewById(R.id.txtStatusDetalhe);

            mTxtNomeEstabelecimentoDetalhe.setText(getString(R.string.estabelecimento) + ": " + mPedido.getEstabelecimento().getNomeFantasia());
            mTxtDataPedidoDetalhe.setText(getString(R.string.data_pedido) + ": "  + mPedido.getDataPedido());
            mTxtValorTotalDetalhe.setText(getString(R.string.valor_total_pedido) + ": R$ "  + mPedido.getValorTotal());
            mTxtStatusDetalhe.setText(getString(R.string.status) + ":"  + mPedido.getStatus());

            mProdutos = mPedido.getPedidoProdutos();

            fab = (FloatingActionButton) layout.findViewById(R.id.fab_cacel_pedido);
            fab.setOnClickListener(new BotaoCancelar());
            if(!mPedido.getStatus().equals("AGUARDANDO")){
                fab.setVisibility(View.INVISIBLE);
            }


            mListProdutos = (ListView)layout.findViewById(R.id.lista_produto_detalhe_pedidos);
            mListProdutos.setAdapter(new PedidoProdutosAdapter(getActivity(), mProdutos));


        }

        return layout;
    }

    class TaskAtualizarPedido extends AsyncTask<Pedido, Void, Pedido>{

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Cancelando... aguarde!");
            showDialog();
        }

        @Override
        protected Pedido doInBackground(Pedido... params) {
            Pedido pedido = null;
            try {
                pedido = DBConnectParser.atualizarStatusPedido(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return pedido;
        }

        @Override
        protected void onPostExecute(Pedido pedido) {

            if(pedido.getStatus().equals("CANCELADO")){
                mTxtStatusDetalhe.setText(getString(R.string.status) + ": "  + mPedido.getStatus());
                fab.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Pedido cancelado com sucesso!", Toast.LENGTH_LONG).show();

            }
            hideDialog();
        }
    }

    void initTaskAtualizarStatusPedido(Pedido pedido){
        new TaskAtualizarPedido().execute(pedido);
    }

    private class BotaoCancelar implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);

            builder.setMessage(R.string.confirmar_cancelamento)
                    .setTitle(R.string.dialog_cancelamento);

            builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mPedido.setStatus("CANCELADO");
                    dialog.dismiss();
                    initTaskAtualizarStatusPedido(mPedido);
                }
            });
            builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
