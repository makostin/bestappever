package com.mmf.rest.transport;

public class RestResponse {
	private int responseCode;
	private String responseMessage;

	public RestResponse(int responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
}
