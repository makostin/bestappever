package com.mmf.rest;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.UserService;
import com.mmf.business.domain.User;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NullPropertyException;
import com.mmf.rest.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
            DomainUtil.checkingForNotNull(domain.getPassword());
            DomainUtil.checkingForNotNull(domain.getAdmin());
            passwordGenerator.hashPassword(domain);
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
        domain.setPassword(newDomain.getPassword());
        domain.setPasswordSalt(newDomain.getPasswordSalt());
        domain.setPasswordFormat(newDomain.getPasswordFormat());
        domain.setAdmin(newDomain.getAdmin());
    }

}
