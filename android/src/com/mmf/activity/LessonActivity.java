package com.mmf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.view.Menu;
import com.mmf.R;
import com.mmf.adapter.LecturerLessonsAdapter;
import com.mmf.adapter.StudentLessonsAdapter;
import com.mmf.android.listener.ActivitySwipeDetector;
import com.mmf.db.model.Filter;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Schedule;
import com.mmf.db.model.ScheduleType;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.prefs.OptionPrefs;
import com.mmf.rest.DataLoader;
import com.mmf.rest.exceptions.ServiceLayerException;
import com.mmf.rest.task.LoadDataTask;
import com.mmf.service.FilterService;
import com.mmf.service.LecturerService;
import com.mmf.service.ScheduleService;
import com.mmf.util.*;
import org.apache.http.auth.InvalidCredentialsException;

import java.text.SimpleDateFormat;
import java.util.*;


public class LessonActivity extends BaseActivity implements SwipeInterface {


    private static final int ADD_NOTE = 0x1b;
    private ListView listView;
    private int currentDay;
    private int course;
    private int group;
    private String header;
    private String subGroup;
    private Lecturer lecturer;
    private String date;
    private ScheduleType scheduleType;
    private Filter filter;

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
        scheduleType = (ScheduleType) getIntent().getSerializableExtra(IntentUtil.SCHEDULE_TYPE_EXTRA);

        service = new ScheduleService();
        filterService = new FilterService();
        lecturerService = new LecturerService();

        TextView login = (TextView) findViewById(R.id.text_login);
        if (CredentialsPrefs.IsLogined.get()){
            login.setVisibility(View.VISIBLE);
            login.setText(CredentialsPrefs.Login.get());
        } else {
            login.setVisibility(View.INVISIBLE);
            login.setText("");
        }

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
                        if (ScheduleType.STUDENT.equals(scheduleType)){
                            filter = DataLoader.getInstance().loadSchedule(course, group, subGroup);
                        } else {
                            filter = DataLoader.getInstance().loadSchedule(lecturer);
                        }
                    } else {
                        if (ScheduleType.STUDENT.equals(scheduleType)){
                            filter = filterService.getFilter(course, group, subGroup);
                        } else {
                            filter = filterService.getFilter(lecturer);
                        }
                        if (filter == null){
                            DialogFragmentUtil.showConnectionErrorDialog(LessonActivity.this);
                        }
                    }
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
                super.onCancelled();
                Toast.makeText(LessonActivity.this, "There are problems with data loading.", Toast.LENGTH_LONG).show();
                LessonActivity.this.finish();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                updateView();
            }
        }.execute();
    }

    private void init(){
        calendar = Calendar.getInstance();

        course = OptionPrefs.Course.get();
        group = OptionPrefs.Group.get();
        subGroup = OptionPrefs.Subgroup.get();
        lecturer = lecturerService.getLecturer(OptionPrefs.Lecturer.get());

        header = "";
        if (ScheduleType.STUDENT.equals(scheduleType)){
            header = StringUtils.getStringByResource(R.string.course) + " " + course + ", " + StringUtils.getStringByResource(R.string.group) + " " + group + subGroup;
        } else {
            header = lecturer.getFullName();
        }
        ((TextView) findViewById(R.id.text_header)).setText(header);

        currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        dateFormat = new SimpleDateFormat("EEEE, dd.MM.yyyy", new Locale("ru", "RU"));
        if (currentDay == 1) {  // if currentDay == Sunday
            currentDay++;
            calendar.add(Calendar.DATE, 1); // +1 day to Monday
            date = dateFormat.format(calendar.getTime());
        } else {
            date = dateFormat.format(new Date());
        }
        dayView = (TextView) findViewById(R.id.text_day);
        dayView.setText(date);

        listView = (ListView) findViewById(R.id.list);
        listView.setTextFilterEnabled(true);
        ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);
//        listView.setOnTouchListener(swipe);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Schedule schedule = (Schedule) listView.getAdapter().getItem(position);
                Intent intent = new Intent(LessonActivity.this, NoteActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("time", schedule.getTime());
                intent.putExtra("discipline", schedule.getDiscipline());
                intent.putExtra("classroom", schedule.getClassroom());
                intent.putExtra("header", schedule.getLecturer().getFullName());
                startActivityForResult(intent, ADD_NOTE);
            }
        });


        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_main);
        layout.setOnTouchListener(swipe);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ADD_NOTE:
                // todo:
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateView() {
        List<Schedule> lessons = service.getLessonsForDay(filter, currentDay);
        if (ScheduleType.STUDENT.equals(scheduleType)){
            StudentLessonsAdapter adapterStudent = new StudentLessonsAdapter(this, R.layout.list_student_lessons_item, lessons);
            listView.setAdapter(adapterStudent);
        } else {
            LecturerLessonsAdapter adapterLecturer = new LecturerLessonsAdapter(this, R.layout.list_lecturer_lessons_item, lessons);
            listView.setAdapter(adapterLecturer);
        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.findItem(R.id.menu_schedule).setVisible(false);
        return true;
    }
}
