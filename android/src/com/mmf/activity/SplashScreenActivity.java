package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.mmf.R;
import com.mmf.rest.DataLoader;
import com.mmf.rest.exceptions.ServiceLayerException;
import com.mmf.util.Logger;
import org.apache.http.auth.InvalidCredentialsException;

/**
 * @author svetlana.voyteh
 * @date: 2/12/12
 */
public class SplashScreenActivity extends Activity{

    public static final int SPLASH_DISPLAY_TIME = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    DataLoader.getInstance().init();
                    SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, OptionActivity.class));
                    SplashScreenActivity.this.finish();
                } catch (ServiceLayerException e) {
                    Logger.getInstance().error(e);
                } catch (InvalidCredentialsException e) {
                    Logger.getInstance().error(e);
                }
            }
        }, SPLASH_DISPLAY_TIME);
    }
}
