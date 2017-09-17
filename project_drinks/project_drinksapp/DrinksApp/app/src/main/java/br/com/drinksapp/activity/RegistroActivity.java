package br.com.drinksapp.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.drinksapp.R;
import br.com.drinksapp.app.AppConfig;
import br.com.drinksapp.app.AppController;
import br.com.drinksapp.basicas.Cliente;
import br.com.drinksapp.dbconect.DBDrinksConnect;
import br.com.drinksapp.helper.SQLiteHandler;
import br.com.drinksapp.helper.SessionManager;
import br.com.drinksapp.util.Validator;

public class RegistroActivity extends AppCompatActivity {
    private static final String TAG = RegistroActivity.class.getSimpleName();
    private Button mBntCadastrar;
    private Button btnLinkToLogin;
    private EditText mEdtNome;
    private EditText mEdtEmail;
    private EditText mEdtSenha;
    private EditText mEdtTelefone;
    private ProgressDialog mProgressDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mEdtNome = (EditText) findViewById(R.id.name);
        mEdtEmail = (EditText) findViewById(R.id.email);
        mEdtSenha = (EditText) findViewById(R.id.password);
        mEdtTelefone = (EditText) findViewById(R.id.telefone);
        mBntCadastrar = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegistroActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        mBntCadastrar.setOnClickListener(new BotaoRegistro());

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

    private class TaskCadastrarUsuario extends AsyncTask<Cliente, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            mProgressDialog.setMessage("Carregando... Aguarde!");
            showDialog();
        }

        @Override
        protected Boolean doInBackground(Cliente... params) {

            boolean inserido = DBDrinksConnect.getInstancia().insertCliente(params[0]);
            return inserido;
        }

        @Override
        protected void onPostExecute(Boolean clienteInserido) {

            if (clienteInserido) {

                Toast.makeText(RegistroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                finish();

            } else {

                mEdtEmail.setError("Email já cadastrado");
                mEdtEmail.setFocusable(true);
                mEdtEmail.requestFocus();
                Toast.makeText(RegistroActivity.this, "Usuário com esse e-mail já existe!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initAsyncTask(Cliente cliente) {
        TaskCadastrarUsuario taskCadastrarUsuario = new TaskCadastrarUsuario();
        taskCadastrarUsuario.execute(cliente);
    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     */
    private void registerUser(final String nome, final String email, final String senha, final String telefone) {
        // Tag used to cancel the request

        String tag_string_req = "req_register";

        mProgressDialog.setMessage("Salvando... Aguarde!");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {


                        JSONObject usuario = jObj.getJSONObject("usuario");
                        String nome = usuario.getString("nome");
                        String email = usuario.getString("email");
                        String telefone = usuario.getString("telefone");

                        db.addUser(nome, email, telefone);

                        Toast.makeText(getApplicationContext(), "Usuario Salvo Com Sucesso. Entre Agora!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(
                                RegistroActivity.this,
                                LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nome", nome);
                params.put("email", email);
                params.put("senha", senha);
                params.put("telefone", telefone);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    private void hideDialog() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    class BotaoRegistro implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String nome = mEdtNome.getText().toString().trim();
            String email = mEdtEmail.getText().toString().trim();
            String senha = mEdtSenha.getText().toString().trim();
            String telefone = mEdtTelefone.getText().toString().trim();

            Validator.validateNotNull(mEdtNome, "Preencha o campo nome");
            Validator.validateNotNull(mEdtSenha, "Preencha o campo Senha");
            Validator.validateNotNull(mEdtTelefone, "Preencha o campo Telefone");

            boolean email_valido = Validator.validateEmail(mEdtEmail.getText().toString());

            if (!email_valido) {
                mEdtEmail.setError("Preencha o campo email");
                mEdtEmail.setFocusable(true);
                mEdtEmail.requestFocus();
            }

            if (!email_valido) {
                return;
            }

            registerUser(nome, email, senha, telefone);
//                    Cliente cliente = new Cliente(nome, email, senha, telefone);
//                    initAsyncTask(cliente);
        }
    }
}

