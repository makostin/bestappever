package com.mmf.rest;

import com.mmf.business.ClassroomService;
import com.mmf.business.domain.Classroom;
import com.mmf.business.domain.User;
import com.mmf.rest.util.DomainUtil;
import com.mmf.rest.util.NullPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("classroom")
public class ClassroomResource extends CrudResource<Classroom, ClassroomService>{

    @Autowired
    private ClassroomService classroomService;

    @Override
    protected ClassroomService getService() {
        return classroomService;
    }

    @Override
    protected void validate(Classroom domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getNumber());
            DomainUtil.checkingForNotNull(domain.getCapacity());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }
    }

    @Override
    protected void updateFields(Classroom domain, Classroom newDomain) {
        domain.setNumber(newDomain.getNumber());
        domain.setCapacity(newDomain.getCapacity());
    }


}
