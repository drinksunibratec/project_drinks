package br.com.drinksapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.Estabelecimento;

/**
 * Created by Silvio Cedrim on 19/11/2017.
 */

public class EstabelecimentosFavoritosAdapter extends ArrayAdapter<Estabelecimento> {

    public EstabelecimentosFavoritosAdapter(Context context, List<Estabelecimento> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Estabelecimento estabelecimento = getItem(position);

        ViewHolder holder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_estabelecimento, null);
            holder = new ViewHolder();
            holder.txtNomeEstabelecimentoFavorito = (TextView)convertView.findViewById(R.id.txtNomeEstabelecimentoFavorito);
            holder.txtEnderecoEstabelecimentoFavorito = (TextView)convertView.findViewById(R.id.txtEnderecoEstabelecimentoFavorito);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txtNomeEstabelecimentoFavorito.setText(estabelecimento.getNomeFantasia());
        holder.txtEnderecoEstabelecimentoFavorito.setText(estabelecimento.getRua() + ", " + estabelecimento.getNumero() + " - " + estabelecimento.getCidade() + " - " + estabelecimento.getUf());
        return convertView;
    }

    static class ViewHolder{
        TextView txtNomeEstabelecimentoFavorito;
        TextView txtEnderecoEstabelecimentoFavorito;
    }
}
