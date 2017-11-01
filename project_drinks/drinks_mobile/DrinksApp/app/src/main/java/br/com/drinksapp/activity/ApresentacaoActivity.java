package br.com.drinksapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;

public class ApresentacaoActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);
        MySaveSharedPreference.clearSharedPreference(this);

        new Handler().postDelayed(new Runnable() {
            final long[] codUsuario = {0};

            @Override
            public void run() {
                codUsuario[0] = MySaveSharedPreference.getUserId(ApresentacaoActivity.this);
                if (codUsuario[0] != 0) {
                    Intent i = new Intent(ApresentacaoActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(ApresentacaoActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
