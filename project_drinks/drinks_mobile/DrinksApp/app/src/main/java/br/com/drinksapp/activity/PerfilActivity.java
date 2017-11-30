package br.com.drinksapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.db.DAODrinks;

public class PerfilActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtEmail;
    private TextView txtSenha;
    private TextView txtTelefone;

    DAODrinks mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mDao = new DAODrinks(this);
        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        txtSenha = (TextView) findViewById(R.id.senha);
        txtTelefone = (TextView) findViewById(R.id.telefone);



        Usuarios usuarios = new Usuarios(MySaveSharedPreference.getUserId(this));
        usuarios = mDao.consultarUsuarioId(usuarios);
        txtName.setText("Nome: " + usuarios.getNome());
//        txtEmail.setText()


//        HashMap<String, String> usuario = db.getUserDetails();
//        String nome = usuario.get("nome");
//        String email = usuario.get("email");
//        String senha = usuario.get("senha");
//        String telefone = usuario.get("telefone");
//        txtName.setText(nome);
//        txtEmail.setText(email);
//        txtSenha.setText(senha);
//        txtTelefone.setText(telefone);
    }



}
