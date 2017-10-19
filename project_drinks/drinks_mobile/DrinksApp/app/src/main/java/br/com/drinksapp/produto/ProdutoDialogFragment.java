package br.com.drinksapp.produto;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import br.com.drinksapp.R;

public class ProdutoDialogFragment extends DialogFragment
        implements TextView.OnEditorActionListener {
    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_PRODUTO = "produto";


    private EditText txtNome;
    private EditText txtEstabelecimento;
    private EditText txtEndereco;
    private EditText txtBairro;
    private EditText txtPreco;
    private Produto mProduto;

    public static ProdutoDialogFragment newInstance(Produto produto) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_PRODUTO, produto);
        ProdutoDialogFragment dialog = new ProdutoDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(
                R.layout.fragment_dialog_produto, container, false);

        mProduto = (Produto)getArguments().getSerializable(EXTRA_PRODUTO);
        txtNome = (EditText) layout.findViewById(R.id.txtNome);
        txtNome.requestFocus();
        txtEstabelecimento = (EditText)layout.findViewById(R.id.txtEstabelecimento);
        txtEstabelecimento.setOnEditorActionListener(this);
        txtEndereco = (EditText)layout.findViewById(R.id.txtEndereco);
        txtEndereco.setOnEditorActionListener(this);
        txtBairro = (EditText)layout.findViewById(R.id.txtBairro);
        txtBairro.setOnEditorActionListener(this);
        txtPreco = (EditText)layout.findViewById(R.id.txtPreco);
        txtPreco.setOnEditorActionListener(this);
        if (mProduto != null) {
            txtNome.setText(mProduto.nome);
            txtEstabelecimento.setText(mProduto.estabelecimento);
            txtEndereco.setText(mProduto.endereco);
            txtBairro.setText(mProduto.bairro);
            txtPreco.setText(mProduto.preco);
        }
        // Exibe o teclado virtual ao exibir o Dialog
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(R.string.acao_novo);
        return layout;
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            Activity activity = getActivity();
            if (activity instanceof AoSalvarProduto) {
                if (mProduto == null) {
                    mProduto = new Produto(
                            txtNome.getText().toString(),
                            txtEstabelecimento.getText().toString(),
                            txtEndereco.getText().toString(),
                            txtBairro.getText().toString(),
                            txtPreco.getText().toString());
                } else {
                    mProduto.nome = txtNome.getText().toString();
                    mProduto.estabelecimento = txtEstabelecimento.getText().toString();
                    mProduto.endereco = txtEndereco.getText().toString();
                    mProduto.bairro = txtBairro.getText().toString();
                    mProduto.preco = txtPreco.getText().toString();
                }
                AoSalvarProduto listener = (AoSalvarProduto) activity;
                listener.salvouProduto(mProduto);
                // Feche o dialog
                dismiss();
                return true;
            }
        }
        return false;
    }
    public void abrir(FragmentManager fm) {
        if (fm.findFragmentByTag(DIALOG_TAG) == null) {
            show(fm, DIALOG_TAG);
        }
    }
    public interface AoSalvarProduto {
        void salvouProduto(Produto produto);
    }
}
