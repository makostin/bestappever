package com.mmf.rest;

import com.mmf.business.StudentService;
import com.mmf.business.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

/**
 * User: svetlana.voyteh
 * Date: 17.05.13
 */
@Service
@Path("user/student")
public class StudentResource extends CrudResource<Student, StudentService> {

    @Autowired
    private StudentService studentService;

    @Override
    protected StudentService getService() {
        return studentService;
    }

    @Override
    protected void validate(Student domain) {
        // todo:
    }

    @Override
    protected void updateFields(Student domain, Student newDomain) {
        // todo:
    }

}
