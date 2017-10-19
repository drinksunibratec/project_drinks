package br.com.drinksapp.pedido;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Renevalda Maria on 15/10/2017.
 */

public class PedidoSQLHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "dbPedido";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_PEDIDO = "pedido";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOMEPEDIDO = "nomepedido";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_ID_USUARIO = "id_usuario";
    public static final String COLUNA_ID_PRODUTO = "id_produto";
    public static final String COLUNA_STATUS = "status";
    public static final String COLUNA_ID_SERVIDOR = "id_servidor";

    public PedidoSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+ TABELA_PEDIDO +" (" +
                        COLUNA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUNA_NOMEPEDIDO     +" TEXT NOT NULL, "+
                        COLUNA_VALOR +" TEXT, " +
                        COLUNA_ID_USUARIO +" TEXT, "+
                        COLUNA_ID_PRODUTO +" TEXT, " +
                        COLUNA_STATUS +" INTEGER, " +
                        COLUNA_ID_SERVIDOR +" INTEGER UNIQUE)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as próximas versões
    }
}