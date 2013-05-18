package com.mmf.rest;

import com.mmf.business.DisciplineService;
import com.mmf.business.domain.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

/**
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("discipline")
public class DisciplineResource extends CrudResource<Discipline, DisciplineService>{

    @Autowired
    private DisciplineService disciplineService;

    @Override
    protected DisciplineService getService() {
        return disciplineService;
    }

    @Override
    protected void validate(Discipline domain) {
        // todo:
    }

    @Override
    protected void updateFields(Discipline domain, Discipline newDomain) {
        // todo:
    }


}
