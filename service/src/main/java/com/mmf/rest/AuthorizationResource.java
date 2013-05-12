package com.mmf.rest;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
@Service
@Path("login")
public class AuthorizationResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(){
        return Response.ok().header("Content-Encoding", "utf-8").build();
    }
}
