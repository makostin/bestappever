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
    private static final String LOGIN_DEFAULT_KEY = "credentials_login_default";
    private static final String PASSWORD_KEY = "password_key";
    private static final String PASSWORD_DEFAULT_KEY = "password_key_default";
    private static final String IS_LOGINED_KEY = "is_logined_key_default";

    public static final PreferenceString LoginDefault = new PreferenceString( CREDENTIAL_PREFERENCES, LOGIN_DEFAULT_KEY, "yasvedko" );
    public static final PreferenceString Login = new PreferenceString( CREDENTIAL_PREFERENCES, LOGIN_KEY );
    public static final PreferenceString PasswordDefault = new PreferenceString( CREDENTIAL_PREFERENCES, PASSWORD_DEFAULT_KEY, "yasvedko" );
    public static final PreferenceString Password = new PreferenceString( CREDENTIAL_PREFERENCES, PASSWORD_KEY );
    public static final PreferenceBoolean IsLogined = new PreferenceBoolean( CREDENTIAL_PREFERENCES, IS_LOGINED_KEY, false );

    public static void erase() {
        PreferencesUtils.clear(CREDENTIAL_PREFERENCES);
    }
}
