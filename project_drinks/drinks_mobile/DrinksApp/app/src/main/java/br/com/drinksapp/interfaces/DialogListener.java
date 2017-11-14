package br.com.drinksapp.interfaces;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Silvio Cedrim on 12/11/2017.
 */

public interface DialogListener {
    void onDialogPositiveClick(DialogFragment dialog, Bundle parametros);
    void onDialogNegativeClick(DialogFragment dialog, Bundle parametros);

}
