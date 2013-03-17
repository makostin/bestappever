package com.mmf.prefs;

/**
 * User: svetlana.voyteh
 * Date: 16.03.13
 */
public class OptionPrefs {

    private static final String OPTION_PREFERENCES = "credentials_preferences";

    private static final String COURSE_AMOUNT_KEY = "course_amount_key";
    private static final String GROUP_AMOUNT_KEY = "group_amount_key";
    private static final String COURSE_KEY = "course_key";
    private static final String GROUP_KEY = "group_key";
    private static final String SUBGROUP_KEY = "subgroup_key";
    private static final String SUBGROUP_AMOUNT_KEY = "subgroup_amount_key";
    private static final String LECTURER_KEY = "lecturer_key";


    public static final PreferenceInt Course = new PreferenceInt(OPTION_PREFERENCES, COURSE_KEY, -1 );
    public static final PreferenceInt CourseAmount = new PreferenceInt(OPTION_PREFERENCES, COURSE_AMOUNT_KEY, -1 );
    public static final PreferenceInt Group = new PreferenceInt(OPTION_PREFERENCES, GROUP_KEY, -1 );
    public static final PreferenceInt GroupAmount = new PreferenceInt(OPTION_PREFERENCES, GROUP_AMOUNT_KEY, -1 );
    public static final PreferenceString Subgroup = new PreferenceString(OPTION_PREFERENCES, SUBGROUP_KEY );
    public static final PreferenceString SubgroupAmount = new PreferenceString(OPTION_PREFERENCES, SUBGROUP_AMOUNT_KEY );
    public static final PreferenceString Lecturer = new PreferenceString(OPTION_PREFERENCES, LECTURER_KEY );


}
