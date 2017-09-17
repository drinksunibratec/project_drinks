package br.com.drinksapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
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
import br.com.drinksapp.helper.SQLiteHandler;
import br.com.drinksapp.helper.SessionManager;
import br.com.drinksapp.util.Validator;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = RegistroActivity.class.getSimpleName();
    private Button mBtnLogin;
    private Button mBtnLinkToRegister;
    private EditText mEdtEmail;
    private EditText mEdtSenha;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdtEmail = (EditText) findViewById(R.id.email);
        mEdtSenha = (EditText) findViewById(R.id.password);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        mBtnLogin.setOnClickListener(new BotaoLogin());

        mBtnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegistroActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    /**
     * function to verify login details in mysql db
     */
    private void checkLogin(final String email, final String senha) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Carregando... Aguarde!");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        session.setLogin(true);

                        JSONObject usuario = jObj.getJSONObject("usuario");
                        String nome = usuario.getString("nome");
                        String email = usuario.getString("email");
                        String telefone = usuario.getString("telefone");

                        db.addUser(nome, email, telefone);
                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("senha", senha);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    class BotaoLogin implements View.OnClickListener {


        @Override
        public void onClick(View view) {
            String email = mEdtEmail.getText().toString().trim();
            String senha = mEdtSenha.getText().toString().trim();

            Validator.validateNotNull(mEdtSenha, "Preencha o campo Senha");

            boolean email_valido = Validator.validateEmail(mEdtEmail.getText().toString());

            if (!email_valido) {
                mEdtEmail.setError("Email inválido");
                mEdtEmail.setFocusable(true);
                mEdtEmail.requestFocus();
            }

            checkLogin(email, senha);
        }
    }
}
