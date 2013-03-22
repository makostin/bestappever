package com.mmf.prefs;

import com.mmf.util.PreferencesUtils;

/**
 * User: svetlana.voyteh
 * Date: 05.07.12
 */
public class PreferenceLong extends Preference {
    private long _defVal;

    PreferenceLong(String file, String key, long defVal) {
        super( file, key );
        _defVal = defVal;
    }

    public long get() {
        return PreferencesUtils.getLong(_file, _key, _defVal);
    }
    public void put( long value ) {
        PreferencesUtils.put( _file, _key, value );
    }
}
