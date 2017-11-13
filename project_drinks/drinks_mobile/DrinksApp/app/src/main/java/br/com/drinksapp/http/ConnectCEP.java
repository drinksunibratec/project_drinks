package br.com.drinksapp.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import br.com.drinksapp.bean.Endereco;
import br.com.drinksapp.util.AppConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Silvio Cedrim on 12/11/2017.
 */

public class ConnectCEP {

    public static Endereco buscarEndereco(String cep) throws IOException {

        Response response = null;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(AppConfig.URL_CORREIOS + cep + AppConfig.COMPLEMENTO_URL_CORREIOS)
                .build();

        response = client.newCall(request).execute();
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            Gson gson = new Gson();

            Endereco result = gson.fromJson(json, Endereco.class);

            if (result != null) {
                return result;
            }
        }

        return null;

    }
}
