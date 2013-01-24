package com.mmf.business.service;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public class BusinessServiceException extends Exception{
    private static final long serialVersionUID = -4331308060111199747L;

    /**
     * Constructs a new exception with the specified cause and a detail.
     *
     * @param cause
     *            the cause
     */
    public BusinessServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail.
     *
     * @param message
     *            message the detail message
     * @param cause
     *            the cause
     */
    public BusinessServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message
     *            message the detail message
     */
    public BusinessServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     */
    public BusinessServiceException() {
    }
}
