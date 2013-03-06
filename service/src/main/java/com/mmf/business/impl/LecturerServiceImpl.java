package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.LecturerService;
import com.mmf.business.domain.Department;
import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.utils.DepartmentHelper;
import com.mmf.business.domain.utils.LecturerHelper;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.DepartmentDao;
import com.mmf.db.dao.LecturerDao;
import com.mmf.db.model.DepartmentEntity;
import com.mmf.db.model.LecturerEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class LecturerServiceImpl extends AbstractCrudService<Long, Lecturer, LecturerEntity, LecturerDao> implements LecturerService{

    @Inject
    private DepartmentDao departmentDao;

    @Inject
    private LecturerDao lecturerDao;

    @Override
    protected LecturerDao getDao() {
        return lecturerDao;
    }

    @Override
    public void convertToEntity(Lecturer domain, LecturerEntity entity) throws BusinessServiceException {
        if (domain != null){
            try {
                Department department = domain.getDepartment();
                if (department != null){
                    DepartmentEntity departmentEntity = departmentDao.getEntityInstance(department.getId());
                    DepartmentHelper.convertToEntity(department, departmentEntity);
                }
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to lecturer entity error.", e);
            }
            LecturerHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public Lecturer convertToDomain(LecturerEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Lecturer lecturer = LecturerHelper.convertToDomain(entity);
        lecturer.setDepartment(DepartmentHelper.convertToDomain(entity.getDepartment()));
        return lecturer;
    }
}
