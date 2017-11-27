package br.com.drinksapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.util.Constantes;
import br.com.drinksapp.util.Validator;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button mBtnLogin;
    private Button mBtnLinkToRegister;
    private EditText mEdtEmail;
    private EditText mEdtSenha;
    private ProgressDialog pDialog;
    Usuarios usuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdtEmail = (EditText) findViewById(R.id.edt_email);
        mEdtSenha = (EditText) findViewById(R.id.edt_senha);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mBtnLogin.setOnClickListener(new BotaoLogin());
        // Link to Register Screen
        mBtnLinkToRegister.setOnClickListener(new LinkCadastrar());

    }

    class LinkCadastrar implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(it, Constantes.CADASTRAR_USUARIO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.CADASTRAR_USUARIO) {
            if (resultCode == RESULT_OK) {
                Usuarios usuario = (Usuarios) data.getSerializableExtra(Constantes.USUARIO_CADASTRADO);
                mEdtEmail.setText(usuario.getEmail());
            }
        }

    }

    private void initAsyncTask(Usuarios usuario){
        TaskLogin task = new TaskLogin();
        task.execute(usuario);
    }

    private class TaskLogin extends AsyncTask<Usuarios, Void, Usuarios> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Carregando ... Aguarde!");
            showDialog();
        }

        @Override
        protected Usuarios doInBackground(Usuarios... params) {

            Usuarios usuario = DBConnectParser.login(params[0]);
            return usuario;
        }

        @Override
        protected void onPostExecute(Usuarios usuario) {
            if(usuario != null){

                Toast.makeText(LoginActivity.this, "Bem vindo, " + usuario.getNome(), Toast.LENGTH_LONG).show();

                MySaveSharedPreference.setUserId(LoginActivity.this, usuario.getCodUsuario());
                MySaveSharedPreference.setUserName(LoginActivity.this, usuario.getNome());

//                if(!mDAO.existeUsuario(usuario)) {
//                    mDAO.insert(usuario);
//                }

                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
                hideDialog();

            }else{
                Toast.makeText(LoginActivity.this, "Email ou senha inválidos!", Toast.LENGTH_LONG).show();

                mEdtEmail.setError("Email inválido");
                mEdtEmail.setFocusable(true);
                mEdtEmail.requestFocus();

                mEdtSenha.setError("Senha inválida");
                mEdtSenha.setFocusable(true);
                mEdtSenha.requestFocus();
            }
        }
    }

    class BotaoLogin implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Validator.validateNotNull(mEdtSenha, "Preencha o campo Senha");

            boolean email_valido = Validator.validateEmail(mEdtEmail.getText().toString());

            if (!email_valido) {
                mEdtEmail.setError("Email inválido");
                mEdtEmail.setFocusable(true);
                mEdtEmail.requestFocus();
            }

            String email = mEdtEmail.getText().toString();
            String senha = mEdtSenha.getText().toString();

            usuario = new Usuarios();
            usuario.setEmail(email);
            usuario.setSenha(senha);

            initAsyncTask(usuario);

        }

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}