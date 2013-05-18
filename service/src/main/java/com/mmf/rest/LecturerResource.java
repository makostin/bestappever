package com.mmf.rest;

import com.mmf.business.LecturerService;
import com.mmf.business.domain.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

/**
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("user/lecturer")
public class LecturerResource extends CrudResource<Lecturer, LecturerService> {

    @Autowired
    private LecturerService lecturerService;

    @Override
    protected LecturerService getService() {
        return lecturerService;
    }

    @Override
    protected void validate(Lecturer domain) {
        // todo:
    }

    @Override
    protected void updateFields(Lecturer domain, Lecturer newDomain) {
        // todo:
    }

}
