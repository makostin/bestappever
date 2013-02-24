package com.mmf.rest.exceptions;

public class RestException extends ServiceLayerException {

	private static final long serialVersionUID = 8732335089132438074L;

	public RestException() {
		super();
	}

	public RestException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public RestException(String detailMessage) {
		super(detailMessage);
	}

	public RestException(Throwable throwable) {
		super(throwable);
	}

}
