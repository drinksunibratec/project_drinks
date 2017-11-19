package br.com.drinksapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.drinksapp.R;
import br.com.drinksapp.bean.ItemCarrinhoCompras;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.util.Constantes;

public class ProdutoDetalheActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);

        Intent it = getIntent();
        Bundle bundle = (Bundle)getIntent().getExtras().get(Constantes.EXTRA_BUNDLE);
        mProduto = (Produto)bundle.getSerializable(Constantes.EXTRA_PRODUTO);
        mEstabelecimento = (Estabelecimento) it.getSerializableExtra(Constantes.EXTRA_ESTABELECIMENTO);

        mTextDescricao = (TextView) findViewById(R.id.txtDescricaoProdutoDetalhe);
        mTextNome = (TextView) findViewById(R.id.txtNomeProdutoDetalhe);
        mTextPreco = (TextView) findViewById(R.id.txtPrecoUnitarioProduto);
        mTextPrecoTotal = (TextView) findViewById(R.id.txt_valor_total);
        mTextQuantidadeProdutos = (TextView) findViewById(R.id.txt_quantidade_produtos);
        mTxtNomeProdutoToolbar = (TextView) findViewById(R.id.txt_nome_produto_toolbar);
        mBotaoMais = (ImageButton) findViewById(R.id.btn_maisProdutos);
        mBotaoMenos = (ImageButton) findViewById(R.id.btn_menosProdutos);
        mBtnAdicionarAoCarrinho = (Button) findViewById(R.id.btn_add_carrinho);

        mBotaoMais.setOnClickListener(new CliqueBotaoMais());
        mBotaoMenos.setOnClickListener(new CliqueBotaoMenos());
        mBtnAdicionarAoCarrinho.setOnClickListener(new BotaoAdicionarAoCarrinho(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalhe_produto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDAO = new DAODrinks(this);

        if (mProduto != null) {

            mQuantidade = mDAO.quantidadeDoProdutoNoCarrinho(mProduto);

            if(mQuantidade == 0){
                mQuantidade = Integer.parseInt(mTextQuantidadeProdutos.getText().toString());
            }

            mTextDescricao.setText(mProduto.descricao);
            mTextNome.setText(mProduto.nome);
            mTxtNomeProdutoToolbar.setText(mProduto.nome);
            mTextPreco.setText(mTextPreco.getText() + ": R$ " + mProduto.getPreco());

            if (mQuantidade != 0) {
                mTextQuantidadeProdutos.setText(String.valueOf(mQuantidade));
                mTextPrecoTotal.setText(getString(R.string.txt_total_pedido) + ": R$ " + (mQuantidade * Double.parseDouble(mProduto.getPreco())));
            }
        }
    }

    @Override
    public void onBackPressed() {
        getIntent().putExtra(Constantes.USUARIO_CADASTRADO, mEstabelecimento);
        setResult(Constantes.RESULT_BACK);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getIntent().putExtra(Constantes.USUARIO_CADASTRADO, mEstabelecimento);
                setResult(Constantes.RESULT_BACK);
                finish();
                break;
            default:break;
        }
        return true;
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
            if(mQuantidade > 1){
                mQuantidade--;
            }
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
                ItemCarrinhoCompras carrinhoCompras = new ItemCarrinhoCompras();
                carrinhoCompras.setCodProduto(mProduto.getCodProduto());
                carrinhoCompras.setQuantidade(mQuantidade);
                carrinhoCompras.setPreco(Double.parseDouble(mProduto.getPreco()));
                carrinhoCompras.setCodEstabelcimento(mEstabelecimento.getCodEstabelecimento());
                mDAO.insertProdutoNoCarrinho(carrinhoCompras);
            } else {
                mDAO.atualizarQuantidadeProdutoNoCarrinho(mProduto, mQuantidade);
            }
            Toast.makeText(context, "Produto adicionado ao carrinho com sucesso!", Toast.LENGTH_LONG).show();

            getIntent().putExtra(Constantes.USUARIO_CADASTRADO, mEstabelecimento);
            setResult(Constantes.RESULT_BACK);
            finish();
        }

    }
}
