package com.mmf.business;

import com.mmf.business.domain.Student;
import com.mmf.business.domain.User;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
public interface StudentService extends CrudService<Long, Student>{
    Student getStudent(String login) throws BusinessServiceException;
}
