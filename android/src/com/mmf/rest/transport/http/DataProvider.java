package com.mmf.rest.transport.http;

import com.mmf.rest.exceptions.RestException;
import com.mmf.rest.exceptions.UnexpectedResponseCodeException;
import org.apache.http.auth.InvalidCredentialsException;

import java.io.IOException;
import java.io.OutputStream;

public interface DataProvider {
	public void get(String login, String password, OutputStream outputWriter) throws RestException, InvalidCredentialsException;
	
	public void post(String login, String password, String requestData, OutputStream outputWriter) throws IOException, UnexpectedResponseCodeException, InvalidCredentialsException;
	
	public void doLogin(String login, String password) throws IOException, UnexpectedResponseCodeException, InvalidCredentialsException;

}
