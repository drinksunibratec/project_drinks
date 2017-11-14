package br.com.drinksapp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.Produto;

/**
 * Created by Silvio Cedrim on 14/11/2017.
 */

public class PedidosAdapter extends ArrayAdapter<Pedido> {
    public PedidosAdapter(Context context, List<Pedido> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Pedido pedido = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pedido, null);
            holder = new ViewHolder();
            holder.txtEstabelecimentoPedido = (TextView) convertView.findViewById(R.id.txtEstabelecimentoPedido);
            holder.txtDataPedido = (TextView) convertView.findViewById(R.id.txtDataPedido);
            holder.txtPrecoPedido = (TextView) convertView.findViewById(R.id.txtPrecoPedido);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtEstabelecimentoPedido.setText(pedido.getEstabelecimento().getNomeFantasia());
        holder.txtDataPedido.setText(pedido.getDataPedido());
        holder.txtPrecoPedido.setText("R$ " + pedido.getValorTotal());

        return convertView;
    }


    static class ViewHolder {
        TextView txtEstabelecimentoPedido;
        TextView txtDataPedido;
        TextView txtPrecoPedido;
    }
}
