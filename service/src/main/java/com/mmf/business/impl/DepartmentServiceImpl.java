package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DepartmentService;
import com.mmf.business.domain.Department;
import com.mmf.business.domain.utils.DepartmentHelper;
import com.mmf.business.domain.utils.LecturerHelper;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.DepartmentDao;
import com.mmf.db.model.DepartmentEntity;
import com.mmf.db.model.LecturerEntity;
import com.mmf.rest.response.department.DepartmentResponse;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;
import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class DepartmentServiceImpl extends AbstractCrudService<Long, Department, DepartmentEntity, DepartmentDao> implements DepartmentService{

    @Inject
    private DepartmentDao departmentDao;

    @Override
    protected DepartmentDao getDao() {
        return departmentDao;
    }

    @Override
    public void convertToEntity(Department domain, DepartmentEntity entity) throws BusinessServiceException {
        if (domain != null){
            DepartmentHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public Department convertToDomain(DepartmentEntity entity) throws BusinessServiceException {
        if (entity == null){
            return null;
        }
        Department department = new Department();
        department.setId(entity.getId());
        department.setName(entity.getName());
        department.setDescription(entity.getDescription());

        for (LecturerEntity lecturerEntity : entity.getLecturers()){
            department.getLecturers().add(LecturerHelper.convertToDomain(lecturerEntity));
        }
        return department;
    }

    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public List<DepartmentResponse> listDepartments() throws BusinessServiceException {
        try {
            List<Department> departments = convertToDomain(departmentDao.list());
            List<DepartmentResponse> departmentResponses = new LinkedList<DepartmentResponse>();
            for(Department department : departments){
                departmentResponses.add(new DepartmentResponse(department));
            }
            return departmentResponses;
        } catch (DataAccessException e) {
            throw new BusinessServiceException(e);
        }
    }
}
