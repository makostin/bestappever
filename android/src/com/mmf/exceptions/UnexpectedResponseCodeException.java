package com.mmf.exceptions;

public class UnexpectedResponseCodeException extends RestException {
	private static final long serialVersionUID = 3965086962933092663L;

	private int responseCode;

	public UnexpectedResponseCodeException(Throwable throwable) {
		super(throwable);
	}

	public UnexpectedResponseCodeException(String details) {
		super("Unexpected response message");
	}

	public UnexpectedResponseCodeException(int responseCode) {
		super("Unexpected response code: " + String.valueOf(responseCode));
		this.responseCode = responseCode;
	}

	public int getResponseCode() {
		return responseCode;
	}

}