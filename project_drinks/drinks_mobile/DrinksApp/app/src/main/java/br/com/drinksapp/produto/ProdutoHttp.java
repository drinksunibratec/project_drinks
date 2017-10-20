package br.com.drinksapp.produto;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProdutoHttp {
    //public static final String SERVIDOR = "http://www.comunidademaanaim.org.br/drinks_service";
    //private static final String WEBSERVICE_URL = SERVIDOR +"/webserviceproduto.php";

    public static final String SERVIDOR = "http://192.168.0.103/drinks_service";
    private static final String WEBSERVICE_URL = SERVIDOR +"/webserviceproduto.php";

    private Context mContext;
    private ProdutoRepositorio mRepositorio;
    public ProdutoHttp(Context ctx) {
        mContext = ctx;
        mRepositorio = new ProdutoRepositorio(mContext);
    }
    public void sincronizar() throws Exception {
        enviarDadosPendentes();
        List<Produto> produtos = getProdutos();
        ContentResolver cr = mContext.getContentResolver();
        for (Produto produto : produtos) {
            produto.status = Produto.Status.OK;
            mRepositorio.inserirLocal(produto, cr);
        }
    }
    private void enviarDadosPendentes() throws Exception{
        Cursor cursor = mContext.getContentResolver().query(
                ProdutoProvider.CONTENT_URI, null,
                ProdutoSQLHelper.COLUNA_STATUS +" != "+
                        Produto.Status.OK.ordinal(), null,
                ProdutoSQLHelper.COLUNA_ID_SERVIDOR +" DESC");
        while (cursor.moveToNext()) {
            Produto produto = ProdutoRepositorio.produtoFromCursor(cursor);
            if (produto.status == Produto.Status.INSERIR) {
                inserir(produto);
            } else if (produto.status == Produto.Status.EXCLUIR) {
                excluir(produto);
            } else if (produto.status == Produto.Status.ATUALIZAR) {
                if (produto.idServidor == 0) {
                    inserir(produto);
                } else {
                    atualizar(produto);
                }
            }
        }
    }
    private void inserir(Produto produto) {
        try {
            if (enviarProduto("POST", produto)) {
                produto.status = Produto.Status.OK;
                mRepositorio.atualizarLocal(produto, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void atualizar(Produto produto) {
        try {
            if (enviarProduto("PUT", produto)) {
                produto.status = Produto.Status.OK;
                mRepositorio.atualizarLocal(produto, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void excluir(Produto produto) {
        boolean podeExcluir = true;
        if (produto.idServidor != 0) {
            try {
                podeExcluir = enviarProduto("DELETE", produto);
            } catch (Exception e) {
                podeExcluir = false;
                e.printStackTrace();
            }
        }
        if (podeExcluir) {
            mRepositorio.excluirLocal(produto, mContext.getContentResolver());
        }
    }
    private boolean enviarProduto(String metodoHttp, Produto produto) throws Exception {
        boolean sucesso = false;
        boolean doOutput = !"DELETE".equals(metodoHttp);
        String url = WEBSERVICE_URL;
        if (!doOutput) {
            url += "/"+ produto.idServidor;
        }
        HttpURLConnection conexao = abrirConexao(url, metodoHttp, doOutput);
        if (doOutput) {
            OutputStream os = conexao.getOutputStream();
            os.write(produtoToJsonBytes(produto));
            os.flush();
            os.close();
        }
        int responseCode = conexao.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();
            JSONObject json = new JSONObject(s);
            produto.idServidor = json.getInt("id");
            sucesso = true;
        } else {
            sucesso = false;
        }
        conexao.disconnect();
        return sucesso;
    }
    private HttpURLConnection abrirConexao(String url,
                                           String metodo, boolean doOutput) throws Exception{
        URL urlCon = new URL(url);
        HttpURLConnection conexao = (HttpURLConnection) urlCon.openConnection();
        conexao.setReadTimeout(15000);
        conexao.setConnectTimeout(15000);
        conexao.setRequestMethod(metodo);
        conexao.setDoInput(true);
        conexao.setDoOutput(doOutput);
        if (doOutput) {
            conexao.addRequestProperty("Content-Type", "application/json");
        }
        conexao.connect();
        return conexao;
    }
    private List<Produto> getProdutos() throws Exception {
        HttpURLConnection conexao = abrirConexao(WEBSERVICE_URL, "GET", false);
        List<Produto> list = new ArrayList<Produto>();
        if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String jsonString = streamToString(conexao.getInputStream());
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject produtoJSON = json.getJSONObject(i);
                Produto p = new Produto(
                        0,
                        produtoJSON.getString("nome"),
                        produtoJSON.getString("endereco"),
                        produtoJSON.getString("estabelecimento"),
                        produtoJSON.getString("bairro"),
                        produtoJSON.getString("preco"),
                        produtoJSON.getInt("id"),
                        Produto.Status.OK);
                list.add(p);
            }
        }
        return list;
    }
    private String streamToString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }
    private byte[] produtoToJsonBytes(Produto produto) {
        try {
            JSONObject jsonPessoa = new JSONObject();
            jsonPessoa.put("id", produto.idServidor);
            jsonPessoa.put("nome", produto.nome);
            jsonPessoa.put("endereco", produto.endereco);
            jsonPessoa.put("estabelecimento", produto.estabelecimento);
            jsonPessoa.put("bairro", produto.bairro);
            jsonPessoa.put("preco", produto.preco);
            String json = jsonPessoa.toString();
            return json.getBytes();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}