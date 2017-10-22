package br.com.drinksapp.produto;


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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.drinksapp.R;
import br.com.drinksapp.activity.MainActivity;
import br.com.drinksapp.pedido.PedidoActivity;


public class ProdutoDetalheFragment extends Fragment {
    public static final String TAG_DETALHE = "tagDetalhe";
    private static final String EXTRA_PRODUTO = "produto";
    TextView mTextDescricao;
    TextView mTextGelada;
    TextView mTextNome;
    TextView mTextPreco;
    TextView mTextCodEstabelecimento;
    Produto mProduto;

    ShareActionProvider mShareActionProvider;

    public static ProdutoDetalheFragment novaInstancia(Produto produto) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_PRODUTO, produto);
        ProdutoDetalheFragment fragment = new ProdutoDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduto = (Produto)
                getArguments().getSerializable(EXTRA_PRODUTO);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(
                R.layout.fragment_detalhe_produto, container, false);
        mTextDescricao = (TextView)layout.findViewById(R.id.txtDescricao);
        mTextGelada = (TextView)layout.findViewById(R.id.txtGelada);
        mTextNome = (TextView)layout.findViewById(R.id.txtNome);
        mTextPreco = (TextView)layout.findViewById(R.id.txtPreco);
        mTextCodEstabelecimento = (TextView)layout.findViewById(R.id.txtCodEstabelecimento);
        if (mProduto != null) {
            mTextDescricao.setText(mProduto.descricao);
            mTextGelada.setText(mProduto.gelada);
            mTextNome.setText(mProduto.nome);
            mTextPreco.setText(mProduto.preco);
            mTextCodEstabelecimento.setText(mProduto.codEstabelecimento);
        }
        return layout;


    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_produto_detalhe, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        String texto = getString(R.string.texto_compartilhar,
                mProduto.nome);
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
            if (activity instanceof AoEditarProduto) {
                AoEditarProduto aoEditarProduto = (AoEditarProduto)activity;
                aoEditarProduto.aoEditarproduto(mProduto);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public Produto getProduto() {
        return mProduto;
    }
    public interface AoEditarProduto {
        void aoEditarproduto(Produto produto);
    }
}
