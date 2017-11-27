package br.com.drinksapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.adapter.CarrinhoComprasAdapter;
import br.com.drinksapp.bean.ItemCarrinhoCompras;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.util.Constantes;
import br.com.drinksapp.util.Util;

public class CarrinhoDeComprasActivity extends AppCompatActivity {

    ListView mListView;

    List<ItemCarrinhoCompras> mCarrinho;

    TextView mTxtValorTotalPedido;

    Double valorTotalPedido;

    DAODrinks mDAO;

    Button mBtnComprar;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_de_compras);

        Bundle bundle = (Bundle)getIntent().getExtras().get(Constantes.EXTRA_BUNDLE);
        mCarrinho = (List<ItemCarrinhoCompras>)bundle.getSerializable(Constantes.EXTRA_CARRINHO_COMPRAS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCarrinho);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.lista_produtos_carrinho);

        mTxtValorTotalPedido = (TextView)findViewById(R.id.txtValorTotalPedido);

        valorTotalPedido = new Double(0);
        for(ItemCarrinhoCompras cc : mCarrinho){
            valorTotalPedido += cc.getPrecoTotal();
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String valor = df.format(valorTotalPedido + 8.00);

        mDAO  = new DAODrinks(this);
        mTxtValorTotalPedido.setText("R$ " + valor);

        mListView.setAdapter(new CarrinhoComprasAdapter(this, mCarrinho, mDAO, mTxtValorTotalPedido));

        mBtnComprar = (Button)findViewById(R.id.btn_comprar);

        mBtnComprar.setOnClickListener(new BotaoComprar());

        pDialog = new ProgressDialog(this, R.style.MyDialogTheme);
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
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Constantes.ADICIONAR_PRODUTOS_AO_CARRINHO);
    }

    class BotaoComprar implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            Intent it = new Intent(CarrinhoDeComprasActivity.this, PagamentoActivity.class);

            List<ItemCarrinhoCompras> carrinho = mDAO.consultarCarrinhoDeCompras();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constantes.EXTRA_CARRINHO_COMPRAS, new ArrayList<ItemCarrinhoCompras>(carrinho));
            it.putExtra(Constantes.EXTRA_BUNDLE, bundle);
            startActivity(it);
        }
    }
}
