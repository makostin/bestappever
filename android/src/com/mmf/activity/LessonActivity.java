package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.mmf.R;
import com.mmf.android.listener.ActivitySwipeDetector;
import com.mmf.service.ScheduleService;
import com.mmf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class LessonActivity extends Activity implements SwipeInterface {

    private static final int LOGIN = 1;
    private static final String STUDENT = "Student";
    private static final String LECTURER = "Lecturer";

    private ListView listView;
    private int currentDay;
    private int course;
    private String group;
    private String lecturer;
    private String department;
    private String role;
    private String day;

    private TextView dayView;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    
    private ScheduleService lessonService;
//    private StudentOptionService studentOptionService;
//    private LecturerOptionService lecturerOptionService;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_lessons);

//        studentOptionService = new StudentOptionService();
//        lecturerOptionService = new LecturerOptionService();
        lessonService = new ScheduleService();

        role = getIntent().getStringExtra("role"); 
        init();
        updateView();
    }
    
    private void init(){
        calendar = Calendar.getInstance();

//        try {
            if (STUDENT.equals(role)){
//                lecturerOptionService.removeCurrent();

                course = getIntent().getIntExtra("course", 0);
                group = getIntent().getStringExtra("group");
                TextView courseGroupView = (TextView) findViewById(R.id.course_group);
                courseGroupView.setVisibility(View.VISIBLE);
                courseGroupView.setText("Course " + course + ", group " + group);

                findViewById(R.id.lecturer).setVisibility(View.INVISIBLE);
                updateLessonsForStudent();

            } if (LECTURER.equals(role)){
//                studentOptionService.removeCurrent();

                lecturer = getIntent().getStringExtra("lecturer");
                department = getIntent().getStringExtra("department");
                TextView lecturerView = (TextView) findViewById(R.id.lecturer);
                lecturerView.setVisibility(View.VISIBLE);
                lecturerView.setText(lecturer);

                findViewById(R.id.course_group).setVisibility(View.INVISIBLE);
                updateLessonsForLecturer();
            }
//        } catch (BusinessLayerException e) {
//            e.printStackTrace();
//        }

        
        currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        dateFormat = new SimpleDateFormat("EEEE, dd.MM.yyyy", Locale.US);
        if (currentDay == 1) {  // if currentDay == Sunday
            currentDay++;
            calendar.add(Calendar.DATE, 1); // +1 day to Monday
            day = dateFormat.format(calendar.getTime());
        } else {
            day = dateFormat.format(new Date());
        }
        dayView = (TextView) findViewById(R.id.day);
        dayView.setText(day);

        listView = (ListView) findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);
        listView.setOnTouchListener(swipe);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_main);
        layout.setOnTouchListener(swipe);
    }

    private void updateLessonsForStudent(){
//        try {
//            int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
//            studentOptionService.setCurrent(course, group);
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
    }

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
//        try {
//            if (STUDENT.equals(role)){
//                List<Lesson> lessons = lessonService.getLessonsForDay(course, group, currentDay);
//                StudentLessonsAdapter adapterStudent = new StudentLessonsAdapter(this, R.layout.list_student_lessons_item, lessons);
//                listView.setAdapter(adapterStudent);
//            } if (LECTURER.equals(role)){
//                List<Lesson> lessons = lessonService.getLessonsForDay(lecturer, currentDay);
//                LecturerLessonsAdapter lecturerLessonsAdapter = new LecturerLessonsAdapter(this, R.layout.list_lecturer_lessons_item, lessons);
//                listView.setAdapter(lecturerLessonsAdapter);
//            }
//        } catch (BusinessLayerException ble){
//            Logger.getInstance().error(ble);
//        }
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
        day = dateFormat.format(calendar.getTime());
        dayView.setText(day);
    }

    public void right2left(View v) {
        if (currentDay == 7) {  // currentDay = Saturday
            currentDay = 1;
            calendar.add(Calendar.DATE, -6); // -6 to Monday
        }
        currentDay++;
        LessonActivity.this.updateView();

        calendar.add(Calendar.DATE, 1); // +days
        day = dateFormat.format(calendar.getTime());
        dayView.setText(day);
    }

    public void top2bottom(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        try {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.menu_home:
                    intent = new Intent(LessonActivity.this, MainActivity.class);
                    intent.putExtra("isOptionExist", true);
                    intent.putExtra("role", role);
                    LessonActivity.this.startActivity(intent);
                    LessonActivity.this.finish();
                    break;
                case R.id.menu_login:
                    intent = new Intent(LessonActivity.this, LoginActivity.class);
                    if (StringUtils.getStringByResource(R.string.menu_login).equals(item.getTitle())) {
                        LessonActivity.this.startActivityForResult(intent, LOGIN);
                    } else {
                        findViewById(R.id.welcome).setVisibility(View.INVISIBLE);
                        findViewById(R.id.login).setVisibility(View.INVISIBLE);
//                        studentOptionService.logout();
                    }
                    break;
            }
//        } catch (BusinessLayerException ble){
//            Logger.getInstance().error(ble);
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case LOGIN:
                        String login = data.getStringExtra("login");
                        findViewById(R.id.welcome).setVisibility(View.VISIBLE);
                        TextView loginTextView = ((TextView)findViewById(R.id.login));
                        loginTextView.setVisibility(View.VISIBLE);
                        loginTextView.setText(login);
                        break;
                }
            }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem loginItem = menu.getItem(1);
//        try {
//            if (studentOptionService.isLogin()) {
//                loginItem.setTitle(R.string.menu_logout);
//            } else {
//                loginItem.setTitle(R.string.menu_login);
//            }
//        } catch (BusinessLayerException ble){
//            Logger.getInstance().error(ble);
//        }
        return super.onPrepareOptionsMenu(menu);
    }
}
