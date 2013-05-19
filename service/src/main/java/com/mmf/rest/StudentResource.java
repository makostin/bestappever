package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.StudentService;
import com.mmf.business.UserService;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.User;
import com.mmf.rest.response.student.StudentResponse;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NotNullPropertyException;
import com.mmf.rest.util.NullPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("user/student")
public class StudentResource extends CrudResource<Student, StudentService> {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Override
    protected StudentService getService() {
        return studentService;
    }

    @Override
    protected void validate(Student domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getId());
            DomainUtil.checkingForNotNull(domain.getPraepostor());
            DomainUtil.checkingForNotNull(domain.getYearOfEntrance());
            DomainUtil.checkingForNotNull(domain.getGroupId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Student domain, Student newDomain) {
        domain.setPraepostor(newDomain.getPraepostor());
        domain.setYearOfEntrance(newDomain.getYearOfEntrance());
        domain.setGroupId(newDomain.getGroupId());
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        try {
            Student domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(new StudentResponse(domain)).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.NOT_FOUND.getStatusCode());
        }
    }

    @Override
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Student domain) {
        try {
            validate(domain);
            User user = userService.get(domain.getId());
            if (user == null){
                throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
            }

            domain.setName(user.getName());
            domain.setSurname(user.getSurname());
            domain.setPatronymic(user.getPatronymic());
            domain.setLogin(user.getLogin());
            domain.setPassword(user.getPassword());
            domain.setPasswordSalt(user.getPasswordSalt());
            domain.setPasswordFormat(user.getPasswordFormat());
            domain.setAdmin(user.getAdmin());
            userService.delete(user.getId());
            getService().create(domain);
            return Response.ok().header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

}
