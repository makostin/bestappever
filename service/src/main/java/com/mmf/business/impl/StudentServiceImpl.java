package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.StudentService;
import com.mmf.business.domain.Student;
import com.mmf.business.domain.utils.GroupHelper;
import com.mmf.business.domain.utils.StudentHelper;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.GroupDao;
import com.mmf.db.dao.StudentDao;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.StudentEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
@Named
public class StudentServiceImpl extends AbstractCrudService<Long, Student, StudentEntity, StudentDao> implements StudentService {

    @Inject
    private StudentDao studentDao;

    @Inject
    private GroupDao groupDao;

    @Override
    protected StudentDao getDao() {
        return studentDao;
    }

    @Override
    public void convertToEntity(Student domain, StudentEntity entity) throws BusinessServiceException {
        if (domain != null) {
            try{
                StudentHelper.convertToEntity(domain, entity);

                GroupEntity groupEntity = groupDao.getEntityInstance(domain.getGroupId());
                if (groupEntity == null){
                    throw new BusinessServiceException("Such group doesn't exist.");
                }

                if (entity != null){
                    entity.setGroup(groupEntity);
                }
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to group entity error.", e);
            }
        }
    }

    @Override
    public Student convertToDomain(StudentEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }
        Student student = StudentHelper.convertToDomain(entity);
        student.setGroup(GroupHelper.convertToDomain(entity.getGroup()));

        return student;
    }

    @Override
    public Student getStudent(String login) throws BusinessServiceException {
        return convertToDomain(studentDao.getUser(login));
    }
}
