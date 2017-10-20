package br.com.drinksapp.estabelecimento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import br.com.drinksapp.R;


public class EstabelecimentoDetalheActivity extends AppCompatActivity
        implements EstabelecimentoDetalheFragment.AoEditarEstabelecimento,
        EstabelecimentoDialogFragment.AoSalvarEstabelecimento {

    public static final String EXTRA_ESTABELECIMENTO = "Estabelecimento";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento_detalhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Estabelecimento estabelecimento = (Estabelecimento)intent.getSerializableExtra(EXTRA_ESTABELECIMENTO);
        exibirEstabelecimentoFragment(estabelecimento);
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
    private void exibirEstabelecimentoFragment(Estabelecimento estabelecimento) {
        EstabelecimentoDetalheFragment fragment = EstabelecimentoDetalheFragment.novaInstancia(estabelecimento);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.detalhe, fragment, EstabelecimentoDetalheFragment.TAG_DETALHE);
        ft.commit();
    }
    @Override
    public void aoEditarestabelecimento(Estabelecimento estabelecimento) {
        EstabelecimentoDialogFragment editNameDialog = EstabelecimentoDialogFragment.newInstance(estabelecimento);
        editNameDialog.abrir(getSupportFragmentManager());
    }
    @Override
    public void salvouEstabelecimento(Estabelecimento estabelecimento) {
        EstabelecimentoRepositorio repo = new EstabelecimentoRepositorio(this);
        repo.salvar(estabelecimento);
        exibirEstabelecimentoFragment(estabelecimento);
        setResult(RESULT_OK);
    }


}
