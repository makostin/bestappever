package com.mmf.prefs;

import com.mmf.util.PreferencesUtils;

/**
 * User: svetlana.voyteh
 * Date: 21.03.13
 */
public class SettingsPrefs {

    private static final String SETTINGS_PREFERENCES = "settings_preferences";

    private static final String FIRST_SEMESTER_START_KEY = "settings_first_start_semester";
    private static final String FIRST_SEMESTER_END_KEY = "settings_first_end_semester";
    private static final String SECOND_SEMESTER_START_KEY = "settings_second_start_semester";
    private static final String SECOND_SEMESTER_END_KEY = "settings_second_end_semester";
    private static final String IS_DATA_LOADED_KEY = "is_data_loaded";

    public static final PreferenceString FirstSemesterStart = new PreferenceString( SETTINGS_PREFERENCES, FIRST_SEMESTER_START_KEY, "01.09" );
    public static final PreferenceString FirstSemesterEnd = new PreferenceString( SETTINGS_PREFERENCES, FIRST_SEMESTER_END_KEY, "25.12" );
    public static final PreferenceString SecondSemesterStart = new PreferenceString( SETTINGS_PREFERENCES, SECOND_SEMESTER_START_KEY, "01.02" );
    public static final PreferenceString SecondSemesterEnd = new PreferenceString( SETTINGS_PREFERENCES, SECOND_SEMESTER_END_KEY, "25.05" );
    public static final PreferenceBoolean IsDataLoaded = new PreferenceBoolean( SETTINGS_PREFERENCES, IS_DATA_LOADED_KEY, false );

    public static void erase() {
        PreferencesUtils.clear(SETTINGS_PREFERENCES);
    }
}
