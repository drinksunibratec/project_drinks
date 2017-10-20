package br.com.drinksapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.HashMap;
import br.com.drinksapp.R;
import br.com.drinksapp.helper.SQLiteHandler;
import br.com.drinksapp.helper.SessionManager;

public class PerfilActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtEmail;
    private TextView txtSenha;
    private TextView txtTelefone;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        txtSenha = (TextView) findViewById(R.id.senha);
        txtTelefone = (TextView) findViewById(R.id.telefone);
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        HashMap<String, String> usuario = db.getUserDetails();
        String nome = usuario.get("nome");
        String email = usuario.get("email");
        String senha = usuario.get("senha");
        String telefone = usuario.get("telefone");
        txtName.setText(nome);
        txtEmail.setText(email);
        txtSenha.setText(senha);
        txtTelefone.setText(telefone);
    }

}
