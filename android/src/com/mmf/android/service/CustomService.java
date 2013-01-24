package com.mmf.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import com.mmf.android.OnAlarmReceiver;

/**
 * @author svetlana.voyteh
 * @date: 2/16/12
 */
public class CustomService extends Service{


    public static final int FIRST_RUN = 5000; // 5 seconds
    public static final int INTERVAL = 1000*60*20; // 20 min

    AlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent(this, OnAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        this.alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        this.alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + FIRST_RUN, INTERVAL, pendingIntent);

        Log.v(this.getClass().getName(), "AlarmManger binding at " + new java.util.Date().toString());
        Log.v(this.getClass().getName(), "Service created.");
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.v(this.getClass().getName(), "Service binded.");
        return null;
    }

    @Override
    public void onDestroy() {
        if (alarmManager != null) {
            Intent intent = new Intent(this, OnAlarmReceiver.class);
            alarmManager.cancel(PendingIntent.getBroadcast(this, 0, intent, 0));
            Log.v(this.getClass().getName(), "AlarmManger unbinding at " + new java.util.Date().toString());
        }
        Log.v(this.getClass().getName(), "Service destroyed.");
    }
}
