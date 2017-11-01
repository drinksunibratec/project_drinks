package br.com.drinksapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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

    DAODrinks mDAO;

    Button mBtnCarrinho;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_produtos_layout);
        mListView = (ListView) findViewById(R.id.listaProdutos);
        mBtnCarrinho = (Button) findViewById(R.id.btn_carrinho);
        mBtnCarrinho.setOnClickListener(new BotaoCarrinho(this));

        mDAO = new DAODrinks(this);
        mDAO.deleteCarrinhoCompras();

        ajustarLista();
        inserirProdutos();
        mDAO.insertEstabelecimento(mEstabelecimento);
        mListView.setAdapter(new ProdutoAdapter(this, mProdutos));
        mListView.setOnItemClickListener(new ClickProduto(this));

    }

    private void inserirProdutos() {
        for (Produto p : mProdutos) {
            if(p.getNome() != null){
                mDAO.insertProduto(p);
            }
        }
    }

    private void ajustarLista() {
        mProdutos = new ArrayList<Produto>();
        mEstabelecimento = (Estabelecimento) getIntent().getSerializableExtra(Constantes.EXTRA_ESTABELECIMENTO);
        List<Produto> result = getIntent().getParcelableArrayListExtra(Constantes.EXTRA_LISTA_PRODUTOS);

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
            it.putExtra(Constantes.EXTRA_PRODUTO, produto);
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
            it.putParcelableArrayListExtra(Constantes.EXTRA_CARRINHO_COMPRAS, new ArrayList<Parcelable>(carrinho));
            startActivity(it);
        }
    }


}