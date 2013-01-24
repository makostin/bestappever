package com.mmf.db;

/**
 * @author svetlana.voyteh
 * @date: 3/13/12
 */
public class TransactionException extends Exception{

    private static final long serialVersionUID = 1648216128469219773L;

    public TransactionException() {
        super();
    }

    public TransactionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TransactionException(String detailMessage) {
        super(detailMessage);
    }

    public TransactionException(Throwable throwable) {
        super(throwable);
    }
}
