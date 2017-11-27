package br.com.drinksapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.adapter.ProdutoPesquisaAdapter;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.fragment.EstabelecimentosFavoritosFragment;
import br.com.drinksapp.fragment.MapFragment;
import br.com.drinksapp.fragment.PedidosListFragment;
import br.com.drinksapp.fragment.ProdutosFavoritosFragment;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.interfaces.OnBackPressedListener;
import br.com.drinksapp.interfaces.OnPedidoClick;
import br.com.drinksapp.util.Constantes;


public class MainActivity extends AppCompatActivity implements OnPedidoClick {


    SelectorPageAdapter selectorPageAdapter;

    MapFragment mMapFragment;

    PedidosListFragment mPedidosListFragment;

    EstabelecimentosFavoritosFragment mEstabelecimentosFavoritosFragment;

    ProdutosFavoritosFragment mProdutosFavoritosFragment;

    ViewPager mViewPager;

    AutoCompleteTextView mTxtPesquisa;

    List<Produto> mProdutos;

    DAODrinks mDAO;

    protected OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTxtPesquisa = (AutoCompleteTextView)findViewById(R.id.edt_pesquisa_precos);
        mDAO = new DAODrinks(this);

        new TaskBuscarProdutos().execute();
        buildViewPager();
    }

    private void buildViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(selectorPageAdapter);

        TabLayout tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void clicouNoPedido(Pedido pedido) {
        Intent it = new Intent(this, PedidoDetalheActivity.class);
        it.putExtra(Constantes.EXTRA_PEDIDO, pedido);
        startActivity(it);

    }

    public class SelectorPageAdapter extends FragmentPagerAdapter {

        public SelectorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mMapFragment == null) {
                        mMapFragment = new MapFragment();
                    }
                    return mMapFragment;
                case 1:
                    if (mPedidosListFragment == null) {
                        mPedidosListFragment = new PedidosListFragment();
                    }
                    return mPedidosListFragment;

                case 2:
                    if (mProdutosFavoritosFragment == null) {
                        mProdutosFavoritosFragment = new ProdutosFavoritosFragment();
                    }
                    return mProdutosFavoritosFragment;
                default:

                    if (mEstabelecimentosFavoritosFragment == null) {
                        mEstabelecimentosFavoritosFragment = new EstabelecimentosFavoritosFragment();
                    }
                    return mEstabelecimentosFavoritosFragment;


            }

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Mapa";
                case 1:
                    return "Pedidos";
                case 2:
                    return "Produtos Favoritos";
                default:
                    return "Estabelecimentos Favoritos";
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_perfil:
                Intent it = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(it);
                return true;
            case R.id.menu_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        MySaveSharedPreference.clearSharedPreference(this);
        Intent it = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(it);
    }

    class TaskBuscarProdutos extends AsyncTask<Void, Void, List<Produto>>{

        @Override
        protected List<Produto> doInBackground(Void... params) {
            List<Produto> produtos = new ArrayList<Produto>();
            try {
                produtos = DBConnectParser.listarTodosOsProdutos();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return produtos;
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {

            if(produtos != null){

                for(Produto p : produtos){
                    mDAO.insertProduto(p);
                }
                mProdutos = produtos;

                String[] listaNomesProdutos = new String[mProdutos.size()];
                for(int i = 0; i < mProdutos.size(); i++){
                    listaNomesProdutos[i] = mProdutos.get(i).getNome();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (MainActivity.this,R.layout.item_pesquisa_produto, listaNomesProdutos);
                mTxtPesquisa.setThreshold(3);
                mTxtPesquisa.setAdapter(adapter);

                mTxtPesquisa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Produto p = new Produto(parent.getItemAtPosition(position).toString());
                        String ean = mDAO.getEANProduto(p);
                        Intent it = new  Intent(MainActivity.this, ComparaPrecosActivity.class);
                        it.putExtra(Constantes.EXTRA_EAN, ean);
                        startActivity(it);
                    }
                });
            }
        }
    }

}