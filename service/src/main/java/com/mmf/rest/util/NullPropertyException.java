package com.mmf.rest.util;

/**
 * User: svetlana.voyteh
 * Date: 18.05.13
 */
public class NullPropertyException extends Exception{
    private static final long serialVersionUID = 2726600826796780374L;

    public NullPropertyException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NullPropertyException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NullPropertyException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NullPropertyException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
