package br.com.drinksapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.util.AppConfig;
import br.com.drinksapp.util.Util;

/**
 * Created by Silvio Cedrim on 23/11/2017.
 */

public class ProdutoPesquisaAdapter extends ArrayAdapter<Produto>{

    private Context context;

    public ProdutoPesquisaAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto_compara_precos, null);
            holder = new ViewHolder();
            holder.txtNomeProdutoCompara = (TextView) convertView.findViewById(R.id.txtNomeProdutoCompara);
            holder.txtEstabelecimentoProdutoCompara = (TextView) convertView.findViewById(R.id.txtEstabelecimentoProdutoCompara);
            holder.txtPrecoProdutoLista = (TextView) convertView.findViewById(R.id.txtPrecoProdutoLista);
            holder.imagmProdutoCompara = (ImageView) convertView.findViewById(R.id.imagmProdutoCompara);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNomeProdutoCompara.setText(produto.getNome());
        holder.txtEstabelecimentoProdutoCompara.setText(produto.getEstabelecimento().getNomeFantasia());
        holder.txtPrecoProdutoLista.setText("R$ " + produto.getPreco());
        Glide.with(context).load(AppConfig.URL_IMAGENS + produto.getEan() + AppConfig.EXTENSAO_IMAGEM)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imagmProdutoCompara);
        return convertView;
    }


    static class ViewHolder {
        TextView txtNomeProdutoCompara;
        TextView txtEstabelecimentoProdutoCompara;
        TextView txtPrecoProdutoLista;
        ImageView imagmProdutoCompara;

    }


}

