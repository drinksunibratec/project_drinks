package br.com.drinksapp.estabelecimento;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class EstabelecimentoProvider extends ContentProvider {
    private static final String AUTHORITY = "br.com.drinksapp.estabelecimento";
    private static final String PATH = "estabelecimentos";
    private static final int TIPO_GERAL = 1;
    private static final int TIPO_ESTABELECIMENTO_ESPECIFICO = 2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY +"/"+ PATH);
    private EstabelecimentoSQLHelper mHelper;
    private static final UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, PATH, TIPO_GERAL);
        sUriMatcher.addURI(AUTHORITY, PATH + "/#", TIPO_ESTABELECIMENTO_ESPECIFICO);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB =  mHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case TIPO_GERAL:
                rowsDeleted = sqlDB.delete(EstabelecimentoSQLHelper.TABELA_ESTABELECIMENTO,
                        selection, selectionArgs);
                break;
            case TIPO_ESTABELECIMENTO_ESPECIFICO:
                String id = uri.getLastPathSegment();
                rowsDeleted = sqlDB.delete(EstabelecimentoSQLHelper.TABELA_ESTABELECIMENTO,
                        EstabelecimentoSQLHelper.COLUNA_ID +"= ?",
                        new String[]{ id });
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        int uriType = sUriMatcher.match(uri);
        switch (uriType){
            case TIPO_GERAL :
                return "vnd.android.cursor.dir/br.com.drinksapp.estabelecimento";
            case TIPO_ESTABELECIMENTO_ESPECIFICO:
                return "vnd.android.cursor.item/br.com.drinksapp.estabelecimento";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mHelper.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case TIPO_GERAL:
                id = sqlDB.insertWithOnConflict(EstabelecimentoSQLHelper.TABELA_ESTABELECIMENTO,
                        null, values, SQLiteDatabase.CONFLICT_REPLACE);
                break;
            default:
                throw new IllegalArgumentException("URI não suportada: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
    }

    @Override
    public boolean onCreate() {
        mHelper = new EstabelecimentoSQLHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder =  new SQLiteQueryBuilder();
        queryBuilder.setTables(EstabelecimentoSQLHelper.TABELA_ESTABELECIMENTO);
        Cursor cursor = null;
        switch (uriType) {
            case TIPO_GERAL:
                cursor = queryBuilder.query(db, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case TIPO_ESTABELECIMENTO_ESPECIFICO:
                queryBuilder.appendWhere(EstabelecimentoSQLHelper.COLUNA_ID + "= ?");
                cursor = queryBuilder.query(db, projection, selection,
                        new String[]{ uri.getLastPathSegment() },
                        null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mHelper.getWritableDatabase();
        int linhasAfetadas = 0;
        switch (uriType) {
            case TIPO_GERAL:
                linhasAfetadas = sqlDB.update(EstabelecimentoSQLHelper.TABELA_ESTABELECIMENTO,
                        values, selection, selectionArgs);
                break;
            case TIPO_ESTABELECIMENTO_ESPECIFICO:
                String id = uri.getLastPathSegment();
                linhasAfetadas = sqlDB.update(EstabelecimentoSQLHelper.TABELA_ESTABELECIMENTO,
                        values, EstabelecimentoSQLHelper.COLUNA_ID +"= ?",
                        new String[]{ id });
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return linhasAfetadas;
    }

}
