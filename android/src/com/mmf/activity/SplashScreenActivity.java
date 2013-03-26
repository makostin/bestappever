package com.mmf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

    public static final int SPLASH_DISPLAY_TIME = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        initData();
    }
    
    private void initData(){
        if(InternetConnectionUtil.hasInternetConnection(this)){
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    try {
                        DataLoader.getInstance().init();
                        SettingsPrefs.IsDataLoaded.put(true);
                    } catch (ServiceLayerException e) {
                        Logger.getInstance().error(e);
                        DialogFragmentUtil.showErrorDialog(SplashScreenActivity.this, "Internet Connection Error", "Connection refused. There are problems with the server.");
                        return;
                    } catch (InvalidCredentialsException e) {
                        Logger.getInstance().error(e);
                        DialogFragmentUtil.showErrorDialog(SplashScreenActivity.this, "Authorization Error", "Invalid credentials.");
                        return;
                    }
                    SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, OptionActivity.class));
                    SplashScreenActivity.this.finish();
                }
            }, SPLASH_DISPLAY_TIME);
        } else if (SettingsPrefs.IsDataLoaded.get()){
            startActivity(new Intent(SplashScreenActivity.this, OptionActivity.class));
            finish();
        } else {
            DialogFragmentUtil.showConnectionErrorDialog(SplashScreenActivity.this);
        }
    }


}
