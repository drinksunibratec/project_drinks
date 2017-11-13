package br.com.drinksapp.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.drinksapp.R;
import br.com.drinksapp.interfaces.DialogListener;
import br.com.drinksapp.util.Constantes;

public class ComplementoEnderecoDialogFragment extends DialogFragment {

    View mLayout;

    EditText mEdtNumero;

    EditText mEdtComplemento;

    DialogListener mListener;

    Button mBtnComplemento;

    private static ComplementoEnderecoDialogFragment instancia = null;

    public static ComplementoEnderecoDialogFragment getInstancia() {
        if (instancia == null) {
            instancia = new ComplementoEnderecoDialogFragment();
            return instancia;
        }
        return instancia;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mLayout = inflater.inflate(R.layout.dialog_fragment_numero_complemento, null);

        mEdtNumero = (EditText) mLayout.findViewById(R.id.edt_numero);
        mEdtComplemento = (EditText) mLayout.findViewById(R.id.edt_complemento);
        mBtnComplemento = (Button)mLayout.findViewById(R.id.btnDialogComplementos);
        mBtnComplemento.setOnClickListener(new BotaoSalvar());
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(mLayout);

        AlertDialog alertDialog = builder.create();
        return alertDialog;

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    class BotaoSalvar implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int numero = Integer.parseInt( mEdtNumero.getText().toString());
            String complento = mEdtComplemento.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putInt(Constantes.EXTRA_NUMERO, numero);
            bundle.putString(Constantes.EXTRA_COMPLEMENTO, complento);

            mListener.onDialogPositiveClick(ComplementoEnderecoDialogFragment.this, bundle);

        }
    }



}