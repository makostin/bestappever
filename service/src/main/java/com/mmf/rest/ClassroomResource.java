package com.mmf.rest;

import com.mmf.business.ClassroomService;
import com.mmf.business.domain.Classroom;
import com.mmf.business.domain.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

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
        // todo:
    }

    @Override
    protected void updateFields(Classroom domain, Classroom newDomain) {
        // todo:
    }


}
