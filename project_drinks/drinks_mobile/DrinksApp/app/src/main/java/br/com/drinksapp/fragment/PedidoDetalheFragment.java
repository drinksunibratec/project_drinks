package br.com.drinksapp.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.adapter.PedidoProdutosAdapter;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.util.Constantes;

public class PedidoDetalheFragment extends Fragment {

    TextView mTxtNomeEstabelecimentoDetalhe;

    TextView mTxtDataPedidoDetalhe;

    TextView mTxtValorTotalDetalhe;

    TextView mTxtStatusDetalhe;

    Pedido mPedido;

    List<PedidoProdutos> mProdutos;

    ListView mListProdutos;

    public PedidoDetalheFragment() {
    }

    public static PedidoDetalheFragment novaInstancia(Pedido pedido) {
        PedidoDetalheFragment fragment = new PedidoDetalheFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constantes.EXTRA_PEDIDO, pedido);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPedido = (Pedido)getArguments().getSerializable(Constantes.EXTRA_PEDIDO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout =  inflater.inflate(R.layout.fragment_pedido_detalhe, container, false);

        if(mPedido != null){

            mTxtNomeEstabelecimentoDetalhe = (TextView)layout.findViewById(R.id.txtNomeEstabelecimentoDetalhe);
            mTxtDataPedidoDetalhe = (TextView)layout.findViewById(R.id.txtDataPedidoDetalhe);
            mTxtValorTotalDetalhe = (TextView)layout.findViewById(R.id.txtValorTotalDetalhe);
            mTxtStatusDetalhe = (TextView)layout.findViewById(R.id.txtStatusDetalhe);

            mTxtNomeEstabelecimentoDetalhe.setText(getString(R.string.estabelecimento) + ": " + mPedido.getEstabelecimento().getNomeFantasia());
            mTxtDataPedidoDetalhe.setText(getString(R.string.data_pedido) + ": "  + mPedido.getDataPedido());
            mTxtValorTotalDetalhe.setText(getString(R.string.valor_total_pedido) + ": R$ "  + mPedido.getValorTotal());
            mTxtStatusDetalhe.setText(getString(R.string.status) + ":"  + mPedido.getStatus());

            mProdutos = mPedido.getPedidoProdutos();

            mListProdutos = (ListView)layout.findViewById(R.id.lista_produto_detalhe_pedidos);
            mListProdutos.setAdapter(new PedidoProdutosAdapter(getActivity(), mProdutos));


        }

        return layout;
    }
}
