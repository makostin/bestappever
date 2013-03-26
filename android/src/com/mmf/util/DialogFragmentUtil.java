package com.mmf.util;

import android.support.v4.app.FragmentActivity;
import com.mmf.fragment.dialog.ConnectionErrorDialog;
import com.mmf.fragment.dialog.ErrorDialogFragment;
import com.mmf.fragment.dialog.ProgressDialogFragment;

/**
 * svetlana.voyteh
 * 26.03.13
 */
public class DialogFragmentUtil {

    public static void showConnectionErrorDialog(FragmentActivity activity){
        ConnectionErrorDialog errorDialog = new ConnectionErrorDialog("Internet Connection Error", "Network is unreachable. Do you want establish internet connection now?");
        errorDialog.show(activity.getSupportFragmentManager(), ConnectionErrorDialog.CONNECTION_ERROR_DIALOG_TAG);
    }

    public static void showErrorDialog(FragmentActivity activity, String title, String message){
        ErrorDialogFragment errorDialog = new ErrorDialogFragment(title, message);
        errorDialog.show(activity.getSupportFragmentManager(), ErrorDialogFragment.ERROR_DIALOG_TAG);
    }

    public static void showProgressDialogFragment(FragmentActivity activity) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.show(activity.getSupportFragmentManager(), ProgressDialogFragment.PROGRESS_DIALOG_TAG);
    }

    public static void dismissProgressDialogFragment(FragmentActivity activity) {
        ProgressDialogFragment prev = (ProgressDialogFragment) activity.getSupportFragmentManager().findFragmentByTag(ProgressDialogFragment.PROGRESS_DIALOG_TAG);

        if (prev != null) {
            prev.dismiss();
        }
    }
}
