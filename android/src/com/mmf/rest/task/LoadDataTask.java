package com.mmf.rest.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import com.mmf.util.DialogFragmentUtil;

/**
 * User: svetlana.voyteh
 * Date: 24.02.13
 */
public abstract class LoadDataTask extends AsyncTask{

    private FragmentActivity activity;

    public LoadDataTask(FragmentActivity activity){
        this.activity = activity;

    }

    @Override
    protected void onPreExecute() {
        DialogFragmentUtil.showProgressDialogFragment(activity);
    }


    @Override
    protected void onPostExecute(Object o) {
        DialogFragmentUtil.dismissProgressDialogFragment(activity);
    }
}
