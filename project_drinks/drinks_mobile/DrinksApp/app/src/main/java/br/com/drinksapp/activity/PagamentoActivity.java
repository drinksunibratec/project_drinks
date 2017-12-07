package br.com.drinksapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.bean.Endereco;
import br.com.drinksapp.bean.ItemCarrinhoCompras;
import br.com.drinksapp.bean.Pedido;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.fragment.BandeiraCartaoDialogFragment;
import br.com.drinksapp.fragment.ComplementoEnderecoDialogFragment;
import br.com.drinksapp.http.DBConnectParser;
import br.com.drinksapp.interfaces.DialogBandeiraCartaoListener;
import br.com.drinksapp.util.Mask;
import br.com.drinksapp.http.ConnectCEP;
import br.com.drinksapp.interfaces.DialogComplementosEnderecoListener;
import br.com.drinksapp.util.Constantes;
import br.com.drinksapp.util.Util;
import br.com.drinksapp.util.Validator;

public class PagamentoActivity extends AppCompatActivity implements DialogComplementosEnderecoListener, DialogBandeiraCartaoListener {

    List<ItemCarrinhoCompras> mCarrinho;

    Double valorTotalPedido;

    private ProgressDialog pDialog;

    Button mBtnCEP;

    EditText mEdtCep;

    TextView mTxtLogradouro;

    TextView mTxtBairro;

    TextView mTxtComplemento;

    TextView mTxtBandeira;

    int mNumero;

    String mPagamento;

    String mComplemento;

    String mLogradouro;

    String mBairro;

    String mCidade;

    String mUf;

    DAODrinks mDAO;

    Button mBtnRealizarPedido;

    String mCEP;

    String mBandeira;

    RadioGroup mRadioPagamento;

    ImageView mImagemBandeira;

    RelativeLayout mLayoutBandeira;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        Bundle bundle = (Bundle)getIntent().getExtras().get(Constantes.EXTRA_BUNDLE);
        mCarrinho = (List<ItemCarrinhoCompras>)bundle.getSerializable(Constantes.EXTRA_CARRINHO_COMPRAS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPagamento);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        valorTotalPedido = new Double(0);
        for(ItemCarrinhoCompras cc : mCarrinho){
            valorTotalPedido += cc.getPrecoTotal();
        }

        pDialog = new ProgressDialog(this, R.style.MyDialogTheme);
        pDialog.setCancelable(false);

        mDAO  = new DAODrinks(this);

        mLayoutBandeira = (RelativeLayout)findViewById(R.id.layout_bandeira_cartao);
        mLayoutBandeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BandeiraCartaoDialogFragment dialogFragment = BandeiraCartaoDialogFragment.getInstancia();
                dialogFragment.show(getSupportFragmentManager(), Constantes.DIALOG_FRAGMENT);
            }
        });


        mEdtCep = (EditText)findViewById(R.id.edt_cep);
        mEdtCep.addTextChangedListener(Mask.insert("#####-###", mEdtCep));
        mEdtCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mBtnCEP.setEnabled(true);
                mBtnCEP.setBackgroundResource(R.drawable.click_botao);
            }
        });

        mTxtLogradouro = (TextView) findViewById(R.id.txtLogradouro);
        mTxtComplemento = (TextView)findViewById(R.id.txtComplemento);
        mTxtBairro = (TextView) findViewById(R.id.txtBairro);
        mBtnCEP = (Button)findViewById(R.id.btnConsultarEndreco);
        mBtnCEP.setOnClickListener(new BotaoCEP());
        mBtnCEP.setEnabled(false);

        mTxtBandeira = (TextView)findViewById(R.id.txtBandeira);
        mImagemBandeira = (ImageView)findViewById(R.id.imagemBandeira);

        mBtnRealizarPedido = (Button) findViewById(R.id.btnRealizarPedido);
        mBtnRealizarPedido.setOnClickListener(new BotaoRealizarPedido());

        mRadioPagamento = (RadioGroup)findViewById(R.id.radio_pagamento);
        mRadioPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                mPagamento = button.getText().toString();

                if(checkedId == R.id.radio_cartao_debito){
                    BandeiraCartaoDialogFragment dialogFragment = BandeiraCartaoDialogFragment.getInstancia();
                    dialogFragment.show(getSupportFragmentManager(), Constantes.DIALOG_FRAGMENT);
                }
            }
        });

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pDialog.dismiss();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Bundle parametros) {
        mNumero = parametros.getInt(Constantes.EXTRA_NUMERO);
        mComplemento = parametros.getString(Constantes.EXTRA_COMPLEMENTO);

        if(mComplemento != null && !mComplemento.equals("")){
            mTxtLogradouro.setText(mLogradouro);
            mTxtComplemento.setText(mNumero + " - "  + mComplemento);
        }else{
            mTxtLogradouro.setText(mLogradouro + ", " + mNumero);
        }
        mTxtBairro.setText(mBairro + " - " + mCidade + " - " + mUf);

        Fragment prev = getSupportFragmentManager().findFragmentByTag(Constantes.DIALOG_FRAGMENT);
        if (prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }
        hideDialog();

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, Bundle parametros) {

    }

    @Override
    public void onChancheRadioButton(DialogFragment dialog, Bundle parametros) {

        int id = parametros.getInt(Constantes.EXTRA_ID_BANDEIRA);
        mBandeira = parametros.getString(Constantes.EXTRA_BANDEIRA);
        mTxtBandeira.setText(getString(R.string.bandeira) + ": " + mBandeira);

        switch (id){
            case R.id.radio_mastercard:
                mImagemBandeira.setImageResource(R.drawable.ic_mastercard);
                break;
            case R.id.radio_visa:
                mImagemBandeira.setImageResource(R.drawable.ic_visa);
                break;
        }

        Fragment prev = getSupportFragmentManager().findFragmentByTag(Constantes.DIALOG_FRAGMENT);
        if (prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }


    }


    private class TaskBuscarEndereco extends AsyncTask<String, Void, Endereco>{

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Consultando endereço ... Aguarde!");
            showDialog();
        }
        @Override
        protected Endereco doInBackground(String... params) {
            Endereco endereco = null;
            try {
                mLogradouro = "";
                mBairro = "";
                mNumero = 0;
                mCidade = "";
                mUf = "";
                mComplemento = "";
                endereco = ConnectCEP.buscarEndereco(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return endereco;
        }

        @Override
        protected void onPostExecute(Endereco endereco) {

            if(endereco != null && endereco.getEndereco() != null && !endereco.getEndereco().equals("")){

                mLogradouro = endereco.getEndereco();
                mBairro = endereco.getBairro();
                mCidade = endereco.getCidade();
                mUf = endereco.getUf();

                hideDialog();

                ComplementoEnderecoDialogFragment dialogFragment = ComplementoEnderecoDialogFragment.getInstancia();
                dialogFragment.show(getSupportFragmentManager(), Constantes.DIALOG_FRAGMENT);

            }else{
                mEdtCep.setError("CEP inválido");
                mEdtCep.setFocusable(true);
                mEdtCep.requestFocus();
                Toast.makeText(PagamentoActivity.this, "Endereço não encontrado. Verifique o CEP e tente novamente!", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }
    }

    private void initAsyncTask(String cep){
        TaskBuscarEndereco taskCadastrarCarrinho = new TaskBuscarEndereco();
        taskCadastrarCarrinho.execute(cep);
    }


    private class TaskCadastrarCarrinho extends AsyncTask<List<ItemCarrinhoCompras>, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            pDialog.setMessage("Criando pedido ... Aguarde!");
            showDialog();
        }

        @Override
        protected Boolean doInBackground(List<ItemCarrinhoCompras>... params) {

            
            ItemCarrinhoCompras carrinhoCompras = params[0].get(0);

            Pedido pedido = new Pedido();
            pedido.setCodEstabelecimento(carrinhoCompras.getCodEstabelcimento());
            pedido.setCodUsuario(MySaveSharedPreference.getUserId(getApplicationContext()));
            pedido.setValorTotal(valorTotalPedido);
            pedido.setDataPedido(Util.getDataAtualAmericana());
            pedido.setStatus(getString(R.string.status_aguardando));
            pedido.setRua(mLogradouro);
            pedido.setNumero(mNumero);
            pedido.setBairro(mBairro);
            pedido.setCidade(mCidade);
            pedido.setUf(mUf);
            pedido.setComplemento(mComplemento);
            pedido.setNumero(mNumero);
            pedido.setCEP(mCEP);
            pedido.setPagamento(mPagamento);
            pedido.setDataHoraPedido(Util.getDataAtual());

            if(mPagamento.equals(getString(R.string.cartao_debito))){
                pedido.setBandeiraCartao(mBandeira);
            }

            Pedido retorno = DBConnectParser.inserirPedido(pedido);
            List<PedidoProdutos> produtos = new ArrayList<PedidoProdutos>();
            List<ItemCarrinhoCompras> carrinhoTemp = params[0];

            for(int i = 0; i < carrinhoTemp.size(); i++){
                PedidoProdutos p = new PedidoProdutos();
                p.setCodPedido(retorno.getCodPedido());
                p.setCodProduto(carrinhoTemp.get(i).getProduto().getCodProduto());
                p.setPreco(carrinhoTemp.get(i).getPreco());
                p.setPrecoTotal(carrinhoTemp.get(i).getPrecoTotal());
                p.setQuantidade(carrinhoTemp.get(i).getQuantidade());
                produtos.add(p);
            }

            boolean inseriu = DBConnectParser.inserirProdutosPedido(produtos);

            return inseriu;
        }

        @Override
        protected void onPostExecute(Boolean inseriu) {
            if(inseriu){
                Toast.makeText(getApplicationContext(), "Seu pedido foi gerado com sucesso!", Toast.LENGTH_LONG).show();
                mDAO.deleteCarrinhoCompras();
                Intent it = new Intent(PagamentoActivity.this, MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
                finish();
            }


        }
    }


    private void initAsyncTask(List<ItemCarrinhoCompras> carrinhoComprases){
        TaskCadastrarCarrinho taskCadastrarCarrinho = new TaskCadastrarCarrinho();
        taskCadastrarCarrinho.execute(carrinhoComprases);
    }


    class BotaoCEP implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            Validator.validateNotNull(mEdtCep, "Preencha o campo CEP");

            mCEP = mEdtCep.getText().toString();

            initAsyncTask(mCEP);
        }
    }

    class BotaoRealizarPedido implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            initAsyncTask(mCarrinho);

        }

    }
}
