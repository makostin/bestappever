package com.mmf.rest.impl;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.ClassroomService;
import com.mmf.db.model.ClassroomEntity;
import com.mmf.rest.RestClassroomService;
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
@Path("/classroom")
public class RestClassroomServiceImpl implements RestClassroomService{

    private static final Logger LOG = LoggerFactory.getLogger(RestClassroomServiceImpl.class);

    @Inject
    private ClassroomService classroomService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    @Override
    public Response create(ClassroomEntity classroom) {
        try {
            classroomService.create(classroom);
            return Response.ok(classroom).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: classroom doesn't create.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    @Override
    public Response update(ClassroomEntity classroom) {
        try {
            classroomService.update(classroom);
            return Response.ok(classroom).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: classroom doesn't update.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/get/{id}")
    @Override
    public Response get(@PathParam("id")Long id) {
        try {
            ClassroomEntity classroom = classroomService.get(id);
            return Response.ok(classroom).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: classroom with such id doesn't exist.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/list")
    @Override
    public Response list() {
        try {
            List<ClassroomEntity> classrooms = classroomService.list();
            return Response.ok(classrooms).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: classrooms don't found.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/delete/{id}")
    @Override
    public Response delete(@PathParam("id")Long id) {
        try {
            classroomService.delete(id);
            return Response.ok().build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: classroom with such id doesn't delete.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
