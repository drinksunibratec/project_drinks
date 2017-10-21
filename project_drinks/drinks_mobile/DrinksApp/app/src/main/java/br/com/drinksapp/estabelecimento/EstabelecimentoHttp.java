package br.com.drinksapp.estabelecimento;


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

public class EstabelecimentoHttp {
    //public static final String SERVIDOR = "http://www.comunidademaanaim.org.br/drinks_service";
    //private static final String WEBSERVICE_URL = SERVIDOR +"/webserviceproduto.php";

    public static final String SERVIDOR = "http://192.168.0.103/drinks_service";
    private static final String WEBSERVICE_URL = SERVIDOR +"/webservicepedido.php";

    private Context mContext;
    private EstabelecimentoRepositorio mRepositorio;
    public EstabelecimentoHttp(Context ctx) {
        mContext = ctx;
        mRepositorio = new EstabelecimentoRepositorio(mContext);
    }
    public void sincronizar() throws Exception {
        enviarDadosPendentes();
        List<Estabelecimento> estabelecimentos = getEstabelecimentos();
        ContentResolver cr = mContext.getContentResolver();
        for (Estabelecimento estabelecimento : estabelecimentos) {
            estabelecimento.status = Estabelecimento.Status.OK;
            mRepositorio.inserirLocal(estabelecimento, cr);
        }
    }
    private void enviarDadosPendentes() throws Exception{
        Cursor cursor = mContext.getContentResolver().query(
                EstabelecimentoProvider.CONTENT_URI, null,
                EstabelecimentoSQLHelper.COLUNA_STATUS +" != "+
                        Estabelecimento.Status.OK.ordinal(), null,
                EstabelecimentoSQLHelper.COLUNA_ID_SERVIDOR +" DESC");
        while (cursor.moveToNext()) {
            Estabelecimento estabelecimento = EstabelecimentoRepositorio.estabelecimentoFromCursor(cursor);
            if (estabelecimento.status == Estabelecimento.Status.INSERIR) {
                inserir(estabelecimento);
            } else if (estabelecimento.status == Estabelecimento.Status.EXCLUIR) {
                excluir(estabelecimento);
            } else if (estabelecimento.status == Estabelecimento.Status.ATUALIZAR) {
                if (estabelecimento.idServidor == 0) {
                    inserir(estabelecimento);
                } else {
                    atualizar(estabelecimento);
                }
            }
        }
    }
    private void inserir(Estabelecimento estabelecimento) {
        try {
            if (enviarEstabelecimento("POST", estabelecimento)) {
                estabelecimento.status = Estabelecimento.Status.OK;
                mRepositorio.atualizarLocal(estabelecimento, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void atualizar(Estabelecimento estabelecimento) {
        try {
            if (enviarEstabelecimento("PUT", estabelecimento)) {
                estabelecimento.status = Estabelecimento.Status.OK;
                mRepositorio.atualizarLocal(estabelecimento, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void excluir(Estabelecimento estabelecimento) {
        boolean podeExcluir = true;
        if (estabelecimento.idServidor != 0) {
            try {
                podeExcluir = enviarEstabelecimento("DELETE", estabelecimento);
            } catch (Exception e) {
                podeExcluir = false;
                e.printStackTrace();
            }
        }
        if (podeExcluir) {
            mRepositorio.excluirLocal(estabelecimento, mContext.getContentResolver());
        }
    }
    private boolean enviarEstabelecimento(String metodoHttp, Estabelecimento estabelecimento) throws Exception {
        boolean sucesso = false;
        boolean doOutput = !"DELETE".equals(metodoHttp);
        String url = WEBSERVICE_URL;
        if (!doOutput) {
            url += "/"+ estabelecimento.idServidor;
        }
        HttpURLConnection conexao = abrirConexao(url, metodoHttp, doOutput);
        if (doOutput) {
            OutputStream os = conexao.getOutputStream();
            os.write(estabelecimentoToJsonBytes(estabelecimento));
            os.flush();
            os.close();
        }
        int responseCode = conexao.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();
            JSONObject json = new JSONObject(s);
            estabelecimento.idServidor = json.getInt("id");
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
    private List<Estabelecimento> getEstabelecimentos() throws Exception {
        HttpURLConnection conexao = abrirConexao(WEBSERVICE_URL, "GET", false);
        List<Estabelecimento> list = new ArrayList<Estabelecimento>();
        if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String jsonString = streamToString(conexao.getInputStream());
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject estabelecimentoJSON = json.getJSONObject(i);
                Estabelecimento p = new Estabelecimento(
                        0,
                        estabelecimentoJSON.getString("nomeFantasia"),
                        estabelecimentoJSON.getString("rua"),
                        estabelecimentoJSON.getString("numero"),
                        estabelecimentoJSON.getString("bairro"),
                        estabelecimentoJSON.getString("cidade"),
                        estabelecimentoJSON.getString("uf"),
                        estabelecimentoJSON.getString("cep"),
                        estabelecimentoJSON.getString("latitude"),
                        estabelecimentoJSON.getString("longetude"),
                        estabelecimentoJSON.getInt("id"),
                        Estabelecimento.Status.OK);
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
    private byte[] estabelecimentoToJsonBytes(Estabelecimento estabelecimento) {
        try {
            JSONObject jsonPessoa = new JSONObject();
            jsonPessoa.put("id", estabelecimento.idServidor);
            jsonPessoa.put("nomeFantasia", estabelecimento.nomeFantasia);
            jsonPessoa.put("rua", estabelecimento.rua);
            jsonPessoa.put("numero", estabelecimento.numero);
            jsonPessoa.put("bairro", estabelecimento.bairro);
            jsonPessoa.put("cidade", estabelecimento.cidade);
            jsonPessoa.put("uf", estabelecimento.uf);
            jsonPessoa.put("cep", estabelecimento.cep);
            jsonPessoa.put("latitude", estabelecimento.latitude);
            jsonPessoa.put("longetude", estabelecimento.longetude);
            String json = jsonPessoa.toString();
            return json.getBytes();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
