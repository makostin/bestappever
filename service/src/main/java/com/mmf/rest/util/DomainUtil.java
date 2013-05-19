package com.mmf.rest.util;

/**
 * User: svetlana.voyteh
 * Date: 18.05.13
 */
public class DomainUtil {

    public static void checkingForNotNull(Object object) throws NullPropertyException{
        if (object == null){
            throw new NullPropertyException();
        }
    }

    public static void checkingForNull(Object object) throws NotNullPropertyException{
        if (object != null){
            throw new NotNullPropertyException();
        }
    }
}
