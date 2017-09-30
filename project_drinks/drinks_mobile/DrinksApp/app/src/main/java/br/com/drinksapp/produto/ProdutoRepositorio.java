package br.com.drinksapp.produto;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

public class ProdutoRepositorio {
    private Context ctx;
    public ProdutoRepositorio(Context ctx) {
        this.ctx = ctx;
    }
    private long inserir(Produto produto) {
        produto.status = Produto.Status.INSERIR;
        long id = inserirLocal(produto, ctx.getContentResolver());
        return id;
    }
    private int atualizar(Produto produto) {
        produto.status = Produto.Status.ATUALIZAR;
        int linhasAfetadas = atualizarLocal(produto, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public void salvar(Produto produto) {
        if (produto.id == 0) {
            inserir(produto);
        } else {
            atualizar(produto);
        }
    }
    public int excluir(Produto produto) {
        produto.status = Produto.Status.EXCLUIR;
        int linhasAfetadas = atualizarLocal(produto, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public CursorLoader buscar(Context ctx, String s) {
        String where = null;
        String[] whereArgs = null;
        if (s != null) {
            where = ProdutoSQLHelper.COLUNA_NOME +" LIKE ?";
            whereArgs = new String[]{ "%"+ s +"%" };
        }
        return new CursorLoader(
                ctx,
                ProdutoProvider.CONTENT_URI,
                null,
                where,
                whereArgs,
                ProdutoSQLHelper.COLUNA_NOME);
    }
    private ContentValues getValues(Produto produto) {
        ContentValues cv = new ContentValues();
        cv.put(ProdutoSQLHelper.COLUNA_NOME, produto.nome);
        cv.put(ProdutoSQLHelper.COLUNA_ESTABELECIMENTO, produto.estabelecimento);
        cv.put(ProdutoSQLHelper.COLUNA_ENDERECO, produto.endereco);
        cv.put(ProdutoSQLHelper.COLUNA_BAIRRO, produto.bairro);
        cv.put(ProdutoSQLHelper.COLUNA_PRECO, produto.preco);
        cv.put(ProdutoSQLHelper.COLUNA_STATUS, produto.status.ordinal());
        if (produto.idServidor != 0) {
            cv.put(ProdutoSQLHelper.COLUNA_ID_SERVIDOR, produto.idServidor);
        }

        return cv;
    }
    public static Produto produtoFromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(
                        ProdutoSQLHelper.COLUNA_ID)
        );
        String nome = cursor.getString(
                cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_NOME)
        );
        String estabelecimento = cursor.getString(
                cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_ESTABELECIMENTO)
        );
        String endereco = cursor.getString(
                cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_ENDERECO)
        );
        String bairro = cursor.getString(
                cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_BAIRRO)
        );
        String preco = cursor.getString(
                cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_PRECO)
        );
        int status = cursor.getInt(cursor.getColumnIndex(
                ProdutoSQLHelper.COLUNA_STATUS));
        long idServidor = cursor.getLong(cursor.getColumnIndex(
                ProdutoSQLHelper.COLUNA_ID_SERVIDOR));
        Produto produto = new Produto(id, nome, estabelecimento, endereco, bairro, preco,
                idServidor, Produto.Status.values()[status]);

        return produto;
    }

    public long inserirLocal(Produto produto, ContentResolver cr) {
        Uri uri = cr.insert(
                ProdutoProvider.CONTENT_URI,
                getValues(produto));
        long id = Long.parseLong(uri.getLastPathSegment());
        if (id != -1) {
            produto.id = id;
        }
        return id;
    }
    public int atualizarLocal(Produto produto, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                ProdutoProvider.CONTENT_URI, String.valueOf(produto.id));
        int linhasAfetadas = cr.update(
                uri, getValues(produto), null, null);
        return linhasAfetadas;
    }
    public int excluirLocal(Produto produto, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                ProdutoProvider.CONTENT_URI, String.valueOf(produto.id));
        int linhasAfetadas = cr.delete(uri, null, null);
        return linhasAfetadas;
    }
}