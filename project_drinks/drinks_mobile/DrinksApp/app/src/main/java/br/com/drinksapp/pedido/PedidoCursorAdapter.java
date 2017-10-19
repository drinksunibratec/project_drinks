package br.com.drinksapp.pedido;

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

public class PedidoCursorAdapter extends CursorAdapter {

    public PedidoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNomePedido = (TextView) view.findViewById(R.id.txtNomepedido);
        TextView txtValor = (TextView) view.findViewById(R.id.txtValor);
        TextView txtId_Usuario = (TextView) view.findViewById(R.id.txtId_usuario);
        TextView txtId_Produto = (TextView) view.findViewById(R.id.txtId_produto);

        txtNomePedido.setText(cursor.getString(cursor.getColumnIndex(PedidoSQLHelper.COLUNA_NOMEPEDIDO)));
        txtValor.setText(cursor.getString(cursor.getColumnIndex(PedidoSQLHelper.COLUNA_VALOR)));
        txtId_Usuario.setText(cursor.getString(cursor.getColumnIndex(PedidoSQLHelper.COLUNA_ID_USUARIO)));
        txtId_Produto.setText(cursor.getString(cursor.getColumnIndex(PedidoSQLHelper.COLUNA_ID_PRODUTO)));

        int status = cursor.getInt(cursor.getColumnIndex(PedidoSQLHelper.COLUNA_STATUS));
        if (status == Pedido.Status.EXCLUIR.ordinal()) {
            txtNomePedido.setTextColor(Color.RED);
        } else {
            txtNomePedido.setTextColor(Color.BLACK);
        }
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_pedido, null);
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