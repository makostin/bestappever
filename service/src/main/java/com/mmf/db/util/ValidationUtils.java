package com.mmf.db.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public final class ValidationUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationUtils.class);

    /**
     * Hidden constructor.
     */
    private ValidationUtils() {
    }

    /**
     * On null object generates an exception of provided class.
     *
     * @param <T> Type of generated exception. Exception should define constructor with single string parameter (error message).
     * @param obj test object
     * @param messageKey message for generated exception
     * @param type class of generated exception
     * @throws T user specified exception
     */
    public static <T extends Exception> void isNullObject(Object obj, String messageKey, Class<T> type) throws T {
        if (obj == null) {
            instantiateException(messageKey, type);
        }
    }

    /**
     * On not null object generates an exception of provided class.
     *
     * @param <T> Type of generated exception. Exception should define constructor with single string parameter (error message).
     * @param obj test object
     * @param messageKey message for generated exception
     * @param type class of generated exception
     * @throws T user specified exception
     */
    public static <T extends Exception> void isNotNullObject(Object obj, String messageKey, Class<T> type) throws T {
        if (obj != null) {
            instantiateException(messageKey, type);
        }
    }

    /**
     * Generates exception of the given type.
     * Method uses single String parameter constructor to create exception object.
     *
     * @param <T> Type of generated exception. Exception should define constructor with single string parameter (error message).
     * @param messageKey message for generated exception
     * @param type class of generated exception
     * @throws T user specified exception
     */
    public static <T extends Exception> void instantiateException(String messageKey, Class<T> type) throws T {
        Constructor<T> messageConstructor = null;
        try {
            messageConstructor = type.getConstructor(String.class);
            throw messageConstructor.newInstance(messageKey);
        } catch (NoSuchMethodException ex) {
            LOG.error(null, ex);
            throw new IllegalArgumentException(ex);
        } catch (InvocationTargetException ex) {
            LOG.error(null, ex);
            throw new UndeclaredThrowableException(ex);
        } catch (InstantiationException ex) {
            LOG.error(null, ex);
            throw new UndeclaredThrowableException(ex);
        } catch (IllegalAccessException ex) {
            LOG.error(null, ex);
            throw new UndeclaredThrowableException(ex);
        }
    }
}
