package com.mmf.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mmf.ScheduleApplication;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 3/13/12
 */
public class DBAdapter {

    private static DBAdapter DBAdapterInstance;
    private final DatabaseConnector databaseConnector;
    private SQLiteDatabase currentDatabase;

    public static DBAdapter getInstance() {
        if (DBAdapterInstance == null) {
            synchronized (DBAdapter.class) {
                if (DBAdapterInstance == null) {
                    DBAdapterInstance = new DBAdapter();
                }
            }
        }
        return DBAdapterInstance;
    }

    private DBAdapter() {
        databaseConnector = ScheduleApplication.getDatabaseConnector();
        currentDatabase = databaseConnector.getWritableDatabase();
        currentDatabase.setLockingEnabled(false);
    }

    public void close() {
        Logger.getInstance().debug("Closing entity manager");
        currentDatabase.close();
        databaseConnector.close();
        DBAdapterInstance = null;
        Logger.getInstance().debug("Entity manager closed");
    }

    public synchronized SQLiteDatabase getCurrentDatabase() {
        return currentDatabase;
    }

    public synchronized void beginTransaction() {
        currentDatabase.beginTransaction();
    }

    public synchronized void rollbackTransaction() {
        if (currentDatabase.inTransaction()) {
            currentDatabase.endTransaction();
        }
    }

    public synchronized void commitTransaction() {
        if (currentDatabase.inTransaction()) {
            currentDatabase.setTransactionSuccessful();
            currentDatabase.endTransaction();
        }
    }
}
