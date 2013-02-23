package com.mmf.prefs;

import com.mmf.util.PreferencesUtils;

/**
 * User: svetlana.voyteh
 * Date: 05.07.12
 */
public class PreferenceInt extends Preference {
    private int _defVal;

    PreferenceInt( String file, String key, int defVal ) {
        super( file, key );
        _defVal = defVal;
    }

    public int get() {
        return PreferencesUtils.getInt(_file, _key, _defVal);
    }
    public void put( int value ) {
        PreferencesUtils.put( _file, _key, value );
    }
}
