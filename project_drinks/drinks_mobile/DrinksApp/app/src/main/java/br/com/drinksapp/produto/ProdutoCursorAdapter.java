package br.com.drinksapp.produto;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import br.com.drinksapp.R;

public class ProdutoCursorAdapter extends CursorAdapter {
    public ProdutoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtMessage = (TextView) view.findViewById(R.id.txtNome);
        TextView txtEstabelecimento = (TextView) view.findViewById(R.id.txtEstabelecimento);
        TextView txtBairro = (TextView) view.findViewById(R.id.txtBairro);
        TextView txtPreco = (TextView) view.findViewById(R.id.txtPreco);

        txtMessage.setText(cursor.getString(cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_NOME)));
        txtEstabelecimento.setText(cursor.getString(cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_ESTABELECIMENTO)));
        txtBairro.setText(cursor.getString(cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_BAIRRO)));
        txtPreco.setText(cursor.getString(cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_PRECO)));

        int status = cursor.getInt(cursor.getColumnIndex(ProdutoSQLHelper.COLUNA_STATUS));
        if (status == Produto.Status.EXCLUIR.ordinal()) {
            txtMessage.setTextColor(Color.RED);
        } else {
            txtMessage.setTextColor(Color.BLACK);
        }
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_produto, null);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        ListView listView = (ListView) parent;
        int color = listView.isItemChecked(position) ?
                Color.argb(0xFF, 0x31, 0xB6, 0xE7) :
                Color.TRANSPARENT;
        v.setBackgroundColor(color);
        return v;
    }
}