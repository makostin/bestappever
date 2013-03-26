package com.mmf.fragment.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * svetlana.voyteh
 * 26.03.13
 */
public class ProgressDialogFragment extends DialogFragment{

    public static final String PROGRESS_DIALOG_TAG = "progress_dialog";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading data");
        dialog.setMessage("Please. wait...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        setCancelable(false);
        return dialog;

    }
}
