package com.mmf.service;

/**
 * @author svetlana.voyteh
 * @date: 3/13/12
 */
public class BusinessLayerException extends Exception {

    private static final long serialVersionUID = 1570442133505582672L;

    public BusinessLayerException() {
        super();
    }

    public BusinessLayerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BusinessLayerException(String detailMessage) {
        super(detailMessage);
    }

    public BusinessLayerException(Throwable throwable) {
        super(throwable);
    }

}
