package br.com.drinksapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import br.com.drinksapp.R;
import br.com.drinksapp.activity.RegisterActivity;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.util.Constantes;


public class ProdutoDetalheFragment extends Fragment {
    public static final String TAG_DETALHE = "tagDetalhe";
    TextView mTextDescricao;
    TextView mTextNome;
    TextView mTextPreco;
    TextView mTextPrecoTotal;
    TextView mTextQuantidadeProdutos;
    TextView mTxtNomeProdutoToolbar;
    Produto mProduto;
    int mQuantidade;
    ImageButton mBotaoMais;
    ImageButton mBotaoMenos;
    DAODrinks mDAO;
    private Estabelecimento mEstabelecimento;
    Button mBtnAdicionarAoCarrinho;


    public static ProdutoDetalheFragment novaInstancia(Produto produto, Estabelecimento estabelecimento) {
        Bundle parametros = new Bundle();
        parametros.putParcelable(Constantes.EXTRA_PRODUTO, produto);
        parametros.putSerializable(Constantes.EXTRA_ESTABELECIMENTO, estabelecimento);
        ProdutoDetalheFragment fragment = new ProdutoDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduto = (Produto) getArguments().getParcelable(Constantes.EXTRA_PRODUTO);
        mEstabelecimento = (Estabelecimento) getArguments().getSerializable(Constantes.EXTRA_ESTABELECIMENTO);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(
                R.layout.fragment_detalhe_produto, container, false);
        mTextDescricao = (TextView) layout.findViewById(R.id.txtDescricaoProdutoDetalhe);
        mTextNome = (TextView) layout.findViewById(R.id.txtNomeProdutoDetalhe);
        mTextPreco = (TextView) layout.findViewById(R.id.txtPrecoUnitarioProduto);
        mTextPrecoTotal = (TextView) layout.findViewById(R.id.txt_valor_total);
        mTextQuantidadeProdutos = (TextView) layout.findViewById(R.id.txt_quantidade_produtos);
        mTxtNomeProdutoToolbar = (TextView) layout.findViewById(R.id.txt_nome_produto_toolbar);
        mBotaoMais = (ImageButton) layout.findViewById(R.id.btn_maisProdutos);
        mBotaoMenos = (ImageButton) layout.findViewById(R.id.btn_menosProdutos);
        mBtnAdicionarAoCarrinho = (Button) layout.findViewById(R.id.btn_add_carrinho);

        mBotaoMais.setOnClickListener(new CliqueBotaoMais());
        mBotaoMenos.setOnClickListener(new CliqueBotaoMenos());
        mBtnAdicionarAoCarrinho.setOnClickListener(new BotaoAdicionarAoCarrinho(getActivity()));

        Toolbar toolbar = (Toolbar) layout.findViewById(R.id.toolbar_detalhe_produto);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDAO = new DAODrinks(getActivity());

        if (mProduto != null) {

            mQuantidade = mDAO.quantidadeDoProdutoNoCarrinho(mProduto);
            mQuantidade = Integer.parseInt(mTextQuantidadeProdutos.getText().toString());

            mTextDescricao.setText(mProduto.descricao);
            mTextNome.setText(mProduto.nome);
            mTxtNomeProdutoToolbar.setText(mProduto.nome);
            mTextPreco.setText(mTextPreco.getText() + ": R$ " + mProduto.getPreco());

            if (mQuantidade != 0) {
                mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
                mTextPrecoTotal.setText(getString(R.string.txt_total_pedido) + ": R$ " + (mQuantidade * Double.parseDouble(mProduto.getPreco())));
            }
        }
        return layout;
    }

    class CliqueBotaoMais implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mQuantidade++;
            mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
            mTextPrecoTotal.setText(getString(R.string.txt_total_pedido) + ": R$ " + (mQuantidade * Double.parseDouble(mProduto.getPreco())));
        }
    }

    class CliqueBotaoMenos implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mQuantidade--;
            mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
            mTextPrecoTotal.setText(getString(R.string.txt_total_pedido) + ": R$ " + (mQuantidade * Double.parseDouble(mProduto.getPreco())));
        }
    }

    class BotaoAdicionarAoCarrinho implements View.OnClickListener {

        Context context;
        public BotaoAdicionarAoCarrinho(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {

            if (!mDAO.existeProdutoNoCarrinho(mProduto)) {
                PedidoProdutos pedidoProdutos = new PedidoProdutos();
                pedidoProdutos.setCodProduto(mProduto.getCodProduto());
                pedidoProdutos.setQuantidade(mQuantidade);
                pedidoProdutos.setPreco(Double.parseDouble(mProduto.getPreco()));
                pedidoProdutos.setCodEstabelcimento(mEstabelecimento.getCodEstabelecimento());
                mDAO.insertProdutoNoCarrinho(pedidoProdutos);
            } else {
                mDAO.atualizarQuantidadeProdutoNoCarrinho(mProduto, mQuantidade);
            }
            Toast.makeText(context, "Produto adicionado ao carrinho com sucesso!", Toast.LENGTH_LONG).show();
            getActivity().getFragmentManager().popBackStack();
        }
    }
}
