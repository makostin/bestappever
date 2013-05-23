package com.mmf.business;

import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.User;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public interface LecturerService extends CrudService<Long, Lecturer>{
    Lecturer getLecturer(String login) throws BusinessServiceException;
}
