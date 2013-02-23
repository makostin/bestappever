package com.mmf.exceptions;

/**
 * @author svetlana.voyteh
 * @date: 4/25/12
 */
public class ServiceLayerException extends Exception {

    private static final long serialVersionUID = 2033648243517462444L;

    public ServiceLayerException() {
        super();
    }

    public ServiceLayerException(String detailMessage) {
        super(detailMessage);
    }

    public ServiceLayerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ServiceLayerException(Throwable throwable) {
        super(throwable);
    }
}
