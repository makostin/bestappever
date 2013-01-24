package com.mmf.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    public static String getString( Context context, String name, String key ) {
        return PreferencesUtils.getString( context, name, key, null );
    }
    public static String getString( Context context, String name, String key, String defValue ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        return prefs.getString( key, defValue );
    }
    public static void put( Context context, String name, String key, String val ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        prefs.edit().putString( key, val ).commit();
    }


    public static boolean getBoolean( Context context, String name, String key, boolean defValue ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        return prefs.getBoolean( key, defValue );
    }
    public static void put( Context context, String name, String key, boolean val ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        prefs.edit().putBoolean( key, val ).commit();
    }


    public static int getInt( Context context, String name, String key, int defValue ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        return prefs.getInt( key, defValue );
    }
    public static void put( Context context, String name, String key, int val ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        prefs.edit().putInt( key, val ).commit();
    }


    public static long getLong( Context context, String name, String key, long defValue ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        return prefs.getLong(key, defValue);
    }
    public static void put( Context context, String name, String key, long val ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        prefs.edit().putLong( key, val ).commit();
    }


    public static float getFloat( Context context, String name, String key, float defValue ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        return prefs.getFloat(key, defValue);
    }
    public static void put( Context context, String name, String key, float val ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        prefs.edit().putFloat( key, val ).commit();
    }


    public static boolean exists( Context context, String name, String key ) {
        SharedPreferences prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE );
        return prefs.contains( key );
    }

    public static void clear( Context context, String name ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        prefs.edit().clear().commit();
    }

    public static void remove( Context context, String name, String key ) {
        SharedPreferences prefs = context.getSharedPreferences( name, Context.MODE_PRIVATE );
        prefs.edit().remove( key ).commit();
    }
}
