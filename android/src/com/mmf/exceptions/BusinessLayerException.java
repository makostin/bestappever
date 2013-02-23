package com.mmf.exceptions;

public class BusinessLayerException extends ServiceLayerException {

	private static final long serialVersionUID = -858196582442819299L;

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
