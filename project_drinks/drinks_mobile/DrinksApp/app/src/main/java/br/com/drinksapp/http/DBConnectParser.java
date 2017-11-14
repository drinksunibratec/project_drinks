package br.com.drinksapp.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import br.com.drinksapp.bean.ListPedido;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.util.AppConfig;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.ListEstabelecimento;
import br.com.drinksapp.bean.ListProduto;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.bean.Usuarios;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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


    public static List<Produto> listProdutosPorEstabelecimento(Estabelecimento estabelecimento) throws IOException {
        Response response = null;

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("codEstabelecimento", String.valueOf(estabelecimento.getCodEstabelecimento()))
                .build();

        Request request = new Request.Builder()
                .url(AppConfig.URL_LISTA_PRODUTOS_POR_ESTABELECIMENTO)
                .post(body)
                .build();

        response = client.newCall(request).execute();
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            Gson gson = new Gson();

            ListProduto produtos = gson.fromJson(json, ListProduto.class);

            if (produtos != null) {
                return produtos.getProdutos();
            }
        }

        return null;

    }

    public static Usuarios login(Usuarios usuario) {
        Response response = null;

        Usuarios retorno = null;


        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("email", usuario.getEmail())
                    .add("senha", usuario.getSenha())
                    .build();


            Request request = new Request.Builder()
                    .url(AppConfig.URL_LOGIN)
                    .post(body)
                    .build();

            response = client.newCall(request).execute();

            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                String json = response.body().string();

                Gson gson = new Gson();

                retorno = gson.fromJson(json, Usuarios.class);


            }
        } catch (IOException e) {

        }
        return retorno;

    }

    public static Usuarios cadastrar(Usuarios usuario) {
        Response response = null;

        Usuarios retorno = null;


        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("nome", usuario.getNome())
                    .add("email", usuario.getEmail())
                    .add("senha", usuario.getSenha())
                    .add("telefone", usuario.getTelefone())
                    .build();


            Request request = new Request.Builder()
                    .url(AppConfig.URL_REGISTER)
                    .post(body)
                    .build();

            response = client.newCall(request).execute();

            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                String json = response.body().string();

                Gson gson = new Gson();

                retorno = gson.fromJson(json, Usuarios.class);

            }
        } catch (IOException e) {

        }
        return retorno;

    }

    public static Pedido inserirPedido(Pedido pedido) {
        Response response = null;
        Gson gson = new Gson();

        String json = gson.toJson(pedido);

        Pedido retorno = null;

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("json_pedido", json)
                    .build();


            Request request = new Request.Builder()
                    .url(AppConfig.URL_INSERIR_PEDIDO)
                    .post(body)
                    .build();

            response = client.newCall(request).execute();

            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                String resposta = response.body().string();


                retorno = gson.fromJson(resposta, Pedido.class);

            }

        } catch (IOException e) {

        }

        return retorno;
    }

    public static List<Pedido> listarPedidosdeCliente(Usuarios usuario) {
        Response response = null;
        Gson gson = new Gson();

        List<Pedido> retorno = null;

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("codUsuario", String.valueOf(usuario.getCodUsuario()))
                    .build();


            Request request = new Request.Builder()
                    .url(AppConfig.URL_BUCAR_PEDIDOS_CLIENTE)
                    .post(body)
                    .build();

            response = client.newCall(request).execute();

            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                String resposta = response.body().string();


                ListPedido listPedido = gson.fromJson(resposta, ListPedido.class);

                if (listPedido != null) {
                    List<Pedido> pedidos = listPedido.getPedidos();
                    for(int i = 0; i < pedidos.size(); i++){
                        long codEstabelecimento = pedidos.get(i).getCodEstabelecimento();
                        Estabelecimento estabelecimento = consultarEstabelecimento(String.valueOf(codEstabelecimento));
                        pedidos.get(i).setEstabelecimento(estabelecimento);
                    }
                    return pedidos;
                }

            }

        } catch (IOException e) {

        }

        return retorno;
    }

    public static Estabelecimento consultarEstabelecimento(String codEstabelecimento) {
        Response response = null;
        Gson gson = new Gson();

        List<Pedido> retorno = null;

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("codEstabelecimento", codEstabelecimento)
                    .build();


            Request request = new Request.Builder()
                    .url(AppConfig.URL_CONSULTA_ESTABELECIMENTO)
                    .post(body)
                    .build();

            response = client.newCall(request).execute();

            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                String resposta = response.body().string();


                Estabelecimento estabelecimento = gson.fromJson(resposta, Estabelecimento.class);

                return estabelecimento;

            }

        } catch (IOException e) {

        }

        return null;
    }

    public static boolean inserirProdutosPedido(List<PedidoProdutos> pedidoProdutos) {
        Response response = null;
        Gson gson = new Gson();

        String json = gson.toJson(pedidoProdutos);

        boolean retorno = false;

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("json_produtos_pedido", json)
                    .build();


            Request request = new Request.Builder()
                    .url(AppConfig.URL_INSERIR_PRODUTOS_NO_PEDIDO)
                    .post(body)
                    .build();

            response = client.newCall(request).execute();

            if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
                retorno = true;

            }

        } catch (IOException e) {

        }

        return retorno;
    }
}