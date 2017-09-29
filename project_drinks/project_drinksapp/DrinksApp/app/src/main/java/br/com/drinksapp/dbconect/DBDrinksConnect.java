package br.com.drinksapp.dbconect;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import br.com.drinksapp.app.AppConfig;
import br.com.drinksapp.basicas.Cliente;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Silvinho Cedrim on 07/08/2017.
 */

public class DBDrinksConnect {

    private final static MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static DBDrinksConnect instancia = null;

    public static DBDrinksConnect getInstancia(){
        if(instancia == null){
            instancia = new DBDrinksConnect();
        }
        return instancia;
    }

//    public boolean insertCliente(Cliente cliente) throws Exception{
//        Response response = null;
//        Request request = null;
//
//        Gson gson = new Gson();
//        String json = gson.toJson(cliente);
//
//        try {
//
//            RequestBody body = RequestBody.create(JSON, json);
//
//
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .readTimeout(50,TimeUnit.SECONDS)
//                    .writeTimeout(50, TimeUnit.SECONDS)
//                    .build();
//
//            request = new Request.Builder()
//                    .url(AppConfig.URL_DB_MANAGER + AppConfig.URL_INSERT_CLIENTE)
//                    .post(body)
//                    .addHeader("content-type", "application/json; charset=utf-8")
//                    .addHeader("Accept", "application/json")
//                    .build();
//
//            response = client.newCall(request).execute();
//
//            if(response.networkResponse().code() == HttpURLConnection.HTTP_OK){
//                return true;
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return false;
//
//    }

    public boolean enviarCliente(String metodoHttp, Cliente cliente)throws Exception{

        Gson gson = new Gson();
        String json = gson.toJson(cliente);



        boolean sucesso = false;
        boolean doOutput = !"DELETE".equals(metodoHttp);

        String url = AppConfig.URL_DB_MANAGER + AppConfig.URL_INSERT_CLIENTE;
        HttpURLConnection connection = abrirConexao(url, metodoHttp, doOutput);

        if(doOutput){
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();
        }
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream is = connection.getInputStream();
            String s = streamToString(is);
            is.close();

            sucesso = true;
        }
        connection.disconnect();
        return sucesso;

    }


    private HttpURLConnection abrirConexao(String url, String metodo, boolean doOutput) throws Exception{

        URL urlCon = new URL(url);
        HttpURLConnection conexao = (HttpURLConnection)urlCon.openConnection();
        conexao.setReadTimeout(50000);
        conexao.setConnectTimeout(50000);
        conexao.setRequestMethod(metodo);
        conexao.setDoOutput(doOutput);

        if(doOutput){
            conexao.addRequestProperty("Content-Type", "application/json; charset=utf-8");
        }
        conexao.connect();
        return conexao;

    }

    private String streamToString(InputStream is) throws Exception{
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;

        while ((lidos = is.read(bytes))> 0){
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }
}
