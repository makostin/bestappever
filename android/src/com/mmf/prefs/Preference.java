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
        PreferencesUtils.remove( _file, _key);
    }

    public boolean exists() {
        return PreferencesUtils.exists(  _file, _key );
    }
}
