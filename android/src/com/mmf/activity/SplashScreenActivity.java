package com.mmf.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.mmf.R;
import com.mmf.prefs.SettingsPrefs;
import com.mmf.rest.DataLoader;
import com.mmf.rest.exceptions.ServiceLayerException;
import com.mmf.util.DialogFragmentUtil;
import com.mmf.util.InternetConnectionUtil;
import com.mmf.util.Logger;
import org.apache.http.auth.InvalidCredentialsException;


/**
 * @author svetlana.voyteh
 * @date: 2/12/12
 */
public class SplashScreenActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        initData();
    }
    
    private void initData(){
        if(InternetConnectionUtil.hasInternetConnection(this)){
            new InitDataTask().execute();
        } else if (SettingsPrefs.IsDataLoaded.get()){
            startActivity(new Intent(SplashScreenActivity.this, OptionActivity.class));
            finish();
        } else {
            DialogFragmentUtil.showConnectionErrorDialog(SplashScreenActivity.this);
        }
    }


    private class InitDataTask extends AsyncTask<Object, Object, Boolean> {

        private String title;
        private String message;

        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                DataLoader.getInstance().init();
                SettingsPrefs.IsDataLoaded.put(true);
                return true;
            } catch (ServiceLayerException e) {
                Logger.getInstance().error(e);
                title = "Internet Connection Error";
                message = "Connection refused. There are problems with the server.";
            } catch (InvalidCredentialsException e) {
                Logger.getInstance().error(e);
                title = "Authorization Error";
                message = "Invalid credentials.";
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result){
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, OptionActivity.class));
                SplashScreenActivity.this.finish();
            } else if (SettingsPrefs.IsDataLoaded.get()){
                startActivity(new Intent(SplashScreenActivity.this, OptionActivity.class));
                finish();
            }else {
                DialogFragmentUtil.showErrorDialog(SplashScreenActivity.this, title, message);
            }
        }
    }

}
