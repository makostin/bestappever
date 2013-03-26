package com.mmf.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.mmf.R;
import com.mmf.adapter.StudentLessonsAdapter;
import com.mmf.android.listener.ActivitySwipeDetector;
import com.mmf.db.model.Filter;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Schedule;
import com.mmf.prefs.OptionPrefs;
import com.mmf.rest.DataLoader;
import com.mmf.rest.exceptions.ServiceLayerException;
import com.mmf.rest.task.LoadDataTask;
import com.mmf.service.BusinessLayerException;
import com.mmf.service.FilterService;
import com.mmf.service.LecturerService;
import com.mmf.service.ScheduleService;
import com.mmf.util.DialogFragmentUtil;
import com.mmf.util.InternetConnectionUtil;
import com.mmf.util.Logger;
import org.apache.http.auth.InvalidCredentialsException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class LessonActivity extends FragmentActivity implements SwipeInterface {


    private ListView listView;
    private int currentDay;
//    private int course;
//    private int group;
//    private String subGroup;
//    private Lecturer lecturer;
    private String date;

    private String studentHeader;
    private String lecturerHeader;

    private TextView dayView;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    
    private ScheduleService service;
    private FilterService filterService;
    private LecturerService lecturerService;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_lessons);

        service = new ScheduleService();
        filterService = new FilterService();
        lecturerService = new LecturerService();
        init();
        loadData();
    }

    private void loadData() {
        new LoadDataTask(this){

            @Override
            protected Object doInBackground(Object... objects) {
                try {
                    // if don't have internet connection then get schedule by filter id
                    if(InternetConnectionUtil.hasInternetConnection(LessonActivity.this)){
                        long idFilter = filterService.updateFilter(course, group, subGroup);
                        DataLoader.getInstance().loadSchedule(course, group, subGroup, idFilter);
                    } else {
                        Filter filter = filterService.getFilter(course, group, subGroup);  
                        if (filter == null){
                            DialogFragmentUtil.showConnectionErrorDialog(LessonActivity.this);
                        }
                    }
                } catch (BusinessLayerException e) {
                    cancel(true);
                    Logger.getInstance().error(e);
                } catch (InvalidCredentialsException e) {
                    cancel(true);
                    Logger.getInstance().error(e);
                } catch (ServiceLayerException e) {
                    cancel(true);
                    Logger.getInstance().error(e);
                }
                return null;
            }

            @Override
            protected void onCancelled() {
                Toast.makeText(LessonActivity.this, "There are problems with data loading.", Toast.LENGTH_LONG);
                super.onCancelled();
            }

            @Override
            protected void onPostExecute(Object o) {
                updateView();
                super.onPostExecute(o);
            }
        }.execute();
    }

    private void init(){
        calendar = Calendar.getInstance();

        int course = OptionPrefs.Course.get();
        int group = OptionPrefs.Group.get();
        String subGroup = OptionPrefs.Subgroup.get();
        Lecturer lecturer = lecturerService.getLecturer(OptionPrefs.Lecturer.get());

        studentHeader = "Course " + course + ", group " + group + subGroup;
        lecturerHeader = lecturer.getFullName();

        currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        dateFormat = new SimpleDateFormat("EEEE, dd.MM.yyyy", Locale.US);
        if (currentDay == 1) {  // if currentDay == Sunday
            currentDay++;
            calendar.add(Calendar.DATE, 1); // +1 day to Monday
            date = dateFormat.format(calendar.getTime());
        } else {
            date = dateFormat.format(new Date());
        }
        dayView = (TextView) findViewById(R.id.day);
        dayView.setText(date);

        listView = (ListView) findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);
        listView.setOnTouchListener(swipe);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_main);
        layout.setOnTouchListener(swipe);
    }

    private void updateView() {
        List<Schedule> lessons = service.getLessonsForDay(course, group, subGroup, currentDay);
        StudentLessonsAdapter adapterStudent = new StudentLessonsAdapter(this, R.layout.list_student_lessons_item, lessons);
        listView.setAdapter(adapterStudent);
    }

    public void bottom2top(View v) {

    }

    public void left2right(View v) {
        if (currentDay == 2) {  //currentDay = Monday
            currentDay = 8;
            calendar.add(Calendar.DATE, 6);// +6 to Saturday
        }
        currentDay--;
        LessonActivity.this.updateView();

        calendar.add(Calendar.DATE, -1); // -day
        date = dateFormat.format(calendar.getTime());
        dayView.setText(date);
    }

    public void right2left(View v) {
        if (currentDay == 7) {  // currentDay = Saturday
            currentDay = 1;
            calendar.add(Calendar.DATE, -6); // -6 to Monday
        }
        currentDay++;
        LessonActivity.this.updateView();

        calendar.add(Calendar.DATE, 1); // +day
        date = dateFormat.format(calendar.getTime());
        dayView.setText(date);
    }

    public void top2bottom(View v) {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
////        try {
//            Intent intent;
//            switch (item.getItemId()) {
//                case R.id.menu_home:
//                    intent = new Intent(LessonActivity.this, OptionActivity.class);
//                    intent.putExtra("isOptionExist", true);
//                    intent.putExtra("role", role);
//                    LessonActivity.this.startActivity(intent);
//                    LessonActivity.this.finish();
//                    break;
//                case R.id.menu_login:
//                    intent = new Intent(LessonActivity.this, LoginActivity.class);
//                    if (StringUtils.getStringByResource(R.string.menu_login).equals(item.getTitle())) {
//                        LessonActivity.this.startActivityForResult(intent, LOGIN);
//                    } else {
//                        findViewById(R.id.welcome).setVisibility(View.INVISIBLE);
//                        findViewById(R.id.login).setVisibility(View.INVISIBLE);
////                        studentOptionService.logout();
//                    }
//                    break;
//            }
////        } catch (BusinessLayerException ble){
////            Logger.getInstance().error(ble);
////        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (data == null) return;
//            if (resultCode == RESULT_OK) {
//                switch (requestCode) {
//                    case LOGIN:
//                        String login = data.getStringExtra("login");
//                        findViewById(R.id.welcome).setVisibility(View.VISIBLE);
//                        TextView loginTextView = ((TextView)findViewById(R.id.login));
//                        loginTextView.setVisibility(View.VISIBLE);
//                        loginTextView.setText(login);
//                        break;
//                }
//            }
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
////        MenuItem loginItem = menu.getItem(1);
////        try {
////            if (studentOptionService.isLogin()) {
////                loginItem.setTitle(R.string.menu_logout);
////            } else {
////                loginItem.setTitle(R.string.menu_login);
////            }
////        } catch (BusinessLayerException ble){
////            Logger.getInstance().error(ble);
////        }
//        return super.onPrepareOptionsMenu(menu);
//    }

}
