package com.mmf.db.dao;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public class DataAccessException extends Exception{

    private static final long serialVersionUID = -981131713980145408L;

    /**
     * Constructs a new exception with the specified cause and a detail.
     *
     * @param cause the cause
     */
    public DataAccessException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message.
     *
     * @param message message the detail message
     * @param cause the cause
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message message the detail message
     */
    public DataAccessException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     */
    public DataAccessException() {
    }
}
