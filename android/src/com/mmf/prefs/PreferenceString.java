package com.mmf.prefs;

import com.mmf.util.PreferencesUtils;

/**
 * User: svetlana.voyteh
 * Date: 05.07.12
 */
public class PreferenceString extends Preference {
    private String _defVal;

    PreferenceString(String file, String key) {
        super(file, key);
    }
    PreferenceString( String file, String key, String defVal ) {
        super(file, key);
        _defVal = defVal;
    }
    public String get( ) {
        return PreferencesUtils.getString( _file, _key, _defVal);
    }
    public void put(String value ) {
        PreferencesUtils.put( _file, _key, value );
    }
}
