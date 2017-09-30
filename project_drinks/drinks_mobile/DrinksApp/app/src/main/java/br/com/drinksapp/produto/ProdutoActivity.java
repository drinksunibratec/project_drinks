package br.com.drinksapp.produto;

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


public class ProdutoActivity extends AppCompatActivity
        implements
        ProdutoListFragment.AoClicarNoProduto,
        SearchView.OnQueryTextListener,
        MenuItemCompat.OnActionExpandListener,
        ProdutoDialogFragment.AoSalvarProduto,
        ProdutoDetalheFragment.AoEditarProduto,
        ProdutoListFragment.AoExcluirProdutos {

    public static final int REQUEST_EDITAR_PRODUTO = 0;
    private long mIdSelecionado;

    private FragmentManager mFragmentManager;
    private ProdutoListFragment mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        mFragmentManager = getSupportFragmentManager();
        mListFragment = (ProdutoListFragment)
                mFragmentManager.findFragmentById(R.id.fragmentLista);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDITAR_PRODUTO && resultCode == RESULT_OK) {
            mListFragment.limparBusca();
        }
    }

    public void clicouNoProduto(Produto produto) {
        mIdSelecionado = produto.id;
        if (isTablet()) {
            ProdutoDetalheFragment fragment =
                    ProdutoDetalheFragment.novaInstancia(produto);
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.detalhe, fragment,
                    ProdutoDetalheFragment.TAG_DETALHE);
            ft.commit();
        } else {

            Intent it = new Intent(this, ProdutoDetalheActivity.class);
            it.putExtra(ProdutoDetalheActivity.EXTRA_PRODUTO, produto);
            startActivityForResult(it, REQUEST_EDITAR_PRODUTO);
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
        getMenuInflater().inflate(R.menu.menu_produto, menu);
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
                SobreDialogFragment dialogFragment = new SobreDialogFragment();
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
    public void salvouProduto(Produto produto) {
        ProdutoRepositorio repo = new ProdutoRepositorio(this);
        repo.salvar(produto);
        mListFragment.limparBusca();
        if (isTablet()) {
            clicouNoProduto(produto);
        }
    }

    public void adicionarProdutoClick(View v){
        ProdutoDialogFragment boletimDialogFragment =
                ProdutoDialogFragment.newInstance(null);
        boletimDialogFragment.abrir(getSupportFragmentManager());
    }

    @Override
    public void aoEditarproduto(Produto produto) {
        ProdutoDialogFragment editNameDialog = ProdutoDialogFragment.newInstance(produto);
        editNameDialog.abrir(getSupportFragmentManager());
    }
    @Override
    public void exclusaoCompleta(List<Produto> excluidos) {
        ProdutoDetalheFragment f = (ProdutoDetalheFragment)
                mFragmentManager.findFragmentByTag(ProdutoDetalheFragment.TAG_DETALHE);
        if (f != null) {
            boolean encontrou = false;
            for (Produto p : excluidos) {
                if (p.id == mIdSelecionado) {
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
