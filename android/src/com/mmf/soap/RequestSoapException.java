package com.mmf.soap;

/**
 * @author svetlana.voyteh
 * @date: 4/10/12
 */
public class RequestSoapException extends Exception{

    private static final long serialVersionUID = 2618285834895076058L;

    public RequestSoapException() {
        super();
    }

    public RequestSoapException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RequestSoapException(String detailMessage) {
        super(detailMessage);
    }

    public RequestSoapException(Throwable throwable) {
        super(throwable);
    }
}
