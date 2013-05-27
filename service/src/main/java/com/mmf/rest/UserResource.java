package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.UserService;
import com.mmf.business.domain.User;
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
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 15.05.13
 */

@Service
@Path("user")
public class UserResource extends CrudResource<User, UserService> {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Override
    protected UserService getService() {
        return userService;
    }

    @Override
    protected void validate(User domain) {
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
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }

    @Override
    protected void updateFields(User domain, User newDomain) {
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
    }

    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id){
        try {
            User domain = getService().get(id);
            DomainUtil.checkingForNotNull(domain);
            UserRoleUtil.setRoles(domain);
            return Response.ok(domain).header("Content-Encoding", "utf-8").build();
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
            List<User> users = getService().list();
            for(User user : users){
                UserRoleUtil.setRoles(user);
            }
            return Response.ok(users).header("Content-Encoding", "utf-8").build();
        } catch (BusinessServiceException e) {
            throw new RestServiceException(e.getErrorCode());
        }
    }
}
