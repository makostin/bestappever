package com.mmf.fragment.dialog;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.mmf.util.InternetConnectionUtil;

/**
 * svetlana.voyteh
 * 26.03.13
 */
public class ConnectionErrorDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public static final String CONNECTION_ERROR_DIALOG_TAG = "ConnectionErrorDialog";

    private String title;
    private String message;

    public ConnectionErrorDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .setMessage(message)
                .setCancelable(false);
        setCancelable(false);
        return dialogBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case Dialog.BUTTON_POSITIVE:
                InternetConnectionUtil.showInternetConnectionSettings(getActivity());
                getActivity().finish();
                break;
            case Dialog.BUTTON_NEGATIVE:
                getActivity().finish();
                break;
        }
    }

}
