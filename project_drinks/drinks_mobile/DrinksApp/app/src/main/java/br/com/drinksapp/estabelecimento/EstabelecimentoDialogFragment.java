package br.com.drinksapp.estabelecimento;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import br.com.drinksapp.R;

public class EstabelecimentoDialogFragment extends DialogFragment
        implements TextView.OnEditorActionListener {
    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_ESTABELECIMENTO = "estabelecimento";


    private EditText txtNome;
    private EditText txtLogradouro;
    private EditText txtNumero;
    private EditText txtBairro;
    private EditText txtCidade;
    private EditText txtUf;
    private EditText txtCep;
    private EditText txtLatitude;
    private EditText txtLongetude;
    private Estabelecimento mEstabelecimento;

    public static EstabelecimentoDialogFragment newInstance(Estabelecimento estabelecimento) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ESTABELECIMENTO, estabelecimento);
        EstabelecimentoDialogFragment dialog = new EstabelecimentoDialogFragment();
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
                R.layout.fragment_dialog_estabelecimento, container, false);

        mEstabelecimento = (Estabelecimento)getArguments().getSerializable(EXTRA_ESTABELECIMENTO);
        txtNome = (EditText) layout.findViewById(R.id.txtNome);
        txtNome.requestFocus();
        txtLogradouro = (EditText)layout.findViewById(R.id.txtLogradouro);
        txtLogradouro.setOnEditorActionListener(this);
        txtNumero = (EditText)layout.findViewById(R.id.txtNumero);
        txtNumero.setOnEditorActionListener(this);
        txtBairro = (EditText)layout.findViewById(R.id.txtBairro);
        txtBairro.setOnEditorActionListener(this);
        txtCidade = (EditText)layout.findViewById(R.id.txtCidade);
        txtCidade.setOnEditorActionListener(this);
        txtUf = (EditText)layout.findViewById(R.id.txtUf);
        txtUf.setOnEditorActionListener(this);
        txtCep = (EditText)layout.findViewById(R.id.txtCep);
        txtCep.setOnEditorActionListener(this);
        txtLatitude = (EditText)layout.findViewById(R.id.txtLatitude);
        txtLatitude.setOnEditorActionListener(this);
        txtLongetude = (EditText)layout.findViewById(R.id.txtLongetude);
        txtLongetude.setOnEditorActionListener(this);
        if (mEstabelecimento != null) {
            txtNome.setText(mEstabelecimento.nome);
            txtLogradouro.setText(mEstabelecimento.logradouro);
            txtNumero.setText(mEstabelecimento.numero);
            txtBairro.setText(mEstabelecimento.bairro);
            txtCidade.setText(mEstabelecimento.cidade);
            txtUf.setText(mEstabelecimento.uf);
            txtCep.setText(mEstabelecimento.cep);
            txtLatitude.setText(mEstabelecimento.latitude);
            txtLongetude.setText(mEstabelecimento.longetude);
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
            if (activity instanceof AoSalvarEstabelecimento) {
                if (mEstabelecimento == null) {
                    mEstabelecimento = new Estabelecimento(
                            txtNome.getText().toString(),
                            txtLogradouro.getText().toString(),
                            txtNumero.getText().toString(),
                            txtBairro.getText().toString(),
                            txtCidade.getText().toString(),
                            txtUf.getText().toString(),
                            txtCep.getText().toString(),
                            txtLatitude.getText().toString(),
                            txtLongetude.getText().toString());
                } else {
                    mEstabelecimento.nome = txtNome.getText().toString();
                    mEstabelecimento.logradouro = txtLogradouro.getText().toString();
                    mEstabelecimento.numero = txtNumero.getText().toString();
                    mEstabelecimento.bairro = txtBairro.getText().toString();
                    mEstabelecimento.cidade = txtCidade.getText().toString();
                    mEstabelecimento.uf = txtUf.getText().toString();
                    mEstabelecimento.cep = txtCep.getText().toString();
                    mEstabelecimento.latitude = txtLatitude.getText().toString();
                    mEstabelecimento.longetude = txtLongetude.getText().toString();
                }
                AoSalvarEstabelecimento listener = (AoSalvarEstabelecimento) activity;
                listener.salvouEstabelecimento(mEstabelecimento);
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
    public interface AoSalvarEstabelecimento {
        void salvouEstabelecimento(Estabelecimento estabelecimento );
    }
}
