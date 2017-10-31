package br.com.drinksapp.adapter;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.activity.ListaProdutosActivity;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.fragment.QuantidadeProdutosDialogFragment;

/**
 * Created by Silvio Cedrim on 25/10/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto> {
    Context context;
    public ProdutoAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_produto, null);
            holder = new ViewHolder();
            holder.txtNomeProduto = (TextView) convertView.findViewById(R.id.txtNomeProdutoLista);
            holder.txtPrecoProduto = (TextView) convertView.findViewById(R.id.txtPrecoProdutoLista);
            holder.txtDescricaoProdutoLista = (TextView) convertView.findViewById(R.id.txtDescricaoProdutoLista);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNomeProduto.setText(produto.getNome());
        holder.txtPrecoProduto.setText("R$ " + produto.getPreco());
        holder.txtDescricaoProdutoLista.setText(produto.getDescricao());

        return convertView;
    }


    static class ViewHolder {
        TextView txtNomeProduto;
        TextView txtPrecoProduto;
        TextView txtDescricaoProdutoLista;
    }


}
