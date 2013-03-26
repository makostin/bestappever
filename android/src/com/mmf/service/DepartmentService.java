package com.mmf.service;

import com.mmf.db.dao.impl.DepartmentDao;
import com.mmf.db.model.Department;
import com.mmf.util.EntityRegistry;

import java.util.List;

/**
 * svetlana.voyteh
 * 22.03.13
 */
public class DepartmentService {

    private final DepartmentDao departmentDao = (DepartmentDao) EntityRegistry.get().getEntityDao(Department.class);

    public List<Department> list(){
        return departmentDao.selectAll();
    }

    public Department getDepartment(long id) {
        return departmentDao.get(id);
    }
}
