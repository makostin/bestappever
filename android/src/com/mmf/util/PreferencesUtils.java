package com.mmf.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.mmf.ScheduleApplication;

public class PreferencesUtils {

    private static SharedPreferences getPreferences(String prefName){
        return ScheduleApplication.getCurrentApplicationContext().getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public static String getString( String name, String key ) {
        return PreferencesUtils.getString( name, key, null );
    }
    public static String getString( String name, String key, String defValue ) {
        return getPreferences(name).getString( key, defValue );
    }
    public static void put( String name, String key, String val ) {
        getPreferences(name).edit().putString( key, val ).commit();
    }


    public static boolean getBoolean( String name, String key, boolean defValue ) {
        return getPreferences(name).getBoolean( key, defValue );
    }
    public static void put( String name, String key, boolean val ) {
        getPreferences(name).edit().putBoolean( key, val ).commit();
    }


    public static int getInt( String name, String key, int defValue ) {
        return getPreferences(name).getInt( key, defValue );
    }
    public static void put( String name, String key, int val ) {
        getPreferences(name).edit().putInt( key, val ).commit();
    }


    public static long getLong( String name, String key, long defValue ) {
        return getPreferences(name).getLong(key, defValue);
    }
    public static void put( String name, String key, long val ) {
        getPreferences(name).edit().putLong( key, val ).commit();
    }


    public static float getFloat( String name, String key, float defValue ) {
        return getPreferences(name).getFloat(key, defValue);
    }
    public static void put( Context context, String name, String key, float val ) {
        getPreferences(name).edit().putFloat( key, val ).commit();
    }


    public static boolean exists( String name, String key ) {
        return getPreferences(name).contains( key );
    }

    public static void clear( String name ) {
        getPreferences(name).edit().clear().commit();
    }

    public static void remove( String name, String key ) {
        getPreferences(name).edit().remove( key ).commit();
    }
}
