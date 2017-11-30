package br.com.drinksapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.tasks.Task;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.util.Constantes;
import br.com.drinksapp.util.GooglePlusLogin;
import br.com.drinksapp.util.Validator;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button mBtnLogin;
    private Button mBtnLinkToRegister;
    private EditText mEdtEmail;
    private EditText mEdtSenha;
    private ProgressDialog pDialog;
    Usuarios usuario;
    private GoogleSignInClient mGoogleApiClient;


    SignInButton mBtnGPlus;
    private DAODrinks mDAO;

    GooglePlusLogin mGoogle;


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

        mBtnGPlus = (SignInButton) findViewById(R.id.btn_google);
        mBtnGPlus.setOnClickListener(new LoginGPlus());

        mDAO = new DAODrinks(this);

        mGoogle = GooglePlusLogin.getInstancia(this);
//        configGooglePlus();


    }

//    private void configGooglePlus(){
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient =  GoogleSignIn.getClient(this, gso);
//
//    }

    private void signIn() {
        Intent signInIntent = mGoogle.mGoogleApiClient.getSignInIntent();
        startActivityForResult(signInIntent, Constantes.RC_SIGN_IN);
    }

    class LoginGPlus implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            signIn();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void disconnectGoogle() {
        mGoogleApiClient.signOut();

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
        } else if (requestCode == Constantes.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
                initTaskCadastroGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }

    void initTaskCadastroGoogle(GoogleSignInAccount account) {
        Usuarios usuario = new Usuarios();
        usuario.setEmail(account.getEmail());
        usuario.setNome(account.getDisplayName());
        usuario.setTelefone("99999999999");
        usuario.setSenha("");
        new TaskCadastrarUsuarioGoogle().execute(usuario);
    }


    private class TaskCadastrarUsuarioGoogle extends AsyncTask<Usuarios, Void, Usuarios> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Aguarde!");
            showDialog();
        }

        @Override
        protected Usuarios doInBackground(Usuarios... params) {

            Usuarios usuario = DBConnectParser.cadastrar(params[0]);

            if (usuario.getErro() == "true" && (usuario.getTipo_erro() != 0 || usuario.getTipo_erro() == 1)) {
                usuario = DBConnectParser.login(params[0]);
            }

            return usuario;
        }

        @Override
        protected void onPostExecute(Usuarios usuario) {

            mDAO.insertUsuario(usuario);

            Toast.makeText(LoginActivity.this, "Bem vindo, " + usuario.getNome() + "!", Toast.LENGTH_LONG).show();
            MySaveSharedPreference.setUserId(LoginActivity.this, usuario.getCodUsuario());
            MySaveSharedPreference.setUserName(LoginActivity.this, usuario.getNome());


            Intent it = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(it);
            hideDialog();

        }
    }

    @Override
    protected void onResume() {

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        super.onResume();
    }

    private void initAsyncTask(Usuarios usuario) {
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
            if (usuario != null) {

                Toast.makeText(LoginActivity.this, "Bem vindo, " + usuario.getNome(), Toast.LENGTH_LONG).show();

                MySaveSharedPreference.setUserId(LoginActivity.this, usuario.getCodUsuario());
                MySaveSharedPreference.setUserName(LoginActivity.this, usuario.getNome());


                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
                hideDialog();

            } else {
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