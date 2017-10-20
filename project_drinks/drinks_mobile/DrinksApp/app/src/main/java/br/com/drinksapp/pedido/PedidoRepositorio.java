package br.com.drinksapp.pedido;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

public class PedidoRepositorio {
    private Context ctx;
    public PedidoRepositorio(Context ctx) {
        this.ctx = ctx;
    }
    private long inserir(Pedido pedido) {
        pedido.status = Pedido.Status.INSERIR;
        long id = inserirLocal(pedido, ctx.getContentResolver());
        return id;
    }
    private int atualizar(Pedido pedido) {
        pedido.status = Pedido.Status.ATUALIZAR;
        int linhasAfetadas = atualizarLocal(pedido, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public void salvar(Pedido pedido) {
        if (pedido.id == 0) {
            inserir(pedido);
        } else {
            atualizar(pedido);
        }
    }
    public int excluir(Pedido pedido) {
        pedido.status = Pedido.Status.EXCLUIR;
        int linhasAfetadas = atualizarLocal(pedido, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public CursorLoader buscar(Context ctx, String s) {
        String where = null;
        String[] whereArgs = null;
        if (s != null) {
            where = PedidoSQLHelper.COLUNA_NOMEPEDIDO +" LIKE ?";
            whereArgs = new String[]{ "%"+ s +"%" };
        }
        return new CursorLoader(
                ctx,
                PedidoProvider.CONTENT_URI,
                null,
                where,
                whereArgs,
                PedidoSQLHelper.COLUNA_NOMEPEDIDO);
    }
    private ContentValues getValues(Pedido pedido) {
        ContentValues cv = new ContentValues();
        cv.put(PedidoSQLHelper.COLUNA_NOMEPEDIDO, pedido.nomepedido);
        cv.put(PedidoSQLHelper.COLUNA_VALOR, pedido.valor);
        cv.put(PedidoSQLHelper.COLUNA_ID_USUARIO, pedido.id_usuario);
        cv.put(PedidoSQLHelper.COLUNA_ID_PRODUTO, pedido.id_produto);
        cv.put(PedidoSQLHelper.COLUNA_STATUS, pedido.status.ordinal());
        if (pedido.idServidor != 0) {
            cv.put(PedidoSQLHelper.COLUNA_ID_SERVIDOR, pedido.idServidor);
        }

        return cv;
    }
    public static Pedido pedidoFromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(
                        PedidoSQLHelper.COLUNA_ID)
        );
        String nomepedido = cursor.getString(
                cursor.getColumnIndex(PedidoSQLHelper.COLUNA_NOMEPEDIDO)
        );
        String valor = cursor.getString(
                cursor.getColumnIndex(PedidoSQLHelper.COLUNA_VALOR)
        );
        String id_usuario = cursor.getString(
                cursor.getColumnIndex(PedidoSQLHelper.COLUNA_ID_USUARIO)
        );
        String id_produto = cursor.getString(
                cursor.getColumnIndex(PedidoSQLHelper.COLUNA_ID_PRODUTO)
        );
        int status = cursor.getInt(cursor.getColumnIndex(
                PedidoSQLHelper.COLUNA_STATUS));
        long idServidor = cursor.getLong(cursor.getColumnIndex(
                PedidoSQLHelper.COLUNA_ID_SERVIDOR));

        Pedido pedido = new Pedido(id, nomepedido, valor, id_usuario, id_produto,
                idServidor, Pedido.Status.values()[status]);

        return pedido;
    }

    public long inserirLocal(Pedido pedido, ContentResolver cr) {
        Uri uri = cr.insert(
                PedidoProvider.CONTENT_URI,
                getValues(pedido));
        long id = Long.parseLong(uri.getLastPathSegment());
        if (id != -1) {
            pedido.id = id;
        }
        return id;
    }
    public int atualizarLocal(Pedido pedido, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                PedidoProvider.CONTENT_URI, String.valueOf(pedido.id));
        int linhasAfetadas = cr.update(
                uri, getValues(pedido), null, null);
        return linhasAfetadas;
    }
    public int excluirLocal(Pedido pedido, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                PedidoProvider.CONTENT_URI, String.valueOf(pedido.id));
        int linhasAfetadas = cr.delete(uri, null, null);
        return linhasAfetadas;
    }
}