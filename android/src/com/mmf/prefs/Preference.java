package com.mmf.prefs;


import android.content.Context;
import com.mmf.util.PreferencesUtils;

public class Preference {
    String _file;
    String _key;

    Preference( String file, String key ) {
        _file = file;
        _key = key;
    }

    public void erase( Context context ) {
        PreferencesUtils.remove(context, _file, _key);
    }

    public boolean exists( Context context ) {
        return PreferencesUtils.exists( context, _file, _key );
    }
}
