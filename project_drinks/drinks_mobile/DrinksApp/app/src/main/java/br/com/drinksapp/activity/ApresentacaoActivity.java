package br.com.drinksapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import br.com.drinksapp.R;

public class ApresentacaoActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(ApresentacaoActivity.this, LoginActivity.class);
               startActivity(i);
               finish();
          }
        }, SPLASH_TIME_OUT);
    }
}
