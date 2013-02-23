package com.mmf.exceptions;

public class UserSessionException extends Exception {

	private static final long serialVersionUID = -7053610552061896708L;

	public UserSessionException() {
		super();
	}

	public UserSessionException(String detailMessage) {
		super(detailMessage);
	}

	public UserSessionException(Throwable throwable) {
		super(throwable);
	}

}
