package br.com.drinksapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.fragment.PedidoDetalheFragment;
import br.com.drinksapp.util.Constantes;

public class PedidoDetalheActivity extends AppCompatActivity {

    Pedido mPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_detalhe);

        Intent it = getIntent();
        mPedido = (Pedido)it.getSerializableExtra(Constantes.EXTRA_PEDIDO);

        PedidoDetalheFragment detalheFragment = PedidoDetalheFragment.novaInstancia(mPedido);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_detalhe_pedido , detalheFragment, Constantes.TAG_DETALHE)
                .commit();

    }
}
