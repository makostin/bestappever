package com.mmf.business;

/**
 * Thrown by business methods to indicate that business error.
 *
 *  svetlana.voyteh
 * 05.03.13
 *
 */
public class BusinessServiceException extends Exception {

    private static final long serialVersionUID = -752293356881729800L;
    private int errorCode;

    /**
     * Constructs a new exception with the specified cause and a detail.
     *
     * @param cause the cause
     */
    public BusinessServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail.
     *
     * @param message message the detail message
     * @param cause the cause
     */
    public BusinessServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message message the detail message
     */
    public BusinessServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified error code.
     *
     * @param errorCode error code
     */
    public BusinessServiceException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new exception with the specified cause and an error code.
     *
     * @param errorCode error code
     * @param cause the cause
     */
    public BusinessServiceException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new exception with
     * <code>null</code> as its detail message.
     */
    public BusinessServiceException() {
    }

    /**
     * Returns error code
     *
     * @return int
     */
    public int getErrorCode() {
        return errorCode;
    }
}