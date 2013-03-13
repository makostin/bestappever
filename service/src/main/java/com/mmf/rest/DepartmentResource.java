package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DepartmentService;
import com.mmf.business.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Service
@Path("department")
public class DepartmentResource {

    @Autowired
    private DepartmentService departmentService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartments(){
        try {
            List<Department> departmentList = departmentService.list();
            return Response.ok(departmentList).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

}
