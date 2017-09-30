package br.com.drinksapp.produto;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class ProdutoIntentService extends IntentService {
    public static final String ACAO_SINCRONIZAR = "sincronizar";
    public static final String EXTRA_SUCESSO   = "sucesso";
    public ProdutoIntentService() {
        super("ProdutoIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ProdutoHttp produtoHttp = new ProdutoHttp(this);
            Intent it = new Intent(ACAO_SINCRONIZAR);
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
            try {
                produtoHttp.sincronizar();
                it.putExtra(EXTRA_SUCESSO, true);
            } catch (Exception e) {
                it.putExtra(EXTRA_SUCESSO, false);
                e.printStackTrace();
            } finally {
                lbm.sendBroadcast(it);
            }
        }
    }
}