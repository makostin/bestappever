package com.mmf.activity;

import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import com.mmf.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * User: svetlana.voyteh
 * Date: 09.06.13
 */
public class OptionActivityTest extends ActivityInstrumentationTestCase2<BaseActivity> {

    private static final String COURSE = "2";
    private static final String GROUP = "2";
    private static final String SUBGROUP = "b";

    public OptionActivityTest() {
        super(BaseActivity.class);
    }

    private Solo solo;

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testStudentScheduleView(){
        solo.clickOnView(solo.getView(R.id.course_spinner));
        solo.clickOnText(COURSE);
        solo.clickOnView(solo.getView(R.id.group_spinner));
        solo.clickOnText(GROUP);
        solo.clickOnView(solo.getView(R.id.subgroup_spinner));
        solo.clickOnText(SUBGROUP);

        solo.clickOnActionBarItem(R.id.menu_schedule);
        solo.assertCurrentActivity("Expected Lesson activity", LessonActivity.class.getSimpleName());

        String date = getDate();
        boolean actual = solo.waitForText(date);
        assertTrue(actual);
    }

    private String getDate() {
        String date = "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd.MM.yyyy", new Locale("ru", "RU"));
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {  // if currentDay == Sunday
            calendar.add(Calendar.DATE, 1); // +1 day to Monday
            date = dateFormat.format(calendar.getTime());
        } else {
            date = dateFormat.format(new Date());
        }
        return date;
    }
}
