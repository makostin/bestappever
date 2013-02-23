package com.mmf.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.mmf.EntityRegistry;
import com.mmf.R;
import com.mmf.android.adapter.StudentLessonsAdapter;
import com.mmf.android.listener.ActivitySwipeDetector;
import com.mmf.db.model.Schedule;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.rest.DataLoader;
import com.mmf.service.BusinessLayerException;
import com.mmf.service.ScheduleService;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class LessonActivity extends Activity implements SwipeInterface {


    private ListView listView;
    private int currentDay;
    private String course;
    private String group;
    private String subGroup;
    private String date;

    private TextView dayView;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    
    private ScheduleService service;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_lessons);

        service = new ScheduleService();
        init();
        new LoadData().execute();
    }
    
    private void init(){
        calendar = Calendar.getInstance();

        course = CredentialsPrefs.Course.get();
        group = CredentialsPrefs.Group.get();
        subGroup = CredentialsPrefs.Subgroup.get();
        TextView courseGroupView = (TextView) findViewById(R.id.course_group);
        courseGroupView.setVisibility(View.VISIBLE);
        courseGroupView.setText("Course " + course + ", group " + group);

        findViewById(R.id.lecturer).setVisibility(View.INVISIBLE);
//        updateLessonsForStudent();


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

//    private void updateLessonsForStudent(){
//        try {
//            int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
//
//            if (studentOptionService.isLessonsUpdate(currentWeek)){
//                lessonService.deleteCurrentLessons(course, group);
//                studentOptionService.setCurrentWeek(currentWeek);
//            }
//
//            if (!lessonService.isLessonsExist(course, group)) {
//                lessonService.insertLessons(course, group, currentWeek);
//            }
//        } catch (BusinessLayerException ble){
//            Logger.getInstance().error(ble);
//        }
//    }

    private void updateLessonsForLecturer(){
//        try {
//            int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
//            lecturerOptionService.setCurrent(department, lecturer);
//
//            if (lecturerOptionService.isLessonsUpdate(currentWeek)){
//                lessonService.deleteCurrentLessons(lecturer);
//                lecturerOptionService.setCurrentWeek(currentWeek);
//            }
//
//            if (!lessonService.isLessonsExist(lecturer)) {
//                lessonService.insertLessons(lecturer, currentWeek);
//            }
//        } catch (BusinessLayerException ble){
//            Logger.getInstance().error(ble);
//        }
    }

    private void updateView() {
        List<Schedule> lessons = service.getLessonsForDay(course, group, currentDay);
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

        calendar.add(Calendar.DATE, -1); // -days
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

        calendar.add(Calendar.DATE, 1); // +days
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
    
    private class LoadData extends AsyncTask{

        private ProgressDialog dialog;
        
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(LessonActivity.this);
            dialog.setMessage("Loading data. Please. wait...");
        }

        @Override
        protected Object doInBackground(Object... objects) {
            DataLoader.getInstance().loadSchedule(course, group, subGroup);
            return null;  
        }

        @Override
        protected void onPostExecute(Object o) {
            updateView();
            if(dialog.isShowing()){
                dialog.dismiss();
            }
        }
    }
}
