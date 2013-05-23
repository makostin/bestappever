package com.mmf.rest;

import com.mmf.business.DisciplineTimeService;
import com.mmf.business.DisciplineTypeService;
import com.mmf.business.domain.DisciplineTime;
import com.mmf.business.domain.DisciplineType;
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
@Path("disciplineTime")
public class DisciplineTimeResource extends CrudResource<DisciplineTime, DisciplineTimeService>{

    @Autowired
    private DisciplineTimeService disciplineTimeService;

    @Override
    protected DisciplineTimeService getService() {
        return disciplineTimeService;
    }

    @Override
    protected void validate(DisciplineTime domain) {
        try {
            DomainUtil.checkingForNotNull(domain.getNumber());
            DomainUtil.checkingForNotNull(domain.getEndTime());
            DomainUtil.checkingForNotNull(domain.getStartTime());
            DomainUtil.checkingForNotNull(domain.getBreakTime());
        } catch (NullPropertyException e) {
            throw new RestServiceException(Response.Status.BAD_REQUEST.getStatusCode());
        }

    }

    @Override
    protected void updateFields(DisciplineTime domain, DisciplineTime newDomain) {
        domain.setNumber(newDomain.getNumber());
        domain.setStartTime(newDomain.getStartTime());
        domain.setEndTime(newDomain.getEndTime());
        domain.setBreakTime(newDomain.getBreakTime());
    }


}
