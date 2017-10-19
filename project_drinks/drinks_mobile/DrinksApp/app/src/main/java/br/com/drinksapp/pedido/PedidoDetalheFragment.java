package br.com.drinksapp.pedido;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.drinksapp.R;


public class PedidoDetalheFragment extends Fragment {
    public static final String TAG_DETALHE = "tagDetalhe";
    private static final String EXTRA_PEDIDO = "pedido";
    TextView mTextNomepedido;
    TextView mTextValor;
    TextView mTextId_usuario;
    TextView mTextId_produto;
    Pedido mPedido;

    ShareActionProvider mShareActionProvider;

    public static PedidoDetalheFragment novaInstancia(Pedido pedido) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PEDIDO, pedido);
        PedidoDetalheFragment fragment = new PedidoDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPedido = (Pedido)
                getArguments().getSerializable(EXTRA_PEDIDO);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(
                R.layout.fragment_detalhe_pedido, container, false);
        mTextNomepedido = (TextView)layout.findViewById(R.id.txtNomepedido);
        mTextValor = (TextView)layout.findViewById(R.id.txtValor);
        mTextId_usuario = (TextView)layout.findViewById(R.id.txtId_usuario);
        mTextId_produto = (TextView)layout.findViewById(R.id.txtId_produto);
        if (mPedido != null) {
            mTextNomepedido.setText(mPedido.nomepedido);
            mTextValor.setText(mPedido.valor);
            mTextId_usuario.setText(mPedido.id_usuario);
            mTextId_produto.setText(mPedido.id_produto);
        }
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pedido_detalhe, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        String texto = getString(R.string.texto_compartilhar,
                mPedido.nomepedido);
        Intent it = new Intent(Intent.ACTION_SEND);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        it.setType("text/plain");
        it.putExtra(Intent.EXTRA_TEXT, texto);
        mShareActionProvider.setShareIntent(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.acao_editar) {
            Activity activity = getActivity();
            if (activity instanceof PedidoDetalheFragment.AoEditarPedido) {
                PedidoDetalheFragment.AoEditarPedido aoEditarPedido = (PedidoDetalheFragment.AoEditarPedido)activity;
                aoEditarPedido.aoEditarpedido(mPedido);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public Pedido getPedido() {
        return mPedido;
    }
    public interface AoEditarPedido {
        void aoEditarpedido(Pedido pedido);
    }
}
