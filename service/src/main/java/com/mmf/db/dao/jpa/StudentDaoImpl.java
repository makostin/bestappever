package com.mmf.db.dao.jpa;

import com.mmf.db.dao.StudentDao;
import com.mmf.db.model.StudentEntity;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
@Named
public class StudentDaoImpl extends GenericJpaDao<Long, StudentEntity> implements StudentDao{
    @Override
    protected Class<StudentEntity> getEntityClass() {
        return StudentEntity.class;
    }
}
