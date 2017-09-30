package br.com.drinksapp.produto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import br.com.drinksapp.R;


public class ProdutoDetalheActivity extends AppCompatActivity
        implements ProdutoDetalheFragment.AoEditarProduto,
        ProdutoDialogFragment.AoSalvarProduto {

    public static final String EXTRA_PRODUTO = "Produto";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Produto produto = (Produto)intent.getSerializableExtra(EXTRA_PRODUTO);
        exibirProdutoFragment(produto);
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
    private void exibirProdutoFragment(Produto produto) {
        ProdutoDetalheFragment fragment = ProdutoDetalheFragment.novaInstancia(produto);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.detalhe, fragment, ProdutoDetalheFragment.TAG_DETALHE);
        ft.commit();
    }
    @Override
    public void aoEditarproduto(Produto produto) {
        ProdutoDialogFragment editNameDialog = ProdutoDialogFragment.newInstance(produto);
        editNameDialog.abrir(getSupportFragmentManager());
    }
    @Override
    public void salvouProduto(Produto produto) {
        ProdutoRepositorio repo = new ProdutoRepositorio(this);
        repo.salvar(produto);
        exibirProdutoFragment(produto);
        setResult(RESULT_OK);
    }


}
