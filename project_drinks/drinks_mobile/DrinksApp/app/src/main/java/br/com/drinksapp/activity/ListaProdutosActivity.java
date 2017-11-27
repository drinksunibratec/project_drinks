package br.com.drinksapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.adapter.ProdutoAdapter;
import br.com.drinksapp.bean.ItemCarrinhoCompras;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.util.Constantes;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;

public class ListaProdutosActivity extends AppCompatActivity {

    ListView mListView;

    List<Produto> mProdutos;

    Estabelecimento mEstabelecimento;

    TextView mTxtNomeEstabelecimento;

    TextView mTxtEnderecoEstabelecimento;

    DAODrinks mDAO;

    FloatingActionButton mBtnCarrinho;

    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_produtos_layout);
        mListView = (ListView) findViewById(R.id.listaProdutos);

        mDAO = new DAODrinks(this);

        mTxtNomeEstabelecimento = (TextView) findViewById(R.id.txtNomeEstabelecimentoProdutos);
        mTxtEnderecoEstabelecimento = (TextView) findViewById(R.id.txtEnderecoEstabelecimentoProdutos);

        mBtnCarrinho = (FloatingActionButton) findViewById(R.id.btn_carrinho);
        mBtnCarrinho.setOnClickListener(new BotaoCarrinho(this));

        mBtnCarrinho.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab_estabelecimento_favorito);
        fab.setOnClickListener(new BotaoFavorito());

        mDAO.deleteCarrinhoCompras();

        ajustarLista();
        inserirProdutos();
        mDAO.insertEstabelecimento(mEstabelecimento);

        mTxtNomeEstabelecimento.setText(mEstabelecimento.getNomeFantasia());
        mTxtEnderecoEstabelecimento.setText(mEstabelecimento.getRua() + ", " + mEstabelecimento.getNumero() + " - " + mEstabelecimento.getCidade() + " - " + mEstabelecimento.getUf());
        toggleFavorito();

        mListView.setAdapter(new ProdutoAdapter(this, mProdutos));
        mListView.setOnItemClickListener(new ClickProduto(this));

    }

    private void inserirProdutos() {
        for (Produto p : mProdutos) {
            if (p.getNome() != null) {
                mDAO.insertProdutoEstab(p);
            }
        }
    }

    private void ajustarLista() {
        mProdutos = new ArrayList<Produto>();
        mEstabelecimento = (Estabelecimento) getIntent().getSerializableExtra(Constantes.EXTRA_ESTABELECIMENTO);
        Bundle bundle = (Bundle) getIntent().getExtras().get(Constantes.EXTRA_BUNDLE);
        List<Produto> result = (List<Produto>) bundle.getSerializable(Constantes.EXTRA_PRODUTO);

        for (Produto p : result) {
            p.setEstabelecimento(mEstabelecimento);
            mProdutos.add(p);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.ADICIONAR_PRODUTOS_AO_CARRINHO) {
            if (resultCode == Constantes.RESULT_BACK) {
                Estabelecimento estabelecimento = (Estabelecimento) getIntent().getSerializableExtra(Constantes.EXTRA_ESTABELECIMENTO);
                mProdutos = mDAO.consultarProdutosPorEstabelecimento(estabelecimento);

                for (int i = 0; i < mProdutos.size(); i++) {
                    mProdutos.get(i).setEstabelecimento(estabelecimento);
                }
                if (mDAO.existeCarrinho()) {
                    mBtnCarrinho.setVisibility(View.VISIBLE);
                } else {
                    mBtnCarrinho.setVisibility(View.INVISIBLE);
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mDAO.deleteCarrinhoCompras();

    }

    class ClickProduto implements AdapterView.OnItemClickListener {

        Context context;

        public ClickProduto(Context context) {
            this.context = context;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Produto produto = (Produto) parent.getItemAtPosition(position);

            Intent it = new Intent(context, ProdutoDetalheActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constantes.EXTRA_PRODUTO, produto);
            it.putExtra(Constantes.EXTRA_BUNDLE, bundle);
            it.putExtra(Constantes.EXTRA_ESTABELECIMENTO, produto.getEstabelecimento());
            startActivityForResult(it, Constantes.ADICIONAR_PRODUTOS_AO_CARRINHO);
        }
    }

    class BotaoCarrinho implements View.OnClickListener {
        Context context;

        public BotaoCarrinho(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            List<ItemCarrinhoCompras> carrinho = mDAO.consultarCarrinhoDeCompras();
            Intent it = new Intent(context, CarrinhoDeComprasActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constantes.EXTRA_CARRINHO_COMPRAS, new ArrayList<ItemCarrinhoCompras>(carrinho));
            it.putExtra(Constantes.EXTRA_BUNDLE, bundle);
            it.putExtra(Constantes.EXTRA_ESTABELECIMENTO, mEstabelecimento);
            startActivity(it);
        }
    }

    public void toggleFavorito() {
        fab.setImageResource(
                mDAO.isEstabelecimentoFavorito(mEstabelecimento) ? R.drawable.ic_remove_favoritos : R.drawable.ic_add_favorito
        );
    }

    class BotaoFavorito implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (mDAO.isEstabelecimentoFavorito(mEstabelecimento)) {
                mDAO.deleteEstabelecimentoFavorito(mEstabelecimento);
            } else {
                mDAO.insertEstabelecimentoFavorito(mEstabelecimento);
            }
            toggleFavorito();
            EventBus.getDefault().post(mEstabelecimento);

        }
    }

}