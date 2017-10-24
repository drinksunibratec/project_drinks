package br.com.drinksapp.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import br.com.drinksapp.app.AppConfig;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.ListEstabelecimento;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Silvio Cedrim on 23/10/2017.
 */

public class DBConnectParser {

    public static List<Estabelecimento> listEstabelecimento() throws IOException {
        Response response = null;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(AppConfig.URL_LISTA_ESTABELECIMENTOS)
                .build();

        response = client.newCall(request).execute();
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            Gson gson = new Gson();

            ListEstabelecimento result = gson.fromJson(json, ListEstabelecimento.class);

            if (result != null) {
                return result.getEstabelecimentos();
            }
        }

        return null;

    }
}