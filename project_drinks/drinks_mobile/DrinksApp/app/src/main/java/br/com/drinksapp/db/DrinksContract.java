package br.com.drinksapp.db;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public interface DrinksContract {

    String TABLE_NAME_PRODUTO = "PRODUTO";

    String TABLE_NAME_PRODUTO_ESTAB = "PRODUTO_ESTAB";

    String TABLE_NAME_USUARIOS = "USUARIOS";

    String TABLE_NAME_PEDIDO_PRODUTO = "PEDIDO_PRODUTO";

    String TABLE_NAME_PEDIDO = "PEDIDO";

    String TABLE_NAME_ESTABELECIMENTO = "ESTABELECIMENTO";

    String TABLE_NAME_ESTABELECIMENTOS_FAVORITOS = "ESTABELECIMENTOS_FAVORITOS";

    String TABLE_NAME_PRODUTOS_FAVORITOS = "PRODUTOS_FAVORITOS";

    String TABLE_NAME_ENDERECO_ENTREGA = "ENDERECO_ENTREGA";

    String TABLE_NAME_CARRINHO_COMPRAS = "CARRINHO_COMPRAS";

    String CODUSUARIO = "CODUSUARIO";

    String CODESTABELECIMENTO = "CODESTABELECIMENTO";

    String CODPRODUTO = "CODPRODUTO";

    String CODPEDIDO = "CODPEDIDO";

    String NOME = "NOME";

    String EAN = "EAN";

    String CNPJ = "CNPJ";

    String NOMEFANTASIA = "NOMEFANTASIA";

    String RUA = "RUA";

    String NUMERO = "NUMERO";

    String BAIRRO = "BAIRRO";

    String CIDADE = "CIDADE";

    String UF = "UF";

    String CEP = "CEP";

    String LATITUDE = "LATITUDE";

    String LONGITUDE = "LONGITUDE";

    String TELEFONE = "TELEFONE";

    String EMAIL = "EMAIL";

    String SENHA = "SENHA";

    String DESCRICAO = "DESCRICAO";

    String PRECO = "PRECO";

    String GELADA = "GELADA";

    String DATA = "DATA";

    String QUANTIDADE = "QUANTIDADE";

    String CODENDERECOENTREGA = "CODENDERECOENTREGA";

    String PRECO_TOTAL = "PRECO_TOTAL";

    String PRECO_UNITARIO = "PRECO_UNITARIO";

    String FAVORITO = "FAVORITO";

    String REF_IMG = "REF_IMG";

    String SQL_CREATE_USUARIO = "CREATE TABLE " + TABLE_NAME_USUARIOS + " (" +
            CODUSUARIO + " INTEGER PRIMARY KEY," +
            NOME + " TEXT NOT NULL," +
            EMAIL + " TEXT NOT NULL," +
            SENHA + " TEXT," +
            TELEFONE + " TEXT NOT NULL) ";

    String SQL_CREATE_ESTABELECIMENTO = "CREATE TABLE " + TABLE_NAME_ESTABELECIMENTO+ " (" +
            CODESTABELECIMENTO + " INTEGER PRIMARY KEY," +
            CNPJ + " TEXT NOT NULL," +
            NOMEFANTASIA + " TEXT NOT NULL," +
            RUA + " TEXT," +
            NUMERO + " TEXT," +
            BAIRRO + " TEXT," +
            CIDADE + " TEXT," +
            UF + " TEXT," +
            CEP + " TEXT," +
            LATITUDE + " TEXT," +
            LONGITUDE + " TEXT," +
            TELEFONE + " TEXT NOT NULL) ";

    String SQL_CREATE_PRODUTO = "CREATE TABLE " + TABLE_NAME_PRODUTO + " (" +
            CODPRODUTO + " INTEGER PRIMARY KEY," +
            NOME + " TEXT NOT NULL," +
            DESCRICAO + " TEXT," +
            EAN + " TEXT NOT NULL," +
            REF_IMG + " TEXT) ";

    String SQL_CREATE_PRODUTO_ESTAB = "CREATE TABLE " + TABLE_NAME_PRODUTO_ESTAB + " (" +
            CODPRODUTO + " INTEGER PRIMARY KEY," +
            EAN + " TEXT NOT NULL," +
            PRECO + " TEXT," +
            CODESTABELECIMENTO + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + CODESTABELECIMENTO + ") " +
            "REFERENCES " + TABLE_NAME_ESTABELECIMENTO + "(" + CODESTABELECIMENTO + ")," +
            "FOREIGN KEY(" + EAN + ") " +
            "REFERENCES " + TABLE_NAME_PRODUTO + "(" + EAN + "))";

    String SQL_CREATE_ESTABELECIMENTOS_FAVORITOS = "CREATE TABLE " + TABLE_NAME_ESTABELECIMENTOS_FAVORITOS + " (" +
            CODESTABELECIMENTO + " INTEGER NOT NULL, " +
            CODUSUARIO + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + CODUSUARIO + ") " +
            "REFERENCES " + TABLE_NAME_USUARIOS + "(" + CODUSUARIO + ")," +
            "FOREIGN KEY(" + CODESTABELECIMENTO + ") " +
            "REFERENCES " + TABLE_NAME_ESTABELECIMENTO + "(" + CODESTABELECIMENTO + ")) ";

    String SQL_CREATE_PRODUTOS_FAVORITOS = "CREATE TABLE " + TABLE_NAME_PRODUTOS_FAVORITOS + " (" +
            CODPRODUTO + " INTEGER NOT NULL, " +
            CODUSUARIO + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + CODUSUARIO + ") " +
            "REFERENCES " + TABLE_NAME_USUARIOS + "(" + CODUSUARIO + ")," +
            "FOREIGN KEY(" + CODPRODUTO + ") " +
            "REFERENCES " + TABLE_NAME_PRODUTO + "(" + CODPRODUTO + ")) ";


    String SQL_CREATE_PEDIDO = "CREATE TABLE " + TABLE_NAME_PEDIDO + " (" +
            CODPEDIDO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CODESTABELECIMENTO + " INTEGER NOT NULL, " +
            CODUSUARIO + " INTEGER NOT NULL, " +
            DATA + " TEXT NOT NULL," +
            "FOREIGN KEY(" + CODUSUARIO + ") " +
            "REFERENCES " + TABLE_NAME_USUARIOS + "(" + CODUSUARIO + ")," +
            "FOREIGN KEY(" + CODESTABELECIMENTO + ") " +
            "REFERENCES " + TABLE_NAME_ESTABELECIMENTO + "(" + CODESTABELECIMENTO + "))";

    String SQL_CREATE_CARRINHO_COMPRAS = "CREATE TABLE " + TABLE_NAME_CARRINHO_COMPRAS + " (" +
            CODPRODUTO + " INTEGER NOT NULL, " +
            CODESTABELECIMENTO + " INTEGER NOT NULL, " +
            QUANTIDADE + " INTEGER NOT NULL, " +
            PRECO_UNITARIO + " DOUBLE NOT NULL," +
            PRECO_TOTAL + " TEXT NOT NULL," +
            "FOREIGN KEY(" + CODESTABELECIMENTO + ") " +
            "REFERENCES " + TABLE_NAME_ESTABELECIMENTO + "(" + CODESTABELECIMENTO + ")" +
            "FOREIGN KEY(" + CODPRODUTO + ") " +
            "REFERENCES " + TABLE_NAME_PRODUTO + "(" + CODPRODUTO + "))";


    String SQL_CREATE_PEDIDO_PRODUTO = "CREATE TABLE " + TABLE_NAME_PEDIDO_PRODUTO + " (" +
            CODPEDIDO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CODPRODUTO + " INTEGER NOT NULL, " +
            QUANTIDADE + " INTEGER NOT NULL, " +
            PRECO_UNITARIO + " TEXT NOT NULL," +
            PRECO_TOTAL + " TEXT NOT NULL," +
            "FOREIGN KEY(" + CODPEDIDO + ") " +
            "REFERENCES " + TABLE_NAME_PEDIDO + "(" + CODPEDIDO + ")," +
            "FOREIGN KEY(" + CODPRODUTO + ") " +
            "REFERENCES " + TABLE_NAME_PRODUTO + "(" + CODPRODUTO + "))";

    String SQL_CREATE_PEDIDO_ENDERECO_ENTREGA = "CREATE TABLE " + TABLE_NAME_ENDERECO_ENTREGA + " (" +
            CODENDERECOENTREGA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CODUSUARIO + " INTEGER NOT NULL, " +
            CODPEDIDO + " INTEGER NOT NULL, " +
            RUA + " TEXT," +
            NUMERO + " TEXT," +
            BAIRRO + " TEXT," +
            CIDADE + " TEXT," +
            UF + " TEXT," +
            CEP + " TEXT," +
            FAVORITO + " TEXT, " +
            "FOREIGN KEY(" + CODPEDIDO + ") " +
            "REFERENCES " + TABLE_NAME_PEDIDO + "(" + CODPEDIDO + ")," +
            "FOREIGN KEY(" + CODUSUARIO + ") " +
            "REFERENCES " + TABLE_NAME_USUARIOS+ "(" + CODUSUARIO + "))";



}
