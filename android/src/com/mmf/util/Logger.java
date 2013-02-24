package com.mmf.util;

import android.util.Log;

/**
 * @author svetlana.voyteh
 * @date: 3/12/12
 */
public class Logger {

    public static final String APPLICATION_LOGGER_TAG = "Schedule";

    private static Logger ourInstance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (ourInstance == null) {
            synchronized (Logger.class) {
                if (ourInstance == null) {
                    ourInstance = new Logger();
                }
            }
        }
        return ourInstance;
    }

    private void warn(String tag, String message) {
        Log.w(tag, message);
    }

    public void warn(String message) {
        warn(APPLICATION_LOGGER_TAG, message);
    }

    public void warn(Throwable ex) {
        if (ex.getMessage() == null) {
            warn(APPLICATION_LOGGER_TAG, ex.getClass().toString());
        } else {
            warn(APPLICATION_LOGGER_TAG, ex.getMessage());
        }
    }

    private void error(String tag, String message) {
        Log.e(tag, message);
    }

    public void error(Throwable ex) {
        if (ex.getMessage() == null) {
            error(APPLICATION_LOGGER_TAG, ex.getClass().toString());
        } else {
            error(APPLICATION_LOGGER_TAG, ex.getMessage());
        }
    }

    private void debug(String tag, String message) {
        Log.d(tag, message);
    }

    public void debug(String message) {
        debug(APPLICATION_LOGGER_TAG, message);
    }
}
