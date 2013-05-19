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

    private static final long serialVersionUID = 7629926072406542317L;

    public RestServiceException(int errorCode) {
        super(Response.status(errorCode == 0 ?  Response.Status.INTERNAL_SERVER_ERROR.getStatusCode() : errorCode).build());
    }
}