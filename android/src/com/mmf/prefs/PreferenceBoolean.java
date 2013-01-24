package com.mmf.prefs;


import android.content.Context;
import com.mmf.util.PreferencesUtils;

public class PreferenceBoolean extends Preference{
    private boolean _defVal;

    PreferenceBoolean( String file, String key, boolean defVal ) {
        super( file, key );
        _defVal = defVal;
    }

    public boolean get( Context context ) {
        return PreferencesUtils.getBoolean(context, _file, _key, _defVal);
    }
    public void put( Context context, boolean value ) {
        PreferencesUtils.put(context, _file, _key, value);
    }
}
