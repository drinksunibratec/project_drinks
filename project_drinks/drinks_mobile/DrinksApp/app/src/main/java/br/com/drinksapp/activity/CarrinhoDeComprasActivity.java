package br.com.drinksapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.adapter.CarrinhoComprasAdapter;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.util.Constantes;

public class CarrinhoDeComprasActivity extends AppCompatActivity {

    ListView mListView;

    List<PedidoProdutos> mProdutos;

    TextView mTxtValorTotalPedido;

    Double valorTotalPedido;

    DAODrinks mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_de_compras);

        mProdutos = getIntent().getParcelableArrayListExtra(Constantes.EXTRA_CARRINHO_COMPRAS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCarrinho);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.lista_produtos_carrinho);

        mTxtValorTotalPedido = (TextView)findViewById(R.id.txtValorTotalPedido);

        for(PedidoProdutos pp : mProdutos){
            valorTotalPedido += pp.getPrecoTotal();
        }

        mDAO  = new DAODrinks(this);
        mTxtValorTotalPedido.setText(String.valueOf(valorTotalPedido));

        mListView.setAdapter(new CarrinhoComprasAdapter(this, mProdutos, mDAO));
    }
}
