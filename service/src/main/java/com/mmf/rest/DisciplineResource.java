package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DisciplineService;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.User;
import com.mmf.rest.response.discipline.DisciplineResponse;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NullPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("discipline")
public class DisciplineResource extends CrudResource<Discipline, DisciplineService>{

    @Autowired
    private DisciplineService disciplineService;

    @Override
    protected DisciplineService getService() {
        return disciplineService;
    }

    @Override
    protected void validate(Discipline domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getName());
            DomainUtil.checkingForNotNull(domain.getDisciplineTypeId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Discipline domain, Discipline newDomain) {
        domain.setName(newDomain.getName());
        domain.setDisciplineTypeId(newDomain.getDisciplineTypeId());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        try {
            Discipline domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            DisciplineResponse disciplineResponse = new DisciplineResponse(domain);
            return Response.ok(disciplineResponse).header("Content-Encoding", "utf-8").build();
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
            List<Discipline> disciplines = getService().list();
            List<DisciplineResponse> disciplineResponses = new LinkedList<DisciplineResponse> ();
            for (Discipline discipline : disciplines) {
                DisciplineResponse specialtyResponse = new DisciplineResponse(discipline);
                disciplineResponses.add(specialtyResponse);
            }
            return Response.ok(disciplineResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/disciplineType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialtyGroups(@PathParam("id") long id) {
        try {
            Discipline domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getDisciplineType()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

}
