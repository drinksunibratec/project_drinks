package br.com.drinksapp.http;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.util.List;

import br.com.drinksapp.bean.Estabelecimento;

/**
 * Created by Silvio Cedrim on 23/10/2017.
 */

public class EstabelecimentoTask extends AsyncTaskLoader<List<Estabelecimento>> {

    List<Estabelecimento> mEstabelecimentos;

    public EstabelecimentoTask(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if(mEstabelecimentos == null){
            forceLoad();
        }else{
            deliverResult(mEstabelecimentos);
        }
    }
    @Override
    public List<Estabelecimento> loadInBackground() {
        try {
            mEstabelecimentos = DBConnectParser.listEstabelecimento();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mEstabelecimentos;
    }
}
