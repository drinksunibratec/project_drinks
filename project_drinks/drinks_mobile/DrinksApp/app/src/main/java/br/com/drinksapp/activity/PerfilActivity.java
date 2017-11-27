package br.com.drinksapp.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.util.Mask;

public class PerfilActivity extends AppCompatActivity {

    private TextView mTxtNomePerfil;
    private TextView mTxtEmailPerfil;
    private TextView mTxtTelefonePerfil;

    private ProgressDialog pDialog;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mTxtNomePerfil = (TextView) findViewById(R.id.txtNomePerfil);
        mTxtEmailPerfil = (TextView) findViewById(R.id.txtEmailPerfil);
        mTxtTelefonePerfil = (TextView) findViewById(R.id.txtTelefonePerfil);

        pDialog = new ProgressDialog(this, R.style.MyDialogTheme);
        pDialog.setCancelable(false);

        mToolbar = (Toolbar)findViewById(R.id.toolbar_perfil);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        long codUsuario = MySaveSharedPreference.getUserId(this);
        Usuarios usuario = new Usuarios(codUsuario);
        initAsyncTask(usuario);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private class TaskBuscarUsuario extends AsyncTask<Usuarios, Void, Usuarios> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Salvando ... Aguarde!");
            showDialog();
        }

        @Override
        protected Usuarios doInBackground(Usuarios... params) {

            Usuarios usuario = DBConnectParser.getUsuario(params[0]);
            return usuario;
        }

        @Override
        protected void onPostExecute(Usuarios usuario) {

            if(usuario != null){
                mTxtNomePerfil.setText(getString(R.string.nome) + ": " + usuario.getNome());
                mTxtEmailPerfil.setText((getString(R.string.email) + ": " + usuario.getEmail()));
                mTxtTelefonePerfil.setText(getString(R.string.edt_telefone) + ": " + Mask.formartTelefone(usuario.getTelefone()));
            }

            hideDialog();
        }
    }

    private void initAsyncTask(Usuarios usuario){
        TaskBuscarUsuario taskCadastrarUsuario = new TaskBuscarUsuario();
        taskCadastrarUsuario.execute(usuario);
    }

}
