package br.com.drinksapp.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.drinksapp.R;
import br.com.drinksapp.interfaces.DialogBandeiraCartaoListener;
import br.com.drinksapp.interfaces.DialogComplementosEnderecoListener;
import br.com.drinksapp.util.Constantes;

public class BandeiraCartaoDialogFragment extends DialogFragment {

    View mLayout;

    DialogBandeiraCartaoListener mListener;

    RadioGroup mRadioBandeira;



    private static BandeiraCartaoDialogFragment instancia = null;

    public static BandeiraCartaoDialogFragment getInstancia() {
        if (instancia == null) {
            instancia = new BandeiraCartaoDialogFragment();
            return instancia;
        }
        return instancia;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mLayout = inflater.inflate(R.layout.dialog_fragment_bandeira_cartao, null);

        mRadioBandeira = (RadioGroup) mLayout.findViewById(R.id.radio_bandeira_cartao);
        mRadioBandeira.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                String bandeira =  button.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putInt(Constantes.EXTRA_ID_BANDEIRA, checkedId);
                bundle.putString(Constantes.EXTRA_BANDEIRA, bandeira);


                mListener.onChancheRadioButton(BandeiraCartaoDialogFragment.this, bundle);

            }
        });
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
            mListener = (DialogBandeiraCartaoListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }






}