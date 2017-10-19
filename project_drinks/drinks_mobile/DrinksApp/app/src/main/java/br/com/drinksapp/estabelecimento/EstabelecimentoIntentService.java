package br.com.drinksapp.estabelecimento;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import br.com.drinksapp.produto.ProdutoHttp;

public class EstabelecimentoIntentService extends IntentService {
    public static final String ACAO_SINCRONIZAR = "sincronizar";
    public static final String EXTRA_SUCESSO   = "sucesso";
    public EstabelecimentoIntentService() {
        super("EstabelecimentoIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            EstabelecimentoHttp estabelecimentoHttp = new EstabelecimentoHttp(this);
            Intent it = new Intent(ACAO_SINCRONIZAR);
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
            try {
                estabelecimentoHttp.sincronizar();
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