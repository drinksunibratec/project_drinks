package br.com.drinksapp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import br.com.drinksapp.R;
import br.com.drinksapp.SaveSharedPreference.MySaveSharedPreference;
import br.com.drinksapp.bean.Estabelecimento;
import br.com.drinksapp.bean.PedidoProdutos;
import br.com.drinksapp.bean.Produto;
import br.com.drinksapp.bean.Usuarios;
import br.com.drinksapp.db.DAODrinks;
import br.com.drinksapp.util.Constantes;

public class QuantidadeProdutosDialogFragment extends DialogFragment {

    View mLayout;

    NumberPicker mNumberPicker;

    Produto mProduto;

    DAODrinks mDAO;

    Estabelecimento mEstabelecimento;

    private static QuantidadeProdutosDialogFragment instancia = null;

    public static QuantidadeProdutosDialogFragment getInstancia(Produto produto) {
        if (instancia == null) {
            instancia = new QuantidadeProdutosDialogFragment();
            Bundle parametros = new Bundle();
            parametros.putParcelable(Constantes.EXTRA_PRODUTO, produto);
            parametros.putSerializable(Constantes.EXTRA_ESTABELECIMENTO, produto.getEstabelecimento());
            instancia.setArguments(parametros);
            return instancia;
        }
        return instancia;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mLayout = inflater.inflate(R.layout.fragment_dialog_quantidade_produtos, null);
        mNumberPicker = (NumberPicker) mLayout.findViewById(R.id.pickerQuantidade);
        mDAO = new DAODrinks(getActivity());

        mProduto = (Produto) getArguments().getParcelable(Constantes.EXTRA_PRODUTO);
        mEstabelecimento = (Estabelecimento)getArguments().getSerializable(Constantes.EXTRA_ESTABELECIMENTO);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(mLayout);
        builder.setPositiveButton(R.string.dialog_produtos_ok, new BotaoSalvar());
        builder.setNegativeButton(R.string.dialog_produtos_cancelar, new BotaoCancelar());

        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(100);
        mNumberPicker.setWrapSelectorWheel(true);

        int quantidadeAtual = mDAO.quantidadeDoProdutoNoCarrinho(mProduto);
        mNumberPicker.setValue(quantidadeAtual);

        AlertDialog alertDialog = builder.create();

        return alertDialog;

    }

    class BotaoSalvar implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            int quantidade = mNumberPicker.getValue();

            if(!mDAO.existeProdutoNoCarrinho(mProduto)){
//                PedidoProdutos pedidoProdutos = new PedidoProdutos();
//                pedidoProdutos.setCodProduto(mProduto.getCodProduto());
//                pedidoProdutos.setQuantidade(quantidade);
//                pedidoProdutos.setPreco(Double.parseDouble(mProduto.getPreco()));
//                pedidoProdutos.setCodEstabelcimento(mEstabelecimento.getCodEstabelecimento());
//                mDAO.insertProdutoNoCarrinho(pedidoProdutos);

            }else{

                mDAO.atualizarQuantidadeProdutoNoCarrinho(mProduto, quantidade);
            }

            dialogInterface.dismiss();
        }
    }

    class BotaoCancelar implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

}