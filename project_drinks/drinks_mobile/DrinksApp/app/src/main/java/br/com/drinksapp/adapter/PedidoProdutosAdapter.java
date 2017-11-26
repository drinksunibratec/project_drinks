package br.com.drinksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.util.AppConfig;

/**
 * Created by Silvio Cedrim on 25/10/2017.
 */

public class PedidoProdutosAdapter extends ArrayAdapter<PedidoProdutos> {
    Context context;
    public PedidoProdutosAdapter(Context context, List<PedidoProdutos> pedidoProdutos) {
        super(context, 0, pedidoProdutos);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PedidoProdutos produto = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto_detalhe_pedido, null);
            holder = new ViewHolder();
            holder.txtNomeProdutoDetalhe = (TextView) convertView.findViewById(R.id.txtNomeProdutoDetalhe);
            holder.txtPrecoProdutoDetalhe = (TextView) convertView.findViewById(R.id.txtPrecoProdutoDetalhe);
            holder.txtDescricaoProdutoDetalhe = (TextView) convertView.findViewById(R.id.txtDescricaoProdutoDetalhe);
            holder.txtQuantidadeProdutosDetalhe = (TextView) convertView.findViewById(R.id.txtQuantidadeProdutosDetalhe);
            holder.txtValorTotalProdutoDetalhe = (TextView) convertView.findViewById(R.id.txtValorTotalProdutoDetalhe);
            holder.imagemProdutoDetalhePedido = (ImageView) convertView.findViewById(R.id.imagemProdutoDetalhePedido);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNomeProdutoDetalhe.setText(produto.getProduto().getNome());
        holder.txtPrecoProdutoDetalhe.setText("Preço unitário: R$ " + produto.getProduto().getPreco());
        holder.txtDescricaoProdutoDetalhe.setText(produto.getProduto().getDescricao());
        holder.txtQuantidadeProdutosDetalhe.setText("Quantidade: " + produto.getQuantidade());
        holder.txtValorTotalProdutoDetalhe.setText("Valor total: R$ " + (Double.parseDouble(produto.getProduto().getPreco()) * produto.getQuantidade()));
        Glide.with(context).load(AppConfig.URL_IMAGENS + produto.getProduto().getEan() + AppConfig.EXTENSAO_IMAGEM)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imagemProdutoDetalhePedido);
        return convertView;
    }


    static class ViewHolder {
        TextView txtNomeProdutoDetalhe;
        TextView txtPrecoProdutoDetalhe;
        TextView txtDescricaoProdutoDetalhe;
        TextView txtQuantidadeProdutosDetalhe;
        TextView txtValorTotalProdutoDetalhe;
        ImageView imagemProdutoDetalhePedido;
    }


}
