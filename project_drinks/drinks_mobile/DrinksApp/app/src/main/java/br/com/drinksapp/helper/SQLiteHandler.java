package br.com.drinksapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USUARIO = "usuario";

    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TELEFONE = "telefone";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USUARIO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE, " + KEY_TELEFONE + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String nome, String email, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, nome);
        values.put(KEY_EMAIL, email);
        values.put(KEY_TELEFONE, telefone);

        // Inserting Row
        long id = db.insert(TABLE_USUARIO, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> usuario = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USUARIO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            usuario.put("nome", cursor.getString(1));
            usuario.put("email", cursor.getString(2));
            usuario.put("telefone", cursor.getString(3));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + usuario.toString());

        return usuario;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USUARIO, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}