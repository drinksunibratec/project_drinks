package br.com.drinksapp.http;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.Usuarios;

/**
 * Created by Silvio Cedrim on 14/11/2017.
 */

public class PedidosTask extends AsyncTaskLoader<List<Pedido>> {
    List<Pedido> mPedidos;

    Usuarios usuario;

    public PedidosTask(Context context, Usuarios usuario) {
        super(context);
        this.usuario = usuario;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if(mPedidos == null){
            forceLoad();
        }else{
            deliverResult(mPedidos);
        }
    }

    @Override
    public List<Pedido> loadInBackground() {
        mPedidos = DBConnectParser.listarPedidosdeCliente(usuario);
        return mPedidos;
    }

}
