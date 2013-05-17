package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.CrudService;
import com.mmf.business.domain.DomainClass;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
public abstract class CrudResource<T extends DomainClass<Long>, S extends CrudService<Long, T>> {

    protected abstract S getService();       
    protected abstract void validate(T domain);
    protected abstract void updateFields(T domain, T newDomain);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        try {
            T domain = getService().get(id);
            // todo: if domain == null return StatusCode=404
            return Response.ok(domain).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }



    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(T domain){
        try {
            getService().create(domain);
            // todo: validate domain fields
            validate(domain);
            return Response.ok().header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }


    @POST
    @Path("/{id}/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") long id, T newDomain){
        try {
            T domain = getService().get(id);
            // todo: reset fields from newDomain to domain
            updateFields(domain, newDomain);
            getService().update(domain);
            return Response.ok().header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") long id){
        try {
            getService().delete(id);
            return Response.ok().header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        try {
            return Response.ok(getService().list()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
