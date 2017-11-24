package br.com.drinksapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.adapter.ProdutoPesquisaAdapter;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.util.Constantes;

public class ComparaPrecosActivity extends AppCompatActivity {

    Spinner mSpinnerOrdemPreços;

    private ProgressDialog pDialog;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compara_precos);

        mSpinnerOrdemPreços = (Spinner)findViewById(R.id.ordem_pesquisa);
        addItensSpinnerOrdemPreco();

        mListView = (ListView)findViewById(R.id.listaProdutosPesquisa);

        pDialog = new ProgressDialog(this, R.style.MyDialogTheme);
        pDialog.setCancelable(false);

        String ean = getIntent().getStringExtra(Constantes.EXTRA_EAN);
        initTaskListaProdutoPorEan(ean);
    }

    public void addItensSpinnerOrdemPreco(){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spiner_item, getResources().getStringArray(R.array.ordem_precos));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerOrdemPreços.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.white_color), PorterDuff.Mode.SRC_ATOP);
        mSpinnerOrdemPreços.setAdapter(adapter);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    class TaskBuscarProdutosPorEan extends AsyncTask<String, Void, List<Produto>>{

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Carregando... aguarde!");
            showDialog();
        }

        @Override
        protected List<Produto> doInBackground(String... params) {
            List<Produto> produtos = null;
            try {
                produtos = DBConnectParser.listarProdutosPorEan(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return produtos;
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {

            if(produtos != null){
                mListView.setAdapter(new ProdutoPesquisaAdapter(getApplicationContext(), produtos));
                mListView.setOnItemClickListener(new ClicarNoProduto());
            }

            hideDialog();
        }
    }

    private class TaskListarProdutosPorEstabelecimento extends AsyncTask<Estabelecimento, Void, Intent> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Carregando... Aguarde!");
            showDialog();
        }

        @Override
        protected Intent doInBackground(Estabelecimento... estabelecimento) {
            ArrayList<Produto> retorno = null;
            List<Produto> produtos = null;
            Intent it = null;
            try {
                produtos = DBConnectParser.listProdutosPorEstabelecimento(estabelecimento[0]);
                retorno = new ArrayList<Produto>(produtos);
                if (retorno != null) {
                    it = new Intent(ComparaPrecosActivity.this, ListaProdutosActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.EXTRA_PRODUTO, retorno);
                    it.putExtra(Constantes.EXTRA_BUNDLE, bundle);
                    it.putExtra(Constantes.EXTRA_ESTABELECIMENTO, estabelecimento[0]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return it;
        }

        @Override
        protected void onPostExecute(Intent it) {
            startActivity(it);
            hideDialog();

        }
    }

    void initTaskListaProdutoPorEstabelecimento(Estabelecimento estabelecimento){
        new TaskListarProdutosPorEstabelecimento().execute(estabelecimento);
    }

    void initTaskListaProdutoPorEan(String ean){
        new TaskBuscarProdutosPorEan().execute(ean);
    }

    class ClicarNoProduto implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Produto p = (Produto)parent.getItemAtPosition(position);
            Estabelecimento estabelecimento = p.getEstabelecimento();
            initTaskListaProdutoPorEstabelecimento(estabelecimento);

        }
    }

}
