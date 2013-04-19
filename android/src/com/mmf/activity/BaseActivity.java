package com.mmf.activity;

import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.mmf.R;

/**
 * svetlana.voyteh
 * 18.04.13
 */
public class BaseActivity extends SherlockFragmentActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_week:
                Toast.makeText(this, "week", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_options:
                Toast.makeText(this, "options", Toast.LENGTH_LONG).show();
                return true;
            case android.R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_LONG).show();
                return true;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
