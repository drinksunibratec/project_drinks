package br.com.drinksapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;

/**
 * Created by Silvio Cedrim on 25/10/2017.
 */

public class CarrinhoComprasAdapter extends ArrayAdapter<PedidoProdutos> {
    Context context;

    DAODrinks mDAO;
    public CarrinhoComprasAdapter(Context context, List<PedidoProdutos> pp,  DAODrinks DAO) {
        super(context, 0, pp);
        this.context = context;
        this.mDAO = DAO;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PedidoProdutos pp = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_carrinho_compras, null);
            holder = new ViewHolder();
            holder.txtNomeProdutoCarrinho = (TextView) convertView.findViewById(R.id.txtNomeProdutoCarrinho);
            holder.txtPrecoProdutoCarrinho = (TextView) convertView.findViewById(R.id.txtPrecoProdutoCarrinho);
            holder.txtQuantidaderodutoCarrinho = (TextView) convertView.findViewById(R.id.txt_quantidade_produtos_carrinho);
            holder.btnBotaoMaisCarrinho = (ImageButton) convertView.findViewById(R.id.btn_maisProdutos_carrinho);
            holder.btnBotaoMenosCarrinho = (ImageButton) convertView.findViewById(R.id.btn_menosProdutos_carrinho);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNomeProdutoCarrinho.setText(pp.getProduto().getNome());
        holder.txtPrecoProdutoCarrinho.setText("R$ " + pp.getPrecoTotal());
        holder.txtQuantidaderodutoCarrinho.setText(String.valueOf(pp.getQuantidade()));

        holder.btnBotaoMaisCarrinho.setOnClickListener(new CliqueBotaoMais(
                holder.txtQuantidaderodutoCarrinho, holder.txtPrecoProdutoCarrinho, pp));
        holder.btnBotaoMenosCarrinho.setOnClickListener(new CliqueBotaoMenos(
                holder.txtQuantidaderodutoCarrinho, holder.txtPrecoProdutoCarrinho, pp));

        return convertView;
    }
    static class ViewHolder {
        TextView txtNomeProdutoCarrinho;
        TextView txtPrecoProdutoCarrinho;
        TextView txtQuantidaderodutoCarrinho;
        ImageButton btnBotaoMaisCarrinho;
        ImageButton btnBotaoMenosCarrinho;
    }

    class CliqueBotaoMais implements View.OnClickListener {

        TextView mTextQuantidadeProdutos;

        TextView mTextPrecoTotal;

        PedidoProdutos pp;

        public CliqueBotaoMais(TextView txtQuantidaderoduto, TextView textPrecoTotal, PedidoProdutos pp) {
            this.mTextQuantidadeProdutos = txtQuantidaderoduto;
            this.mTextPrecoTotal = textPrecoTotal;
            this.pp = pp;
        }

        @Override
        public void onClick(View v) {
            int mQuantidade = Integer.parseInt(this.mTextQuantidadeProdutos.getText().toString());
            mQuantidade++;
            mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
            mTextPrecoTotal.setText("R$ " + (mQuantidade * pp.getPreco()));

            Produto p = new Produto();
            p.setCodProduto(pp.getProduto().getCodProduto());
            p.setPreco(String.valueOf(pp.getPreco()));
            mDAO.atualizarQuantidadeProdutoNoCarrinho(p, mQuantidade);
        }
    }

    class CliqueBotaoMenos implements View.OnClickListener {

        TextView mTextQuantidadeProdutos;

        TextView mTextPrecoTotal;

        PedidoProdutos pp;


        public CliqueBotaoMenos(TextView txtQuantidaderoduto, TextView textPrecoTotal, PedidoProdutos pp) {
            this.mTextQuantidadeProdutos = txtQuantidaderoduto;
            this.mTextPrecoTotal = textPrecoTotal;
            this.pp = pp;
        }

        @Override
        public void onClick(View v) {
            int mQuantidade = Integer.parseInt(this.mTextQuantidadeProdutos.getText().toString());
            if(mQuantidade > 1){
                mQuantidade--;
            }
            mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
            mTextPrecoTotal.setText("R$ " + (mQuantidade * pp.getPreco()));

            Produto p = new Produto();
            p.setCodProduto(pp.getProduto().getCodProduto());
            p.setPreco(String.valueOf(pp.getPreco()));
            mDAO.atualizarQuantidadeProdutoNoCarrinho(p, mQuantidade);
        }
    }


}
