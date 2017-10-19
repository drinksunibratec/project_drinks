package br.com.drinksapp.estabelecimento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EstabelecimentoSQLHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "dbEstabelecimento";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_ESTABELECIMENTO = "estabelecimento";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_LOGRADOURO = "logradouro";
    public static final String COLUNA_NUMERO = "numero";
    public static final String COLUNA_BAIRRO = "bairro";
    public static final String COLUNA_CIDADE = "cidade";
    public static final String COLUNA_UF = "uf";
    public static final String COLUNA_CEP = "cep";
    public static final String COLUNA_LATITUDE = "latitude";
    public static final String COLUNA_LONGETUDE = "longetude";
    public static final String COLUNA_STATUS = "status";
    public static final String COLUNA_ID_SERVIDOR = "id_servidor";

    public EstabelecimentoSQLHelper(Context context) {super(context, NOME_BANCO, null, VERSAO_BANCO);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+ TABELA_ESTABELECIMENTO +" (" +
                        COLUNA_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COLUNA_NOME     +" TEXT NOT NULL, "+
                        COLUNA_LOGRADOURO +" TEXT, " +
                        COLUNA_NUMERO +" TEXT, "+
                        COLUNA_BAIRRO +" TEXT, " +
                        COLUNA_CIDADE +" TEXT, " +
                        COLUNA_UF +" TEXT, " +
                        COLUNA_CEP +" TEXT, " +
                        COLUNA_LATITUDE +" TEXT, " +
                        COLUNA_LONGETUDE +" TEXT, " +
                        COLUNA_STATUS +" INTEGER, " +
                        COLUNA_ID_SERVIDOR +" INTEGER UNIQUE)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as próximas versões
    }
}
