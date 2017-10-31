package br.com.drinksapp.activity;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.helper.Mask;
import br.com.drinksapp.helper.SQLiteHandler;
import br.com.drinksapp.helper.SessionManager;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.util.Constantes;
import br.com.drinksapp.util.Validator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button mBtnRegister;
    private EditText mEdtNome;
    private EditText mEdtEmail;
    private EditText mEdtSenha;
    private EditText mEdtTelefone;
    private ProgressDialog pDialog;
    private Usuarios usuario;
    private Toolbar mToolbar;
    private DAODrinks mDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEdtNome = (EditText) findViewById(R.id.edt_nome_cadastro);
        mEdtEmail = (EditText) findViewById(R.id.edt_email_cadastro);

        mEdtSenha = (EditText) findViewById(R.id.edt_senha_cadastro);
        mEdtSenha.addTextChangedListener(Mask.insert("######", mEdtSenha));

        mEdtTelefone = (EditText) findViewById(R.id.edt_telefone_cadastro);
        mEdtTelefone.addTextChangedListener(Mask.insert("(##)#####-####", mEdtTelefone));

        mBtnRegister = (Button) findViewById(R.id.btnRegister);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        mToolbar = (Toolbar)findViewById(R.id.toolbar_cadastro);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnRegister.setOnClickListener(new BotaoCadastrar());

        mDAO = new DAODrinks(this);

    }

    private class TaskCadastrarUsuario extends AsyncTask<Usuarios, Void, Usuarios> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Salvando ... Aguarde!");
            showDialog();
        }

        @Override
        protected Usuarios doInBackground(Usuarios... params) {

            Usuarios usuario = DBConnectParser.cadastrar(params[0]);
            return usuario;
        }

        @Override
        protected void onPostExecute(Usuarios usuario) {

            if(usuario.getErro() == "false"){


                mDAO.insertUsuario(usuario);

                Toast.makeText(RegisterActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                getIntent().putExtra(Constantes.USUARIO_CADASTRADO, usuario);
                setResult(RESULT_OK, getIntent());
                finish();
            }else{

                mEdtEmail.setError("Email já cadastrado");
                mEdtEmail.setFocusable(true);
                mEdtEmail.requestFocus();
                Toast.makeText(RegisterActivity.this, "Usuário com esse e-mail já existe!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initAsyncTask(Usuarios usuario){
        TaskCadastrarUsuario taskCadastrarUsuario = new TaskCadastrarUsuario();
        taskCadastrarUsuario.execute(usuario);
    }


    class BotaoCadastrar implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            boolean nome_valido = Validator.validateNotNull(mEdtNome,"Preencha o campo nome");
            boolean senha_valido =Validator.validateNotNull(mEdtSenha,"Preencha o campo Senha");
            boolean telefone_valido =Validator.validateNotNull(mEdtTelefone,"Preencha o campo Telefone");

            boolean email_valido = Validator.validateEmail(mEdtEmail.getText().toString());

            if(!email_valido){
                mEdtEmail.setError("Preencha o campo email");
                mEdtEmail.setFocusable(true);
                mEdtEmail.requestFocus();

            }

            if(!email_valido){
                return;
            }

            String nome = mEdtNome.getText().toString();
            String email = mEdtEmail.getText().toString();
            String senha = mEdtSenha.getText().toString();
            String telefone = mEdtTelefone.toString();

            usuario = new Usuarios(nome, email, senha, telefone);

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
