package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.UserService;
import com.mmf.business.domain.User;
import com.mmf.rest.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.security.util.Password;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 15.05.13
 */

@Service
@Path("user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordGenerator passwordGenerator;


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user){
        try {
            passwordGenerator.hashPassword(user);
            userService.create(user);
            return Response.ok().header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @POST
    @Path("/{id}/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, User newUser){
        try {
            User user = userService.get(id);
            if(newUser.getPassword() != null){
                passwordGenerator.hashPassword(newUser);
            }
            userService.update(updateFields(user, newUser));
            return Response.ok().header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    private User updateFields(User user, User newUser) {
        user.setName(ifNotNull(user.getName(), newUser.getName()));
        user.setSurname(ifNotNull(user.getSurname(), newUser.getSurname()));
        user.setPatronymic(ifNotNull(user.getPatronymic(), newUser.getPatronymic()));
        user.setLogin(ifNotNull(user.getLogin(), newUser.getLogin()));
        user.setPassword(ifNotNull(user.getPassword(), newUser.getPassword()));
        user.setPasswordSalt(ifNotNull(user.getPasswordSalt(), newUser.getPasswordSalt()));
        user.setAdmin(ifNotNull(user.getAdmin(), newUser.getAdmin()));
        return user;
    }

    private String ifNotNull(String old, String cur) {
        return cur != null ? cur : old;
    }

    private Boolean ifNotNull(Boolean old, Boolean cur) {
        return cur != null ? cur : old;
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id){
        try {
            User user = userService.get(id);
            return Response.ok(user).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id){
        try {
            userService.delete(id);
            return Response.ok().header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        try {
            List<User> users = userService.list();
            return Response.ok(users).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
