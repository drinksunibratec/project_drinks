package br.com.drinksapp.estabelecimento;

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

public class EstabelecimentoCursorAdapter extends CursorAdapter {

    public EstabelecimentoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNomeFantasia = (TextView) view.findViewById(R.id.txtNomeFantasia);
        TextView txtRua = (TextView) view.findViewById(R.id.txtRua);
        TextView txtBairro = (TextView) view.findViewById(R.id.txtBairro);

        txtNomeFantasia.setText(cursor.getString(cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_NOMEFANTASIA)));
        txtRua.setText(cursor.getString(cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_RUA)));
        txtBairro.setText(cursor.getString(cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_BAIRRO)));

        int status = cursor.getInt(cursor.getColumnIndex(EstabelecimentoSQLHelper.COLUNA_STATUS));
        if (status == Estabelecimento.Status.EXCLUIR.ordinal()) {
            txtNomeFantasia.setTextColor(Color.RED);
        } else {
            txtNomeFantasia.setTextColor(Color.BLACK);
        }
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_estabelecimento, null);
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