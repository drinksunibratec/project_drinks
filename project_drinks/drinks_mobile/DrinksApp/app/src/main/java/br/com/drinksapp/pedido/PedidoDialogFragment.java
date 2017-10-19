package br.com.drinksapp.pedido;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import br.com.drinksapp.R;

public class PedidoDialogFragment extends DialogFragment
        implements TextView.OnEditorActionListener {
    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_PEDIDO = "pedido";


    private EditText txtNomepedido;
    private EditText txtValor;
    private EditText txtId_usuario;
    private EditText txtId_produto;
    private Pedido mPedido;

    public static PedidoDialogFragment newInstance(Pedido pedido) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_PEDIDO, pedido);
        PedidoDialogFragment dialog = new PedidoDialogFragment();
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
                R.layout.fragment_dialog_pedido, container, false);

        mPedido = (Pedido)getArguments().getSerializable(EXTRA_PEDIDO);
        txtNomepedido = (EditText) layout.findViewById(R.id.txtNomepedido);
        txtNomepedido.requestFocus();
        txtValor = (EditText)layout.findViewById(R.id.txtValor);
        txtValor.setOnEditorActionListener(this);
        txtId_usuario = (EditText)layout.findViewById(R.id.txtId_usuario);
        txtId_usuario.setOnEditorActionListener(this);
        txtId_produto = (EditText)layout.findViewById(R.id.txtId_produto);
        txtId_produto.setOnEditorActionListener(this);

        if (mPedido != null) {
            txtNomepedido.setText(mPedido.nomepedido);
            txtValor.setText(mPedido.valor);
            txtId_usuario.setText(mPedido.id_usuario);
            txtId_produto.setText(mPedido.id_produto);
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
            if (activity instanceof PedidoDialogFragment.AoSalvarPedido) {
                if (mPedido == null) {
                    mPedido = new Pedido(
                            txtNomepedido.getText().toString(),
                            txtValor.getText().toString(),
                            txtId_usuario.getText().toString(),
                            txtId_produto.getText().toString());
                } else {
                    mPedido.nomepedido = txtNomepedido.getText().toString();
                    mPedido.valor = txtValor.getText().toString();
                    mPedido.id_usuario = txtId_usuario.getText().toString();
                    mPedido.id_produto = txtId_produto.getText().toString();
                }
                PedidoDialogFragment.AoSalvarPedido listener = (PedidoDialogFragment.AoSalvarPedido) activity;
                listener.salvouPedido(mPedido);
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
    public interface AoSalvarPedido {
        void salvouPedido(Pedido pedido);
    }
}

