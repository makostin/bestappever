package com.mmf.fragment.dialog;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * svetlana.voyteh
 * 26.03.13
 */
public class ErrorDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String ERROR_DIALOG_TAG = "ErrorDialog";

    private String title;
    private String message;

    public ErrorDialogFragment(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setPositiveButton(R.string.yes, this)
                .setMessage(message)
                .setCancelable(false);
        setCancelable(false);
        return dialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case Dialog.BUTTON_POSITIVE:
                getActivity().finish();
                break;
        }
    }
}
