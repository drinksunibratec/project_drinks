package br.com.drinksapp.pedido;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import br.com.drinksapp.R;


public class PedidoActivity extends AppCompatActivity
        implements
        PedidoListFragment.AoClicarNoPedido,
        SearchView.OnQueryTextListener,
        MenuItemCompat.OnActionExpandListener,
        PedidoDialogFragment.AoSalvarPedido,
        PedidoDetalheFragment.AoEditarPedido,
        PedidoListFragment.AoExcluirPedidos {

    public static final int REQUEST_EDITAR_PEDIDO = 0;
    private long mIdSelecionado;

    private FragmentManager mFragmentManager;
    private PedidoListFragment mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        mFragmentManager = getSupportFragmentManager();
        mListFragment = (PedidoListFragment)
                mFragmentManager.findFragmentById(R.id.fragmentLista);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDITAR_PEDIDO && resultCode == RESULT_OK) {
            mListFragment.limparBusca();
        }
    }

    public void clicouNoPedido(Pedido pedido) {
        mIdSelecionado = pedido.id;
        if (isTablet()) {
            PedidoDetalheFragment fragment =
                    PedidoDetalheFragment.novaInstancia(pedido);
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.contentDetalheProduto, fragment,
                    PedidoDetalheFragment.TAG_DETALHE);
            ft.commit();
        } else {

            Intent it = new Intent(this, PedidoDetalheActivity.class);
            it.putExtra(PedidoDetalheActivity.EXTRA_PEDIDO, pedido);
            startActivityForResult(it, REQUEST_EDITAR_PEDIDO);
        }
    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }
    private boolean isSmartphone() {
        return getResources().getBoolean(R.bool.smartphone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pedido, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.hint_busca));
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                PedidoActivity.SobreDialogFragment dialogFragment = new PedidoActivity.SobreDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "sobre");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }
    @Override
    public boolean onQueryTextChange(String s) {
        mListFragment.buscar(s);
        return false;
    }
    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true; // para expandir a view
    }
    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        mListFragment.limparBusca();
        return true; // para voltar ao normal
    }

    public static class SobreDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_NEGATIVE){
                        Intent it = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.nglauber.com.br"));
                        startActivity(it);
                    }
                }
            };
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.sobre_titulo)
                    .setMessage(R.string.sobre_mensagem)
                    .setPositiveButton(android.R.string.ok, null)
                    .setNegativeButton(R.string.sobre_botao_site, listener)
                    .create();
            return dialog;
        }
    }

    @Override
    public void salvouPedido(Pedido pedido) {
        PedidoRepositorio repo = new PedidoRepositorio(this);
        repo.salvar(pedido);
        mListFragment.limparBusca();
        if (isTablet()) {
            clicouNoPedido(pedido);
        }
    }

    public void adicionarPedidoClick(View v){
        PedidoDialogFragment pedidoDialogFragment =
                PedidoDialogFragment.newInstance(null);
        pedidoDialogFragment.abrir(getSupportFragmentManager());
    }

    @Override
    public void aoEditarpedido(Pedido pedido) {
        PedidoDialogFragment editNameDialog = PedidoDialogFragment.newInstance(pedido);
        editNameDialog.abrir(getSupportFragmentManager());
    }
    @Override
    public void exclusaoCompleta(List<Pedido> excluidos) {
        PedidoDetalheFragment f = (PedidoDetalheFragment)
                mFragmentManager.findFragmentByTag(PedidoDetalheFragment.TAG_DETALHE);
        if (f != null) {
            boolean encontrou = false;
            for (Pedido pe : excluidos) {
                if (pe.id == mIdSelecionado) {
                    encontrou = true;
                    break;
                }
            }
            if (encontrou) {
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.remove(f);
                ft.commit();
            }
        }
    }

}