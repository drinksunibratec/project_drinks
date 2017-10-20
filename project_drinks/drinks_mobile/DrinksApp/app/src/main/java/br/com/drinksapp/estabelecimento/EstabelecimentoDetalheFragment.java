package br.com.drinksapp.estabelecimento;

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


public class EstabelecimentoDetalheFragment extends Fragment {
    public static final String TAG_DETALHE = "tagDetalhe";
    private static final String EXTRA_ESTABELECIMENTO = "estabelecimento";
    TextView mTextNome;
    TextView mTextLogradouro;
    TextView mTextNumero;
    TextView mTextBairro;
    TextView mTextCidade;
    TextView mTextUf;
    TextView mTextCep;
    TextView mTextLatitude;
    TextView mTextLongetude;
    Estabelecimento mEstabelecimento;

    ShareActionProvider mShareActionProvider;

    public static EstabelecimentoDetalheFragment novaInstancia(Estabelecimento estabelecimento) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_ESTABELECIMENTO, estabelecimento);
        EstabelecimentoDetalheFragment fragment = new EstabelecimentoDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEstabelecimento = (Estabelecimento)
                getArguments().getSerializable(EXTRA_ESTABELECIMENTO);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(
                R.layout.fragment_detalhe_estabelecimento, container, false);
        mTextNome = (TextView)layout.findViewById(R.id.txtNome);
        mTextLogradouro = (TextView)layout.findViewById(R.id.txtLogradouro);
        mTextNumero = (TextView)layout.findViewById(R.id.txtNumero);
        mTextBairro = (TextView)layout.findViewById(R.id.txtBairro);
        mTextCidade = (TextView)layout.findViewById(R.id.txtCidade);
        mTextUf = (TextView)layout.findViewById(R.id.txtUf);
        mTextCep = (TextView)layout.findViewById(R.id.txtCep);
        mTextLatitude = (TextView)layout.findViewById(R.id.txtLatitude);
        mTextLongetude = (TextView)layout.findViewById(R.id.txtLongetude);
        if (mEstabelecimento != null) {
            mTextNome.setText(mEstabelecimento.nome);
            mTextLogradouro.setText(mEstabelecimento.logradouro);
            mTextNumero.setText(mEstabelecimento.numero);
            mTextBairro.setText(mEstabelecimento.bairro);
            mTextCidade.setText(mEstabelecimento.cidade);
            mTextUf.setText(mEstabelecimento.uf);
            mTextCep.setText(mEstabelecimento.cep);
            mTextLatitude.setText(mEstabelecimento.latitude);
            mTextLongetude.setText(mEstabelecimento.longetude);
        }
        return layout;


    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_estabelecimento_detalhe, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);
        String texto = getString(R.string.texto_compartilhar,
                mEstabelecimento.nome);
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
            if (activity instanceof AoEditarEstabelecimento) {
                AoEditarEstabelecimento aoEditarEstabelecimento = (AoEditarEstabelecimento)activity;
                aoEditarEstabelecimento.aoEditarestabelecimento(mEstabelecimento);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public Estabelecimento getEstabelecimento() {
        return mEstabelecimento;
    }
    public interface AoEditarEstabelecimento {
        void aoEditarestabelecimento(Estabelecimento estabelecimento);
    }
}

