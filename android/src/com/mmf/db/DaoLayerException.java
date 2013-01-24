package com.mmf.db;

/**
 * @author svetlana.voyteh
 * @date: 3/13/12
 */
public class DaoLayerException extends Exception{

    private static final long serialVersionUID = -3768702387581105581L;

    public DaoLayerException() {
        super();
    }

    public DaoLayerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DaoLayerException(String detailMessage) {
        super(detailMessage);
    }

    public DaoLayerException(Throwable throwable) {
        super(throwable);
    }
}
