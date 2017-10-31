package br.com.drinksapp.pedido;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import br.com.drinksapp.R;


public class PedidoDetalheActivity extends AppCompatActivity
        implements PedidoDetalheFragment.AoEditarPedido,
        PedidoDialogFragment.AoSalvarPedido {

    public static final String EXTRA_PEDIDO = "Pedido";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_detalhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Pedido pedido = (Pedido)intent.getSerializableExtra(EXTRA_PEDIDO);
        exibirPedidoFragment(pedido);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void exibirPedidoFragment(Pedido pedido) {
        PedidoDetalheFragment fragment = PedidoDetalheFragment.novaInstancia(pedido);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentDetalheProduto, fragment, PedidoDetalheFragment.TAG_DETALHE);
        ft.commit();
    }
    @Override
    public void aoEditarpedido(Pedido pedido) {
        PedidoDialogFragment editNameDialog = PedidoDialogFragment.newInstance(pedido);
        editNameDialog.abrir(getSupportFragmentManager());
    }
    @Override
    public void salvouPedido(Pedido pedido) {
        PedidoRepositorio repo = new PedidoRepositorio(this);
        repo.salvar(pedido);
        exibirPedidoFragment(pedido);
        setResult(RESULT_OK);
    }


}
