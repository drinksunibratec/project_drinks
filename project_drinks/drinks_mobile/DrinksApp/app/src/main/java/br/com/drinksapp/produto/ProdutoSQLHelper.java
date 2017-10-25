package br.com.drinksapp.produto;

/**
 * Created by Renevalda Maria on 24/09/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProdutoSQLHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "dbProduto";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_PRODUTO = "produto";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_GELADA = "gelada";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_PRECO = "preco";
    public static final String COLUNA_CODESTABELECIMENTO = "codEstabelecimento";
    public static final String COLUNA_STATUS = "status";
    public static final String COLUNA_ID_SERVIDOR = "id_servidor";

    public ProdutoSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+ TABELA_PRODUTO +" (" +
                        COLUNA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUNA_DESCRICAO     +" TEXT, "+
                        COLUNA_GELADA +" TEXT, " +
                        COLUNA_NOME +" TEXT NOT NULL, "+
                        COLUNA_PRECO +" TEXT, " +
                        COLUNA_CODESTABELECIMENTO +" TEXT, " +
                        COLUNA_STATUS +" INTEGER, " +
                        COLUNA_ID_SERVIDOR +" INTEGER UNIQUE)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as próximas versões
    }
}