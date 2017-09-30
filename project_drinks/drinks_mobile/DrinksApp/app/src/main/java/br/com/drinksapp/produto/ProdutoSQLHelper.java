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
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_ESTABELECIMENTO = "estabelecimento";
    public static final String COLUNA_ENDERECO = "endereco";
    public static final String COLUNA_BAIRRO = "bairro";
    public static final String COLUNA_PRECO = "preco";
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
                        COLUNA_NOME     +" TEXT NOT NULL, "+
                        COLUNA_ESTABELECIMENTO +" TEXT, "+
                        COLUNA_ENDERECO +" TEXT, " +
                        COLUNA_BAIRRO +" TEXT, " +
                        COLUNA_PRECO +" TEXT, " +
                        COLUNA_STATUS      +" INTEGER, " +
                        COLUNA_ID_SERVIDOR +" INTEGER UNIQUE)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as próximas versões
    }
}