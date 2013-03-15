package com.mmf.prefs;

import android.content.Context;
import com.mmf.util.PreferencesUtils;

/**
 * svetlana.voyteh
 * 20.02.13
 */
public class CredentialsPrefs {

    private static final String CREDENTIAL_PREFERENCES = "credentials_preferences";
    private static final String LOGIN_KEY = "credentials_login";
    private static final String PASSWORD_KEY = "password_key";
    private static final String COURSE_AMOUNT_KEY = "course_amount_key";
    private static final String COURSE_KEY = "course_key";
    private static final String GROUP_AMOUNT_KEY = "group_amount_key";
    private static final String GROUP_KEY = "group_key";
    private static final String SUBGROUP_KEY = "subgroup_key";
    private static final String LECTURER_KEY = "lecturer_key";

    public static final PreferenceString Login = new PreferenceString( CREDENTIAL_PREFERENCES, LOGIN_KEY, "user" );
    public static final PreferenceString Password = new PreferenceString( CREDENTIAL_PREFERENCES, PASSWORD_KEY, "user" );
    public static final PreferenceString Course = new PreferenceString( CREDENTIAL_PREFERENCES, COURSE_KEY );
    public static final PreferenceString CourseAmount = new PreferenceString( CREDENTIAL_PREFERENCES, COURSE_AMOUNT_KEY );
    public static final PreferenceString Group = new PreferenceString( CREDENTIAL_PREFERENCES, GROUP_KEY );
    public static final PreferenceString GroupAmount = new PreferenceString( CREDENTIAL_PREFERENCES, GROUP_AMOUNT_KEY );
    public static final PreferenceString Subgroup = new PreferenceString( CREDENTIAL_PREFERENCES, SUBGROUP_KEY );
    public static final PreferenceString Lecturer = new PreferenceString( CREDENTIAL_PREFERENCES, LECTURER_KEY );

    public static void erase() {
        PreferencesUtils.clear(CREDENTIAL_PREFERENCES);
    }
}
