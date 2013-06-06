package com.mmf.activity;

import android.content.Intent;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.mmf.R;
import com.mmf.db.model.ScheduleType;
import com.mmf.util.IntentUtil;
import com.mmf.view.ToggleButton;

/**
 * svetlana.voyteh
 * 18.04.13
 */
public class BaseActivity extends SherlockFragmentActivity {


    protected ToggleButton toggleButton;

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
            case R.id.menu_schedule:
                Intent intent = new Intent(BaseActivity.this, LessonActivity.class);
                if (toggleButton.getSelectedView() == ScheduleType.STUDENT.getId()){
                    intent.putExtra(IntentUtil.SCHEDULE_TYPE_EXTRA, ScheduleType.STUDENT);
                } else {
                    intent.putExtra(IntentUtil.SCHEDULE_TYPE_EXTRA, ScheduleType.LECTURER);
                }
                startActivity(intent);
                finish();
                return true;
            case R.id.menu_options:
                startActivity(new Intent(BaseActivity.this, OptionActivity.class));
                return true;
            case android.R.id.home:
                startActivity(new Intent(BaseActivity.this, LessonActivity.class));
                finish();
                return true;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }
}
