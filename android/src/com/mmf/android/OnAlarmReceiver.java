package com.mmf.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author svetlana.voyteh
 * @date: 2/16/12
 */
public class OnAlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "Service work!", Toast.LENGTH_LONG).show();
        Log.v(this.getClass().getName(), "Service catch alarm message at: " + new java.util.Date().toString());

//        StudentOptionService optionService = new StudentOptionService();
//        LessonService lessonService = new LessonService();
//        int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
//        ArrayList<StudentOption> options = (ArrayList<StudentOption>) optionService.getDownloaded();
//        for (StudentOption option: options){
//            lessonService.deleteCurrentLessons(option.getCourse(), option.getSubgroup());
//            optionService.setCurrentWeek(week);
//            lessonService.insertLessons(option.getCourse(), option.getSubgroup(), week);
//        }
    }
}
