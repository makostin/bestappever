package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.StudentService;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.utils.StudentHelper;
import com.mmf.db.dao.StudentDao;
import com.mmf.db.model.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
@Named
public class StudentServiceImpl extends AbstractCrudService<Long, Student, StudentEntity, StudentDao> implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    protected StudentDao getDao() {
        return studentDao;
    }

    @Override
    public void convertToEntity(Student domain, StudentEntity entity) throws BusinessServiceException {
        if (domain != null){
            StudentHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public Student convertToDomain(StudentEntity entity) throws BusinessServiceException {
        if (entity == null){
            return null;
        }
        return StudentHelper.convertToDomain(entity);
    }
}
