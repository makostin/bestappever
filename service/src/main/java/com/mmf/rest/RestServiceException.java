package com.mmf.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Class to define exception for thew rest web service
 *
 *
 * svetlana.voyteh
 * 06.03.13
 */
public class RestServiceException extends WebApplicationException {
    public RestServiceException(int errorCode) {
        super(Response.status(errorCode).build());
    }
}