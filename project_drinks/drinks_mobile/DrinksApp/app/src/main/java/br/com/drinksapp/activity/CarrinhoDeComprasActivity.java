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

        mCarrinho = getIntent().getParcelableArrayListExtra(Constantes.EXTRA_CARRINHO_COMPRAS);

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
        String valor = df.format(valorTotalPedido);

        mDAO  = new DAODrinks(this);
        mTxtValorTotalPedido.setText("R$ " + valor);

        mListView.setAdapter(new CarrinhoComprasAdapter(this, mCarrinho, mDAO, mTxtValorTotalPedido));

        mBtnComprar = (Button)findViewById(R.id.btn_comprar);

        mBtnComprar.setOnClickListener(new BotaoComprar());

        pDialog = new ProgressDialog(this);
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

    private class TaskCadastrarCarrinho extends AsyncTask<List<ItemCarrinhoCompras>, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Criando pedido ... Aguarde!");
            showDialog();
        }

        @Override
        protected Boolean doInBackground(List<ItemCarrinhoCompras>... params) {

            ItemCarrinhoCompras carrinhoCompras = params[0].get(0);

            Pedido pedido = new Pedido();
            pedido.setCodEstabelecimento(carrinhoCompras.getCodEstabelcimento());
            pedido.setCodUsuario(MySaveSharedPreference.getUserId(getApplicationContext()));
            pedido.setValorTotal(valorTotalPedido);
            pedido.setDataPedido(Util.getDataAtual());
            pedido.setStatus("AGUARDANDO");


            Pedido retorno = DBConnectParser.inserirPedido(pedido);
            List<PedidoProdutos> produtos = new ArrayList<PedidoProdutos>();
            List<ItemCarrinhoCompras> carrinhoTemp = params[0];

            for(int i = 0; i < carrinhoTemp.size(); i++){
                PedidoProdutos p = new PedidoProdutos();
                p.setCodPedido(retorno.getCodPedido());
                p.setCodProduto(carrinhoTemp.get(i).getProduto().getCodProduto());
                p.setPreco(carrinhoTemp.get(i).getPreco());
                p.setPrecoTotal(carrinhoTemp.get(i).getPrecoTotal());
                p.setQuantidade(carrinhoTemp.get(i).getQuantidade());
                produtos.add(p);
            }

            boolean inseriu = DBConnectParser.inserirProdutosPedido(produtos);

            return inseriu;
        }

        @Override
        protected void onPostExecute(Boolean inseriu) {
            if(inseriu){
                Toast.makeText(getApplicationContext(), "Seu pedido foi gerado com sucesso!", Toast.LENGTH_LONG).show();
                mDAO.deleteCarrinhoCompras();
                Intent it = new Intent(CarrinhoDeComprasActivity.this, MainActivity.class);
                startActivity(it);
            }


        }
    }

    private void initAsyncTask(List<ItemCarrinhoCompras> carrinhoComprases){
        TaskCadastrarCarrinho taskCadastrarCarrinho = new TaskCadastrarCarrinho();
        taskCadastrarCarrinho.execute(carrinhoComprases);
    }

    class BotaoComprar implements View.OnClickListener{

        @Override
        public void onClick(View v) {

//            initAsyncTask(mCarrinho);

            Intent it = new Intent(CarrinhoDeComprasActivity.this, PagamentoActivity.class);

            List<ItemCarrinhoCompras> carrinho = mDAO.consultarCarrinhoDeCompras();
            it.putParcelableArrayListExtra(Constantes.EXTRA_CARRINHO_COMPRAS, new ArrayList<Parcelable>(carrinho));
            startActivity(it);
        }
    }
}
