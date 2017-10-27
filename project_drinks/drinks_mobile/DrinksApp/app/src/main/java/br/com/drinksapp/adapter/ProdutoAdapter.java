package br.com.drinksapp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Produto;

/**
 * Created by Silvio Cedrim on 25/10/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto> {
    public ProdutoAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto, null);
            holder = new ViewHolder();
            holder.txtNomeProduto = (TextView) convertView.findViewById(R.id.txtNomeProduto);
            holder.txtPrecoProduto = (TextView) convertView.findViewById(R.id.txtPrecoProduto);
            holder.txtNomeEstabelecimentoProduto= (TextView) convertView.findViewById(R.id.txtNomeEstabelecimentoProduto);
            holder.btnAddProdutoCarrinho = (ImageButton)convertView.findViewById(R.id.bnt_add_produto_carrinho);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNomeProduto.setText(produto.getNome());
        holder.txtPrecoProduto.setText(produto.getPreco());
        holder.txtNomeEstabelecimentoProduto.setText(produto.getEstabelecimento().getNomeFantasia());

        return convertView;
    }


    static class ViewHolder {
        TextView txtNomeProduto;
        TextView txtPrecoProduto;
        TextView txtNomeEstabelecimentoProduto;
        ImageButton btnAddProdutoCarrinho;
    }
}
