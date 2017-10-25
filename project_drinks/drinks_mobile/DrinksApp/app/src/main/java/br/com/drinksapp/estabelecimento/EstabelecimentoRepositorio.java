package br.com.drinksapp.estabelecimento;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

public class EstabelecimentoRepositorio {
    private Context ctx;
    public EstabelecimentoRepositorio(Context ctx) {
        this.ctx = ctx;
    }
    private long inserir(Estabelecimento estabelecimento) {
        estabelecimento.status = Estabelecimento.Status.INSERIR;
        long codEstabelecimento = inserirLocal(estabelecimento, ctx.getContentResolver());
        return codEstabelecimento;
    }
    private int atualizar(Estabelecimento estabelecimento) {
        estabelecimento.status = Estabelecimento.Status.ATUALIZAR;
        int linhasAfetadas = atualizarLocal(estabelecimento, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public void salvar(Estabelecimento estabelecimento) {
        if (estabelecimento.id == 0) {
            inserir(estabelecimento);
        } else {
            atualizar(estabelecimento);
        }
    }
    public int excluir(Estabelecimento estabelecimento) {
        estabelecimento.status = Estabelecimento.Status.EXCLUIR;
        int linhasAfetadas = atualizarLocal(estabelecimento, ctx.getContentResolver());
        return linhasAfetadas;
    }
    public CursorLoader buscar(Context ctx, String s) {
        String where = null;
        String[] whereArgs = null;
        if (s != null) {
            where = EstabelecimentoSQLHelper.COLUNA_NOMEFANTASIA +" LIKE ?";
            whereArgs = new String[]{ "%"+ s +"%" };
        }
        return new CursorLoader(
                ctx,
                EstabelecimentoProvider.CONTENT_URI,
                null,
                where,
                whereArgs,
                EstabelecimentoSQLHelper.COLUNA_NOMEFANTASIA);
    }
    private ContentValues getValues(Estabelecimento estabelecimento) {
        ContentValues cv = new ContentValues();
        cv.put(EstabelecimentoSQLHelper.COLUNA_NOMEFANTASIA, estabelecimento.nomeFantasia);
        cv.put(EstabelecimentoSQLHelper.COLUNA_RUA, estabelecimento.rua);
        cv.put(EstabelecimentoSQLHelper.COLUNA_NUMERO, estabelecimento.numero);
        cv.put(EstabelecimentoSQLHelper.COLUNA_BAIRRO, estabelecimento.bairro);
        cv.put(EstabelecimentoSQLHelper.COLUNA_CIDADE, estabelecimento.cidade);
        cv.put(EstabelecimentoSQLHelper.COLUNA_UF, estabelecimento.uf);
        cv.put(EstabelecimentoSQLHelper.COLUNA_CEP, estabelecimento.cep);
        cv.put(EstabelecimentoSQLHelper.COLUNA_LATITUDE, estabelecimento.latitude);
        cv.put(EstabelecimentoSQLHelper.COLUNA_LONGETUDE, estabelecimento.longetude);
        cv.put(EstabelecimentoSQLHelper.COLUNA_STATUS, estabelecimento.status.ordinal());
        if (estabelecimento.idServidor != 0) {
            cv.put(EstabelecimentoSQLHelper.COLUNA_ID_SERVIDOR, estabelecimento.idServidor);
        }

        return cv;
    }
    public static Estabelecimento estabelecimentoFromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(
                        EstabelecimentoSQLHelper.COLUNA_ID)
        );
        String nomeFantasia = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_NOMEFANTASIA)
        );
        String rua = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_RUA)
        );
        String numero = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_NUMERO)
        );
        String bairro = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_BAIRRO)
        );
        String cidade = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_CIDADE)
        );
        String uf = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_UF)
        );
        String cep = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_CEP)
        );
        String latitude = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_LATITUDE)
        );
        String londetude = cursor.getString(
                cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_LONGETUDE)
        );

        int status = cursor.getInt(cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_STATUS));
        long idServidor = cursor.getLong(cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_ID_SERVIDOR));

        Estabelecimento estabelecimento = new Estabelecimento(id, nomeFantasia, rua, numero, bairro, cidade, uf, cep, latitude, londetude,
                idServidor, Estabelecimento.Status.values()[status]);

        return estabelecimento;
    }

    public long inserirLocal(Estabelecimento estabelecimento, ContentResolver cr) {
        Uri uri = cr.insert(
                EstabelecimentoProvider.CONTENT_URI,
                getValues(estabelecimento));
        long id = Long.parseLong(uri.getLastPathSegment());
        if (id != -1) {
            estabelecimento.id = id;
        }
        return id;
    }
    public int atualizarLocal(Estabelecimento estabelecimento, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                EstabelecimentoProvider.CONTENT_URI, String.valueOf(estabelecimento.id));
        int linhasAfetadas = cr.update(
                uri, getValues(estabelecimento), null, null);
        return linhasAfetadas;
    }
    public int excluirLocal(Estabelecimento estabelecimento, ContentResolver cr) {
        Uri uri = Uri.withAppendedPath(
                EstabelecimentoProvider.CONTENT_URI, String.valueOf(estabelecimento.id));
        int linhasAfetadas = cr.delete(uri, null, null);
        return linhasAfetadas;
    }
}