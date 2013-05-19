package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DepartmentService;
import com.mmf.business.domain.Department;
import com.mmf.rest.response.department.DepartmentResponse;
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
import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Service
@Path("department")
public class DepartmentResource extends CrudResource<Department, DepartmentService>{

    @Autowired
    private DepartmentService departmentService;

    @Override
    protected DepartmentService getService() {
        return departmentService;
    }

    @Override
    protected void validate(Department domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getName());
            DomainUtil.checkingForNotNull(domain.getDescription());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Department domain, Department newDomain) {
        domain.setName(newDomain.getName());
        domain.setDescription(newDomain.getDescription());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        try {
            Department domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            DepartmentResponse departmentResponse = new DepartmentResponse(domain);
            return Response.ok(departmentResponse).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.NOT_FOUND.getStatusCode());
        }
    }

    @Override
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<Department> departments = getService().list();
            List<DepartmentResponse> departmentResponses = new ArrayList<DepartmentResponse>(departments.size());
            for (Department department : departments) {
                DepartmentResponse departmentResponse = new DepartmentResponse(department);
                departmentResponses.add(departmentResponse);
            }
            return Response.ok(departmentResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/lecturers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialtyGroups(@PathParam("id") long id) {
        try {
            Department domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getLecturers()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.NOT_FOUND.getStatusCode());
        }
    }
}
