package com.mmf.rest;

import com.mmf.business.DisciplineTypeService;
import com.mmf.business.domain.DisciplineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

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
        // todo:
    }

    @Override
    protected void updateFields(DisciplineType domain, DisciplineType newDomain) {
        // todo:
    }


}
