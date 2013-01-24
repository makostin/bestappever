package com.mmf.prefs;

import android.content.Context;
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

    public int get( Context context ) {
        return PreferencesUtils.getInt(context, _file, _key, _defVal);
    }
    public void put( Context context, int value ) {
        PreferencesUtils.put( context, _file, _key, value );
    }
}
