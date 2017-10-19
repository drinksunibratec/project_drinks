package br.com.drinksapp.pedido;

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

public class PedidoHttp {
    //public static final String SERVIDOR = "http://www.comunidademaanaim.org.br/drinks_service";
    //private static final String WEBSERVICE_URL = SERVIDOR +"/webserviceproduto.php";

    public static final String SERVIDOR = "http://192.168.0.103/drinks_service";
    private static final String WEBSERVICE_URL = SERVIDOR +"/webservicepedido.php";

    private Context mContext;
    private PedidoRepositorio mRepositorio;
    public PedidoHttp(Context ctx) {
        mContext = ctx;
        mRepositorio = new PedidoRepositorio(mContext);
    }
    public void sincronizar() throws Exception {
        enviarDadosPendentes();
        List<Pedido> pedidos = getPedidos();
        ContentResolver cr = mContext.getContentResolver();
        for (Pedido pedido : pedidos) {
            pedido.status = Pedido.Status.OK;
            mRepositorio.inserirLocal(pedido, cr);
        }
    }
    private void enviarDadosPendentes() throws Exception{
        Cursor cursor = mContext.getContentResolver().query(
                PedidoProvider.CONTENT_URI, null,
                PedidoSQLHelper.COLUNA_STATUS +" != "+
                        Pedido.Status.OK.ordinal(), null,
                PedidoSQLHelper.COLUNA_ID_SERVIDOR +" DESC");
        while (cursor.moveToNext()) {
            Pedido pedido = PedidoRepositorio.pedidoFromCursor(cursor);
            if (pedido.status == Pedido.Status.INSERIR) {
                inserir(pedido);
            } else if (pedido.status == Pedido.Status.EXCLUIR) {
                excluir(pedido);
            } else if (pedido.status == Pedido.Status.ATUALIZAR) {
                if (pedido.idServidor == 0) {
                    inserir(pedido);
                } else {
                    atualizar(pedido);
                }
            }
        }
    }
    private void inserir(Pedido pedido) {
        try {
            if (enviarPedido("POST", pedido)) {
                pedido.status = Pedido.Status.OK;
                mRepositorio.atualizarLocal(pedido, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void atualizar(Pedido pedido) {
        try {
            if (enviarPedido("PUT", pedido)) {
                pedido.status = Pedido.Status.OK;
                mRepositorio.atualizarLocal(pedido, mContext.getContentResolver());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void excluir(Pedido pedido) {
        boolean podeExcluir = true;
        if (pedido.idServidor != 0) {
            try {
                podeExcluir = enviarPedido("DELETE", pedido);
            } catch (Exception e) {
                podeExcluir = false;
                e.printStackTrace();
            }
        }
        if (podeExcluir) {
            mRepositorio.excluirLocal(pedido, mContext.getContentResolver());
        }
    }
    private boolean enviarPedido(String metodoHttp, Pedido pedido) throws Exception {
        boolean sucesso = false;
        boolean doOutput = !"DELETE".equals(metodoHttp);
        String url = WEBSERVICE_URL;
        if (!doOutput) {
            url += "/"+ pedido.idServidor;
        }
        HttpURLConnection conexao = abrirConexao(url, metodoHttp, doOutput);
        if (doOutput) {
            OutputStream os = conexao.getOutputStream();
            os.write(pedidoToJsonBytes(pedido));
            os.flush();
            os.close();
        }
        int responseCode = conexao.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream is = conexao.getInputStream();
            String s = streamToString(is);
            is.close();
            JSONObject json = new JSONObject(s);
            pedido.idServidor = json.getInt("id");
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
    private List<Pedido> getPedidos() throws Exception {
        HttpURLConnection conexao = abrirConexao(WEBSERVICE_URL, "GET", false);
        List<Pedido> list = new ArrayList<Pedido>();
        if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String jsonString = streamToString(conexao.getInputStream());
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject pedidoJSON = json.getJSONObject(i);
                Pedido pe = new Pedido(
                        0,
                        pedidoJSON.getString("nomepedido"),
                        pedidoJSON.getString("valor"),
                        pedidoJSON.getString("id_usuario"),
                        pedidoJSON.getString("id_produto"),
                        pedidoJSON.getInt("id"),
                        Pedido.Status.OK);
                list.add(pe);
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
    private byte[] pedidoToJsonBytes(Pedido pedido) {
        try {
            JSONObject jsonPessoa = new JSONObject();
            jsonPessoa.put("id", pedido.idServidor);
            jsonPessoa.put("nomepedido", pedido.nomepedido);
            jsonPessoa.put("valor", pedido.valor);
            jsonPessoa.put("id_usuario", pedido.id_usuario);
            jsonPessoa.put("id_produto", pedido.id_produto);
            String json = jsonPessoa.toString();
            return json.getBytes();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}