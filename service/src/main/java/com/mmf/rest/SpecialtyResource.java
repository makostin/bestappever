package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.SpecialtyService;
import com.mmf.business.domain.Specialty;
import com.mmf.rest.response.specialty.SpecialtyResponse;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NullPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * svetlana.voyteh
 * 06.03.13
 */
@Service
@Path("specialty")
public class SpecialtyResource extends CrudResource<Specialty, SpecialtyService> {

    @Autowired
    private SpecialtyService specialtyService;


    @Override
    protected SpecialtyService getService() {
        return specialtyService;
    }

    @Override
    protected void validate(Specialty domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getName());
            DomainUtil.checkingForNotNull(domain.getDescription());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Specialty domain, Specialty newDomain) {
        domain.setName(newDomain.getName());
        domain.setDescription(newDomain.getDescription());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        try {
            Specialty domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            SpecialtyResponse specialtyResponse = new SpecialtyResponse(domain);
            return Response.ok(specialtyResponse).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @Override
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<Specialty> specialties = getService().list();
            List<SpecialtyResponse> specialtyResponses = new LinkedList<SpecialtyResponse> ();
            for (Specialty specialty : specialties) {
                SpecialtyResponse specialtyResponse = new SpecialtyResponse(specialty);
                specialtyResponses.add(specialtyResponse);
            }
            return Response.ok(specialtyResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialtyGroups(@PathParam("id") long id) {
        try {
            Specialty domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getGroups()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }
}
