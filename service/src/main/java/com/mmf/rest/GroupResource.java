package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.GroupService;
import com.mmf.business.domain.Group;
import com.mmf.rest.response.group.GroupResponse;
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
import java.util.LinkedList;
import java.util.List;

/**
 * svetlana.voyteh
 * 12.03.13
 */
@Service
@Path("group")
public class GroupResource extends CrudResource<Group, GroupService> {

    @Autowired
    private GroupService groupService;


    protected GroupService getService() {
        return groupService;
    }

    @Override
    protected void validate(Group domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getNumber());
            DomainUtil.checkingForNotNull(domain.getCourse());
            if (domain.getSubgroup() != null && !"a".equals(domain.getSubgroup()) && !"b".equals(domain.getSubgroup())) {
                throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
            }
            DomainUtil.checkingForNotNull(domain.getYear());
            DomainUtil.checkingForNotNull(domain.getSpecialtyId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Group domain, Group newDomain) {
        domain.setCourse(newDomain.getCourse());
        domain.setNumber(newDomain.getNumber());
        if (domain.getSubgroup() == null && newDomain.getSubgroup() != null) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
        if (domain.getSubgroup() != null && newDomain.getSubgroup() == null) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
        domain.setSubgroup(newDomain.getSubgroup());
        domain.setYear(newDomain.getYear());
        domain.setSpecialtyId(newDomain.getSpecialtyId());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        try {
            Group domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(new GroupResponse(domain)).header("Content-Encoding", "utf-8").build();
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
    public Response list(){
        try {
            List<GroupResponse> groupResponses = new LinkedList<GroupResponse>();
            for(Group group : getService().list()){
                groupResponses.add(new GroupResponse(group));
            }
            return Response.ok(groupResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/specialty")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialtyOfGroup(@PathParam("id") long id) {
        try {
            Group domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getSpecialty()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.NOT_FOUND.getStatusCode());
        }
    }

    @GET
    @Path("/{id}/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentsyOfGroup(@PathParam("id") long id) {
        try {
            Group domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getStudents()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.NOT_FOUND.getStatusCode());
        }
    }
}
