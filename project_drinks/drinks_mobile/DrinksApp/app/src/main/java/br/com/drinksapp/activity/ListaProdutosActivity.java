package br.com.drinksapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.adapter.ProdutoAdapter;
import br.com.drinksapp.app.Constantes;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;

public class ListaProdutosActivity extends AppCompatActivity {

    ListView mListView;

    List<Produto> mProdutos;
    Estabelecimento mEstabelecimento;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_produtos_layout);
        mListView = (ListView)findViewById(R.id.listaProdutos);
        ajustarLista();
        mListView.setAdapter(new ProdutoAdapter(this, mProdutos));


    }

    private void ajustarLista(){
        mProdutos = new ArrayList<Produto>();
        mEstabelecimento = (Estabelecimento) getIntent().getSerializableExtra(Constantes.EXTRA_ESTABELECIMENTO);
        List<Produto> result = getIntent().getParcelableArrayListExtra(Constantes.EXTRA_LISTA_PRODUTOS);
        for (Produto p : result) {
            p.setEstabelecimento(mEstabelecimento);
            mProdutos.add(p);
        }
    }

}