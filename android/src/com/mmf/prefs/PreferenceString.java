package com.mmf.prefs;

import android.content.Context;
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
    public String get( Context context ) {
        return PreferencesUtils.getString(context, _file, _key, _defVal);
    }
    public void put( Context context, String value ) {
        PreferencesUtils.put( context, _file, _key, value );
    }
}
