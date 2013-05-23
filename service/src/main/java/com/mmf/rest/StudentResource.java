package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.StudentService;
import com.mmf.business.UserService;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.User;
import com.mmf.rest.response.student.StudentResponse;
import com.mmf.rest.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

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
    private PasswordGenerator passwordGenerator;


    @Override
    protected StudentService getService() {
        return studentService;
    }

    @Override
    protected void validate(Student domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getName());
            DomainUtil.checkingForNotNull(domain.getSurname());
            DomainUtil.checkingForNotNull(domain.getPatronymic());
            DomainUtil.checkingForNotNull(domain.getLogin());
            DomainUtil.checkingForNotNull(domain.getPassword());
            DomainUtil.checkingForNotNull(domain.getAdmin());
            passwordGenerator.hashPassword(domain);
            DomainUtil.checkingForNotNull(domain.getPraepostor());
            DomainUtil.checkingForNotNull(domain.getYearOfEntrance());
            DomainUtil.checkingForNotNull(domain.getGroupId());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @Override
    protected void updateFields(Student domain, Student newDomain) {
        domain.setName(newDomain.getName());
        domain.setSurname(newDomain.getSurname());
        domain.setPatronymic(newDomain.getPatronymic());
        domain.setLogin(newDomain.getLogin());
        domain.setPassword(newDomain.getPassword());
        domain.setPasswordSalt(newDomain.getPasswordSalt());
        domain.setPasswordFormat(newDomain.getPasswordFormat());
        domain.setAdmin(newDomain.getAdmin());
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
            UserRoleUtil.setRoles(domain);
            return Response.ok(new StudentResponse(domain)).header("Content-Encoding", "utf-8").build();
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
    public Response list(){
        try {
            List<Student> students = getService().list();
            List<StudentResponse> studentResponses = new LinkedList<StudentResponse>();
            for(Student student : students){
                UserRoleUtil.setRoles(student);
                studentResponses.add(new StudentResponse(student));
            }
            return Response.ok(studentResponses).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/group")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecialtyGroups(@PathParam("id") long id) {
        try {
            Student domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            return Response.ok(domain.getGroup()).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        } catch (NullPropertyException e) {
            return Response.noContent().build();
        }
    }
}
