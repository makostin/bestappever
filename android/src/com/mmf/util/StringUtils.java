package com.mmf.util;

import com.mmf.ScheduleApplication;

/**
 * @author svetlana.voyteh
 * @date: 3/12/12
 */
public class StringUtils {
    public static boolean isEmpty(String val) {
        return val == null || val.trim().length() == 0;
    }

    public static String getStringByResource(int resourceId){
        return ScheduleApplication.getCurrentApplicationContext().getResources().getString(resourceId);
    }

    public static String[] getStringArrayByResource(int resourceId){
        return ScheduleApplication.getCurrentApplicationContext().getResources().getStringArray(resourceId);
    }
    
    public static String getSelectionString(String[] strings){
        StringBuilder selection = new StringBuilder("(");
        boolean first = true;
        for(String str: strings){
            if (!first){
                selection.append("AND ");
            } else {
                first = false;
            }
            selection.append(str);
            selection.append(" = ? ");
        }
        selection.append(")");
        return selection.toString();
    }
}
