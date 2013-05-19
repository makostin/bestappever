package com.mmf.rest;

import com.mmf.business.DisciplineTypeService;
import com.mmf.business.domain.DisciplineType;
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
@Path("disciplineType")
public class DisciplineTypeResource extends CrudResource<DisciplineType, DisciplineTypeService>{

    @Autowired
    private DisciplineTypeService disciplineTypeService;

    @Override
    protected DisciplineTypeService getService() {
        return disciplineTypeService;
    }

    @Override
    protected void validate(DisciplineType domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getType());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }

    }

    @Override
    protected void updateFields(DisciplineType domain, DisciplineType newDomain) {
        domain.setType(newDomain.getType());
    }


}
