package com.mmf.rest.impl;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.DisciplineService;
import com.mmf.db.model.DisciplineEntity;
import com.mmf.rest.RestSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/13/12
 */
@Path("/discipline")
public class RestDisciplineServiceImpl implements RestSubjectService{

    private static final Logger LOG = LoggerFactory.getLogger(RestDisciplineServiceImpl.class);

    @Inject
    private DisciplineService disciplineService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    @Override
    public Response create(DisciplineEntity discipline) {
        try {
            disciplineService.create(discipline);
            return Response.ok(discipline).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Subject isn't created.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    @Override
    public Response update(DisciplineEntity discipline) {
        try {
            disciplineService.update(discipline);
            return Response.ok(discipline).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Subject isn't updated.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/get/{id}")
    @Override
    public Response get(@PathParam("id")Long id) {
        try {
            DisciplineEntity discipline = disciplineService.get(id);
            return Response.ok(discipline).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Subject with such id isn't existed.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/delete/{id}")
    @Override
    public Response delete(@PathParam("id")Long id) {
        try {
            disciplineService.delete(id);
            return Response.ok().build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Subject with such id isn't deleted.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/list")
    @Override
    public Response list() {
        try {
            List<DisciplineEntity> disciplines = disciplineService.list();
            return Response.ok(disciplines).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Subjects isn't found.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
