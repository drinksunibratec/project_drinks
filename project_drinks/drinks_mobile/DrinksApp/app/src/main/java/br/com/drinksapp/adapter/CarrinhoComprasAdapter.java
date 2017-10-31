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
import br.com.drinksapp.bean.ItemCarrinhoCompras;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;

/**
 * Created by Silvio Cedrim on 25/10/2017.
 */

public class CarrinhoComprasAdapter extends ArrayAdapter<ItemCarrinhoCompras> {
    Context context;

    DAODrinks mDAO;
    public CarrinhoComprasAdapter(Context context, List<ItemCarrinhoCompras> cc, DAODrinks DAO) {
        super(context, 0, cc);
        this.context = context;
        this.mDAO = DAO;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemCarrinhoCompras cc = getItem(position);

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

        holder.txtNomeProdutoCarrinho.setText(cc.getProduto().getNome());
        holder.txtPrecoProdutoCarrinho.setText("R$ " + cc.getPrecoTotal());
        holder.txtQuantidaderodutoCarrinho.setText(String.valueOf(cc.getQuantidade()));

        holder.btnBotaoMaisCarrinho.setOnClickListener(new CliqueBotaoMais(
                holder.txtQuantidaderodutoCarrinho, holder.txtPrecoProdutoCarrinho, cc));
        holder.btnBotaoMenosCarrinho.setOnClickListener(new CliqueBotaoMenos(
                holder.txtQuantidaderodutoCarrinho, holder.txtPrecoProdutoCarrinho, cc));

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

        ItemCarrinhoCompras cc;

        public CliqueBotaoMais(TextView txtQuantidaderoduto, TextView textPrecoTotal, ItemCarrinhoCompras cc) {
            this.mTextQuantidadeProdutos = txtQuantidaderoduto;
            this.mTextPrecoTotal = textPrecoTotal;
            this.cc = cc;
        }

        @Override
        public void onClick(View v) {
            int mQuantidade = Integer.parseInt(this.mTextQuantidadeProdutos.getText().toString());
            mQuantidade++;
            mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
            mTextPrecoTotal.setText("R$ " + (mQuantidade * cc.getPreco()));

            Produto p = new Produto();
            p.setCodProduto(cc.getProduto().getCodProduto());
            p.setPreco(String.valueOf(cc.getPreco()));
            mDAO.atualizarQuantidadeProdutoNoCarrinho(p, mQuantidade);
        }
    }

    class CliqueBotaoMenos implements View.OnClickListener {

        TextView mTextQuantidadeProdutos;

        TextView mTextPrecoTotal;

        ItemCarrinhoCompras cc;


        public CliqueBotaoMenos(TextView txtQuantidaderoduto, TextView textPrecoTotal, ItemCarrinhoCompras cc) {
            this.mTextQuantidadeProdutos = txtQuantidaderoduto;
            this.mTextPrecoTotal = textPrecoTotal;
            this.cc = cc;
        }

        @Override
        public void onClick(View v) {
            int mQuantidade = Integer.parseInt(this.mTextQuantidadeProdutos.getText().toString());
            if(mQuantidade > 1){
                mQuantidade--;
            }
            mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
            mTextPrecoTotal.setText("R$ " + (mQuantidade * cc.getPreco()));

            Produto p = new Produto();
            p.setCodProduto(cc.getProduto().getCodProduto());
            p.setPreco(String.valueOf(cc.getPreco()));
            mDAO.atualizarQuantidadeProdutoNoCarrinho(p, mQuantidade);
        }
    }


}
