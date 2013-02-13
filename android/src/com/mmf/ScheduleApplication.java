package com.mmf;

import android.app.Application;
import android.content.Context;
import com.mmf.db.DatabaseConnector;


/**
 * @author svetlana.voyteh
 * @date: 3/12/12
 */
public class ScheduleApplication extends Application {

    private static DatabaseConnector databaseConnector;
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        databaseConnector = new DatabaseConnector(applicationContext);
    }

    public static DatabaseConnector getDatabaseConnector() {
        return databaseConnector;
    }

    public static Context getCurrentApplicationContext() {
        return applicationContext;
    }
}
