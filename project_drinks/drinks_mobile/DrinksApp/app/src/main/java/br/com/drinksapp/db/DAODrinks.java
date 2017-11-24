package br.com.drinksapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.bean.ItemCarrinhoCompras;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.bean.Usuarios;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class DAODrinks {

    private DrinksSQLHelper mHelper;

    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

    Context mContext;

    public DAODrinks(Context context) {
        this.mHelper = new DrinksSQLHelper(context);
        mContext = context;
        mContext.deleteDatabase(this.mHelper.getDatabaseName());
    }

    public long insertUsuario(Usuarios usuario) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = parserUsuario(usuario);
        long id = 0;
        id = db.insertOrThrow(DrinksContract.TABLE_NAME_USUARIOS, null, values);

        db.close();
        return id;
    }


    public void insertEstabelecimento(Estabelecimento estabelecimento) {
        SQLiteDatabase db = mHelper.getWritableDatabase();


        String sql = "SELECT * FROM " + DrinksContract.TABLE_NAME_ESTABELECIMENTO + " WHERE " + DrinksContract.CODESTABELECIMENTO + " = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(estabelecimento.getCodEstabelecimento())});

        if (cursor.getCount() == 0) {
            ContentValues values = parserEstabelecimento(estabelecimento);
            db.insertOrThrow(DrinksContract.TABLE_NAME_ESTABELECIMENTO, null, values);

            db.close();

        }

    }

    public void insertProduto(Produto produto) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        String sql = "SELECT * FROM " + DrinksContract.TABLE_NAME_PRODUTO + " WHERE " + DrinksContract.EAN + " = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(produto.getEan())});

        if (cursor.getCount() == 0) {
            ContentValues values = parserProduto(produto);
            db.insertOrThrow(DrinksContract.TABLE_NAME_PRODUTO, null, values);
            db.close();
        }
    }


    public void insertEstabelecimentoFavorito(Estabelecimento estabelecimento) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DrinksContract.CODESTABELECIMENTO, estabelecimento.getCodEstabelecimento());
        values.put(DrinksContract.CODUSUARIO, MySaveSharedPreference.getUserId(mContext));

        db.insertOrThrow(DrinksContract.TABLE_NAME_ESTABELECIMENTOS_FAVORITOS, null, values);

        db.close();
    }

    public void insertProdutoFavorito(Produto produto) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DrinksContract.CODPRODUTO, produto.getCodProduto());
        values.put(DrinksContract.CODUSUARIO, MySaveSharedPreference.getUserId(mContext));

        db.insertOrThrow(DrinksContract.TABLE_NAME_PRODUTOS_FAVORITOS, null, values);

        db.close();
    }

    public boolean isEstabelecimentoFavorito(Estabelecimento estabelecimento) {
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql = "SELECT COUNT(*) FROM " + DrinksContract.TABLE_NAME_ESTABELECIMENTOS_FAVORITOS;

        String[] argumentos = null;

        sql += " WHERE " + DrinksContract.CODESTABELECIMENTO + " = ?";
        argumentos = new String[]{String.valueOf(estabelecimento.getCodEstabelecimento())};

        Cursor cursor = db.rawQuery(sql, argumentos);
        boolean existe = false;
        if (cursor != null) {
            cursor.moveToFirst();
            existe = cursor.getInt(0) > 0;

            db.close();
            cursor.close();
        }
        return existe;
    }

    public boolean isProdutoFavorito(Produto produto) {
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql = "SELECT COUNT(*) FROM " + DrinksContract.TABLE_NAME_PRODUTOS_FAVORITOS;

        String[] argumentos = null;

        sql += " WHERE " + DrinksContract.CODPRODUTO + " = ?";
        argumentos = new String[]{String.valueOf(produto.getCodProduto())};

        Cursor cursor = db.rawQuery(sql, argumentos);
        boolean existe = false;
        if (cursor != null) {
            cursor.moveToFirst();
            existe = cursor.getInt(0) > 0;

            db.close();
            cursor.close();
        }
        return existe;
    }

    public int deleteEstabelecimentoFavorito(Estabelecimento estabelecimento) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int linhasAfetadas = db.delete(DrinksContract.TABLE_NAME_ESTABELECIMENTOS_FAVORITOS,
                DrinksContract.CODESTABELECIMENTO + " = ?",
                new String[]{String.valueOf(estabelecimento.getCodEstabelecimento())});
        db.close();
        return linhasAfetadas;
    }

    public int deleteProdutoFavorito(Produto produto) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int linhasAfetadas = db.delete(DrinksContract.TABLE_NAME_PRODUTOS_FAVORITOS,
                DrinksContract.CODPRODUTO + " = ?",
                new String[]{String.valueOf(produto.getCodProduto())});
        db.close();
        return linhasAfetadas;
    }

    public long insertPedido(Pedido pedido) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = parserPedido(pedido);
        long id = 0;
        id = db.insertOrThrow(DrinksContract.TABLE_NAME_PEDIDO, null, values);

        for (PedidoProdutos pp : pedido.getPedidoProdutos()) {
            db.insertOrThrow(DrinksContract.TABLE_NAME_PEDIDO_PRODUTO, null, parserPedidoProdutos(pp));
        }

        db.close();
        return id;
    }

    public boolean existeEstabelecimento(Estabelecimento estabelecimento) {
        boolean existe = false;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] argumentos = null;

        String sql = "SELECT * FROM " + DrinksContract.TABLE_NAME_ESTABELECIMENTO + " WHERE " + DrinksContract.CODESTABELECIMENTO + " = ?";

        argumentos = new String[]{String.valueOf(estabelecimento.getCodEstabelecimento())};

        Cursor cursor = db.rawQuery(sql, argumentos);

        if (cursor.getCount() > 0) {
            existe = true;
        }
        cursor.close();
        db.close();
        return existe;
    }

    public void atualizarQuantidadeProdutoNoCarrinho(Produto produto, int quantidade) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DrinksContract.QUANTIDADE, quantidade);
        values.put(DrinksContract.PRECO_TOTAL, String.valueOf(Double.parseDouble(produto.getPreco()) * quantidade));

        db.update(DrinksContract.TABLE_NAME_CARRINHO_COMPRAS,
                values,
                DrinksContract.CODPRODUTO + " = ?",
                new String[]{String.valueOf(produto.getCodProduto())});
        db.close();
    }

    public void deleteCarrinhoCompras() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(DrinksContract.TABLE_NAME_CARRINHO_COMPRAS,
                null,
                null);
    }

    public boolean existeProdutoNoCarrinho(Produto produto) {
        boolean existe = false;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] argumentos = null;

        String sql = "SELECT * FROM " + DrinksContract.TABLE_NAME_CARRINHO_COMPRAS + " WHERE " + DrinksContract.CODPRODUTO + " = ?";

        argumentos = new String[]{String.valueOf(produto.getCodProduto())};

        Cursor cursor = db.rawQuery(sql, argumentos);

        if (cursor.getCount() > 0) {
            existe = true;
        }
        cursor.close();
        db.close();
        return existe;
    }

    public int quantidadeDoProdutoNoCarrinho(Produto produto) {
        int quantidade = 0;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] argumentos = null;

        String sql = "SELECT QUANTIDADE FROM " + DrinksContract.TABLE_NAME_CARRINHO_COMPRAS + " WHERE " + DrinksContract.CODPRODUTO + " = ?";

        argumentos = new String[]{String.valueOf(produto.getCodProduto())};

        Cursor cursor = db.rawQuery(sql, argumentos);

        if (cursor.getCount() > 0) {
            int idxQuantidade = cursor.getColumnIndex(DrinksContract.QUANTIDADE);
            if (cursor.moveToFirst()) {
                quantidade = cursor.getInt(idxQuantidade);
            }
        }
        cursor.close();
        db.close();
        return quantidade;
    }

    public String getEANProduto(Produto produto) {
        String EAN = "";
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] argumentos = null;

        String sql = "SELECT EAN FROM " + DrinksContract.TABLE_NAME_PRODUTO + " WHERE " + DrinksContract.NOME + " LIKE ?";

        argumentos = new String[]{produto.getNome()};

        Cursor cursor = db.rawQuery(sql, argumentos);

        if (cursor.getCount() > 0) {
            int idxEAN = cursor.getColumnIndex(DrinksContract.EAN);
            if (cursor.moveToFirst()) {
                EAN = cursor.getString(idxEAN);
            }
        }
        cursor.close();
        db.close();
        return EAN;
    }

    public double precoTotalDoCarrinho(ItemCarrinhoCompras cc) {
        double valorTotal = 0;
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] argumentos = null;

        String sql = "SELECT sum(" + DrinksContract.PRECO_TOTAL + ")    as " + DrinksContract.PRECO_TOTAL + "  FROM " + DrinksContract.TABLE_NAME_CARRINHO_COMPRAS;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            int idxPrecoTotal = cursor.getColumnIndex(DrinksContract.PRECO_TOTAL);
            if (cursor.moveToFirst()) {
                valorTotal = cursor.getInt(idxPrecoTotal);
            }
        }
        cursor.close();
        db.close();
        return valorTotal;
    }

    public List<ItemCarrinhoCompras> consultarCarrinhoDeCompras() {

        List<ItemCarrinhoCompras> carrinho = new ArrayList<ItemCarrinhoCompras>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] argumentos = null;

        String sql = "SELECT C." + DrinksContract.QUANTIDADE + ", " +
                "C." + DrinksContract.PRECO_UNITARIO + ", " +
                "C." + DrinksContract.PRECO_TOTAL + ", " +
                "P." + DrinksContract.NOME + ", " +
                "P." + DrinksContract.CODPRODUTO + ", " +
                "C." + DrinksContract.CODESTABELECIMENTO +
                " FROM " + DrinksContract.TABLE_NAME_CARRINHO_COMPRAS + " C " +
                " JOIN " + DrinksContract.TABLE_NAME_PRODUTO + " P ON P." + DrinksContract.CODPRODUTO + " = C." + DrinksContract.CODPRODUTO;

        Cursor cursor = db.rawQuery(sql, argumentos);

        if (cursor.getCount() > 0) {
            int idxQuantidade = cursor.getColumnIndex(DrinksContract.QUANTIDADE);
            int idxNomeProduto = cursor.getColumnIndex(DrinksContract.NOME);
            int idxValorUnitario = cursor.getColumnIndex(DrinksContract.PRECO_UNITARIO);
            int idxValorTotal = cursor.getColumnIndex(DrinksContract.PRECO_TOTAL);
            int idxCodProduto = cursor.getColumnIndex(DrinksContract.CODPRODUTO);
            int idxCodEstabelecimento = cursor.getColumnIndex(DrinksContract.CODESTABELECIMENTO);

            while (cursor.moveToNext()) {
                int quantidade = cursor.getInt(idxQuantidade);
                String nome = cursor.getString(idxNomeProduto);
                Double valorUnitario = cursor.getDouble(idxValorUnitario);
                Double valorTotal = cursor.getDouble(idxValorTotal);
                long codProduto = cursor.getLong(idxCodProduto);
                long codEstabelecimento = cursor.getLong(idxCodEstabelecimento);

                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setCodProduto(codProduto);

                ItemCarrinhoCompras carrinhoCompras = new ItemCarrinhoCompras();
                carrinhoCompras.setCodEstabelcimento(codEstabelecimento);
                carrinhoCompras.setQuantidade(quantidade);
                carrinhoCompras.setPreco(valorUnitario);
                carrinhoCompras.setPrecoTotal(valorTotal);
                carrinhoCompras.setProduto(produto);

                carrinho.add(carrinhoCompras);
            }
        }
        cursor.close();
        db.close();
        return carrinho;
    }

    public List<Produto> consultarProdutosPorEstabelecimento(Estabelecimento estabelecimento) {

        List<Produto> produtos = new ArrayList<Produto>();
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql = "SELECT * " +
                " FROM " + DrinksContract.TABLE_NAME_PRODUTO + " WHERE " + DrinksContract.CODESTABELECIMENTO + " = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(estabelecimento.getCodEstabelecimento())});

        if (cursor.getCount() > 0) {
            int idxCodProduto = cursor.getColumnIndex(DrinksContract.CODPRODUTO);
            int idxNomeProduto = cursor.getColumnIndex(DrinksContract.NOME);
            int idxValorUnitario = cursor.getColumnIndex(DrinksContract.PRECO);
            int idxDescricao = cursor.getColumnIndex(DrinksContract.DESCRICAO);
            int idxGelada = cursor.getColumnIndex(DrinksContract.GELADA);
            int idxCodEstabelecimento = cursor.getColumnIndex(DrinksContract.CODESTABELECIMENTO);

            while (cursor.moveToNext()) {
                long codProduto = cursor.getLong(idxCodProduto);
                String nome = cursor.getString(idxNomeProduto);
                String descricao = cursor.getString(idxDescricao);
                String gelada = cursor.getString(idxGelada);
                Double valorUnitario = cursor.getDouble(idxValorUnitario);
                long codEstabelecimento = cursor.getLong(idxCodEstabelecimento);

                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setCodProduto(codProduto);
                produto.setDescricao(descricao);
                produto.setGelada(gelada);
                produto.setPreco(String.valueOf(valorUnitario));
                produto.setCodEstabelecimento(String.valueOf(codEstabelecimento));

                produtos.add(produto);
            }
        }
        cursor.close();
        db.close();
        return produtos;
    }

    public List<Produto> consultarProdutos() {

        List<Produto> produtos = new ArrayList<Produto>();
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + DrinksContract.TABLE_NAME_PRODUTO;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            int idxCodProduto = cursor.getColumnIndex(DrinksContract.CODPRODUTO);
            int idxNomeProduto = cursor.getColumnIndex(DrinksContract.NOME);
            int idxDescricao = cursor.getColumnIndex(DrinksContract.DESCRICAO);
            int idxEan = cursor.getColumnIndex(DrinksContract.EAN);

            while (cursor.moveToNext()) {
                long codProduto = cursor.getLong(idxCodProduto);
                String nome = cursor.getString(idxNomeProduto);
                String descricao = cursor.getString(idxDescricao);
                String ean = cursor.getString(idxEan);

                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setCodProduto(codProduto);
                produto.setDescricao(descricao);
                produto.setEan(ean);

                produtos.add(produto);
            }
        }
        cursor.close();
        db.close();
        return produtos;
    }

    public List<Produto> consultarProdutosFavoritos() {

        List<Produto> produtos = new ArrayList<Produto>();
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql = "SELECT P." + DrinksContract.CODPRODUTO + ", " +
                "P." + DrinksContract.NOME + ", " +
                "PE." + DrinksContract.PRECO + ", " +
                "P." + DrinksContract.DESCRICAO +
                " FROM " + DrinksContract.TABLE_NAME_PRODUTOS_FAVORITOS + " F " +
                " JOIN " + DrinksContract.TABLE_NAME_PRODUTO + " P ON P." + DrinksContract.CODPRODUTO + " = F." + DrinksContract.CODPRODUTO +
                " JOIN " + DrinksContract.TABLE_NAME_PRODUTO_ESTAB + " PE ON P." + DrinksContract.EAN + " = PE." + DrinksContract.EAN;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            int idxCodProduto = cursor.getColumnIndex(DrinksContract.CODPRODUTO);
            int idxNomeProduto = cursor.getColumnIndex(DrinksContract.NOME);
            int idxValorUnitario = cursor.getColumnIndex(DrinksContract.PRECO);
            int idxDescricao = cursor.getColumnIndex(DrinksContract.DESCRICAO);
            int idxGelada = cursor.getColumnIndex(DrinksContract.GELADA);

            while (cursor.moveToNext()) {
                long codProduto = cursor.getLong(idxCodProduto);
                String nome = cursor.getString(idxNomeProduto);
                String descricao = cursor.getString(idxDescricao);
                String gelada = cursor.getString(idxGelada);
                Double valorUnitario = cursor.getDouble(idxValorUnitario);

                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setCodProduto(codProduto);
                produto.setDescricao(descricao);
                produto.setGelada(gelada);
                produto.setPreco(String.valueOf(valorUnitario));

                produtos.add(produto);
            }
        }
        cursor.close();
        db.close();
        return produtos;
    }

    public List<Estabelecimento> consultarEstabelecimentosFavoritos() {

        List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql = "SELECT E." + DrinksContract.CODESTABELECIMENTO + ", " +
                "E." + DrinksContract.NOMEFANTASIA + ", " +
                "E." + DrinksContract.RUA + ", " +
                "E." + DrinksContract.NUMERO + ", " +
                "E." + DrinksContract.BAIRRO + ", " +
                "E." + DrinksContract.CIDADE + ", " +
                "E." + DrinksContract.UF +
                " FROM " + DrinksContract.TABLE_NAME_ESTABELECIMENTOS_FAVORITOS + " F " +
                " JOIN " + DrinksContract.TABLE_NAME_ESTABELECIMENTO + " E ON E." + DrinksContract.CODESTABELECIMENTO + " = F." + DrinksContract.CODESTABELECIMENTO;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            int idxCodEstabelecimento = cursor.getColumnIndex(DrinksContract.CODESTABELECIMENTO);
            int idxNome = cursor.getColumnIndex(DrinksContract.NOMEFANTASIA);
            int idxRua = cursor.getColumnIndex(DrinksContract.RUA);
            int idxNumero = cursor.getColumnIndex(DrinksContract.NUMERO);
            int idxBairro = cursor.getColumnIndex(DrinksContract.BAIRRO);
            int idxCidade = cursor.getColumnIndex(DrinksContract.CIDADE);
            int idxEstado = cursor.getColumnIndex(DrinksContract.UF);

            while (cursor.moveToNext()) {
                long codEstabelecimento = cursor.getLong(idxCodEstabelecimento);
                String nome = cursor.getString(idxNome);
                String rua = cursor.getString(idxRua);
                int numero = cursor.getInt(idxNumero);
                String bairro = cursor.getString(idxBairro);
                String cidade = cursor.getString(idxCidade);
                String estado = cursor.getString(idxEstado);

                Estabelecimento estabelecimento = new Estabelecimento();
                estabelecimento.setCodEstabelecimento(codEstabelecimento);
                estabelecimento.setNomeFantasia(nome);
                estabelecimento.setRua(rua);
                estabelecimento.setNumero(String.valueOf(numero));
                estabelecimento.setBairro(bairro);
                estabelecimento.setCidade(cidade);
                estabelecimento.setUf(estado);

                estabelecimentos.add(estabelecimento);
            }
        }
        cursor.close();
        db.close();
        return estabelecimentos;
    }


    private ContentValues parserUsuario(Usuarios usuario) {
        ContentValues values = new ContentValues();
        values.put(DrinksContract.CODUSUARIO, usuario.getCodUsuario());
        values.put(DrinksContract.NOME, usuario.getNome());
        values.put(DrinksContract.EMAIL, usuario.getEmail());
        values.put(DrinksContract.SENHA, usuario.getSenha());
        values.put(DrinksContract.TELEFONE, usuario.getTelefone());

        return values;
    }


    private ContentValues parserProduto(Produto produto) {
        ContentValues values = new ContentValues();
        values.put(DrinksContract.CODPRODUTO, produto.getCodProduto());
        values.put(DrinksContract.NOME, produto.getNome());
        values.put(DrinksContract.DESCRICAO, produto.getDescricao());
        values.put(DrinksContract.EAN, produto.getEan());
        values.put(DrinksContract.REF_IMG, produto.getRef_img());

        return values;
    }

    private ContentValues parserPedido(Pedido pedido) {
        ContentValues values = new ContentValues();
        values.put(DrinksContract.CODPEDIDO, pedido.getCodPedido());
        values.put(DrinksContract.CODUSUARIO, pedido.getUsuario().getCodUsuario());
        values.put(DrinksContract.CODESTABELECIMENTO, pedido.getEstabelecimento().getCodEstabelecimento());
        values.put(DrinksContract.DATA, dt.format(pedido.getDataPedido()));
        values.put(DrinksContract.PRECO, String.valueOf(pedido.getValorTotal()));

        return values;
    }

    private ContentValues parserPedidoProdutos(PedidoProdutos pedidoProdutos) {
        ContentValues values = new ContentValues();
        values.put(DrinksContract.CODPEDIDO, pedidoProdutos.getCodPedido());
        values.put(DrinksContract.CODPRODUTO, pedidoProdutos.getCodProduto());
//        values.put(DrinksContract.QUANTIDADE, pedidoProdutos.getQuantidade());
//        values.put(DrinksContract.PRECO_UNITARIO, pedidoProdutos.getPreco());
//        values.put(DrinksContract.PRECO_TOTAL, pedidoProdutos.getPrecoTotal());


        return values;
    }

    private ContentValues parserEstabelecimento(Estabelecimento estabelecimento) {
        ContentValues values = new ContentValues();
        values.put(DrinksContract.CODESTABELECIMENTO, estabelecimento.getCodEstabelecimento());
        values.put(DrinksContract.NOMEFANTASIA, estabelecimento.getNomeFantasia());
        values.put(DrinksContract.CNPJ, estabelecimento.getCnpj());
        values.put(DrinksContract.RUA, estabelecimento.getRua());
        values.put(DrinksContract.NUMERO, estabelecimento.getNumero());
        values.put(DrinksContract.BAIRRO, estabelecimento.getBairro());
        values.put(DrinksContract.CIDADE, estabelecimento.getCidade());
        values.put(DrinksContract.UF, estabelecimento.getUf());
        values.put(DrinksContract.LATITUDE, estabelecimento.getLatitude());
        values.put(DrinksContract.LONGITUDE, estabelecimento.getLongitude());
        values.put(DrinksContract.TELEFONE, estabelecimento.getTelefone());

        return values;
    }

    public void insertProdutoNoCarrinho(ItemCarrinhoCompras carrinhoCompras) {
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DrinksContract.QUANTIDADE, carrinhoCompras.getQuantidade());
        values.put(DrinksContract.CODESTABELECIMENTO, carrinhoCompras.getCodEstabelcimento());
        values.put(DrinksContract.CODPRODUTO, carrinhoCompras.getCodProduto());
        values.put(DrinksContract.PRECO_UNITARIO, carrinhoCompras.getPreco());
        values.put(DrinksContract.PRECO_TOTAL, String.valueOf(carrinhoCompras.getPreco() * carrinhoCompras.getQuantidade()));


        db.insertOrThrow(DrinksContract.TABLE_NAME_CARRINHO_COMPRAS, null, values);

        db.close();
    }
}
