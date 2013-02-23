package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.mmf.R;

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
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, OptionActivity.class));
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }
}
