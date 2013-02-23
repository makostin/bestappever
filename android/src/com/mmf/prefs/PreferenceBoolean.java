package com.mmf.prefs;


import com.mmf.util.PreferencesUtils;

public class PreferenceBoolean extends Preference{
    private boolean _defVal;

    PreferenceBoolean( String file, String key, boolean defVal ) {
        super( file, key );
        _defVal = defVal;
    }

    public boolean get() {
        return PreferencesUtils.getBoolean( _file, _key, _defVal);
    }
    public void put( boolean value ) {
        PreferencesUtils.put( _file, _key, value);
    }
}
