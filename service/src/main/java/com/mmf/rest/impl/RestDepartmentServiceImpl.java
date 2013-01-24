package com.mmf.rest.impl;

import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.DepartmentService;
import com.mmf.db.model.DepartmentEntity;
import com.mmf.rest.RestDepartmentService;
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
@Path("/department")
public class RestDepartmentServiceImpl implements RestDepartmentService{

    private static final Logger LOG = LoggerFactory.getLogger(RestDepartmentServiceImpl.class);

    @Inject
    private DepartmentService departmentService;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/create")
    @Override
    public Response create(DepartmentEntity department) {
        try {
            departmentService.create(department);
            return Response.ok(department).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Department doesn't create.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/update")
    @Override
    public Response update(DepartmentEntity department) {
        try {
            departmentService.update(department);
            return Response.ok(department).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Department doesn't update.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/get/{id}")
    @Override
    public Response get(@PathParam("id")Long id) {
        try {
            DepartmentEntity department = departmentService.get(id);
            return Response.ok(department).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Department with such id doesn't exist.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/delete/{id}")
    @Override
    public Response delete(@PathParam("id")Long id) {
        try {
            departmentService.delete(id);
            return Response.ok().build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Department with such id doesn't delete.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Produces("application/json")
    @Path("/list")
    @Override
    public Response list() {
        try {
            List<DepartmentEntity> departments = departmentService.list();
            return Response.ok(departments).build();
        } catch (BusinessServiceException e) {
            LOG.error("ERROR: Departments don't found.", e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
