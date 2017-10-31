package br.com.drinksapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class DrinksSQLHelper extends SQLiteOpenHelper{


        private static final String NOME_BANCO = "dbDrinks";

        private static final int VERSAO_BANCO = 1;


        public DrinksSQLHelper(Context context) {
            super(context, NOME_BANCO, null, VERSAO_BANCO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DrinksContract.SQL_CREATE_USUARIO);
            db.execSQL(DrinksContract.SQL_CREATE_ESTABELECIMENTO);
            db.execSQL(DrinksContract.SQL_CREATE_PRODUTO);
            db.execSQL(DrinksContract.SQL_CREATE_PEDIDO);
            db.execSQL(DrinksContract.SQL_CREATE_PEDIDO_PRODUTO);
            db.execSQL(DrinksContract.SQL_CREATE_PEDIDO_ENDERECO_ENTREGA);
            db.execSQL(DrinksContract.SQL_CREATE_CARRINHO_COMPRAS);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DrinksContract.TABLE_NAME_USUARIOS);
            db.execSQL("DROP TABLE IF EXISTS " + DrinksContract.TABLE_NAME_ESTABELECIMENTO);
            db.execSQL("DROP TABLE IF EXISTS " + DrinksContract.TABLE_NAME_PRODUTO);
            db.execSQL("DROP TABLE IF EXISTS " + DrinksContract.TABLE_NAME_PEDIDO);
            db.execSQL("DROP TABLE IF EXISTS " + DrinksContract.TABLE_NAME_PEDIDO_PRODUTO);
            db.execSQL("DROP TABLE IF EXISTS " + DrinksContract.TABLE_NAME_ENDERECO_ENTREGA);
            db.execSQL("DROP TABLE IF EXISTS " + DrinksContract.SQL_CREATE_CARRINHO_COMPRAS);

            // create new tables
            onCreate(db);
        }
}
