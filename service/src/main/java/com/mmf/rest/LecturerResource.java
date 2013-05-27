package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.User;
import com.mmf.rest.response.lecturer.LecturerResponse;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NullPropertyException;
import com.mmf.rest.util.PasswordGenerator;
import com.mmf.rest.util.UserRoleUtil;
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
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("user/lecturer")
public class LecturerResource extends CrudResource<Lecturer, LecturerService> {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Override
    protected LecturerService getService() {
        return lecturerService;
    }

    @Override
    protected void validate(Lecturer domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getName());
            DomainUtil.checkingForNotNull(domain.getSurname());
            DomainUtil.checkingForNotNull(domain.getPatronymic());
            DomainUtil.checkingForNotNull(domain.getLogin());
            DomainUtil.checkingForNotNull(domain.getAdmin());
            if (domain.getId() == null){
                DomainUtil.checkingForNotNull(domain.getPassword());
                passwordGenerator.hashPassword(domain);
            } else if (domain.getPassword() != null && !"".equals(domain.getPassword())){
                passwordGenerator.hashPassword(domain);
            }
            DomainUtil.checkingForNotNull(domain.getDepartmentId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @Override
    protected void updateFields(Lecturer domain, Lecturer newDomain) {
        domain.setName(newDomain.getName());
        domain.setSurname(newDomain.getSurname());
        domain.setPatronymic(newDomain.getPatronymic());
        domain.setLogin(newDomain.getLogin());
        if (newDomain.getPassword() != null && !"".equals(newDomain.getPassword())){
            domain.setPassword(newDomain.getPassword());
            domain.setPasswordSalt(newDomain.getPasswordSalt());
            domain.setPasswordFormat(newDomain.getPasswordFormat());
        }
        domain.setAdmin(newDomain.getAdmin());
        domain.setDepartmentId(newDomain.getDepartmentId());
    }


    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        try {
            Lecturer domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            UserRoleUtil.setRoles(domain);
            return Response.ok(new LecturerResponse(domain)).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        try {
            List<Lecturer> lecturers = getService().list();
            List<LecturerResponse> lecturerResponses = new LinkedList<LecturerResponse>();
            for(Lecturer lecturer : lecturers){
                UserRoleUtil.setRoles(lecturer);
                lecturerResponses.add(new LecturerResponse(lecturer));
            }
            return Response.ok(lecturerResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/department")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentsOfGroup(@PathParam("id") long id) {
        try {
            Lecturer domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getDepartment()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }

}
