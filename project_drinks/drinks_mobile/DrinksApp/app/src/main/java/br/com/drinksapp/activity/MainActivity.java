package br.com.drinksapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.fragment.EstabelecimentosFavoritosFragment;
import br.com.drinksapp.fragment.MapFragment;
import br.com.drinksapp.fragment.PedidosListFragment;
import br.com.drinksapp.fragment.ProdutosFavoritosFragment;
import br.com.drinksapp.interfaces.OnPedidoClick;
import br.com.drinksapp.util.Constantes;


public class MainActivity extends AppCompatActivity implements OnPedidoClick {


    SelectorPageAdapter selectorPageAdapter;

    MapFragment mMapFragment;

    PedidosListFragment mPedidosListFragment;

    EstabelecimentosFavoritosFragment mEstabelecimentosFavoritosFragment;

    ProdutosFavoritosFragment mProdutosFavoritosFragment;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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


}