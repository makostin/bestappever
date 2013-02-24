package com.mmf.rest.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * User: svetlana.voyteh
 * Date: 24.02.13
 */
public abstract class LoadDataTask extends AsyncTask{

    private ProgressDialog dialog;
    private Context context;

    public LoadDataTask(Context context){
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading data. Please. wait...");
    }


    @Override
    protected void onPostExecute(Object o) {
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
