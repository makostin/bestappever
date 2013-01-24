package com.mmf.business.service.impl;

import com.mmf.business.service.DepartmentService;
import com.mmf.db.dao.DepartmentDao;
import com.mmf.db.dao.jpa.DepartmentDaoImpl;
import com.mmf.db.model.DepartmentEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@Named
public class DepartmentServiceImpl extends AbstractCrudService<Long, DepartmentEntity, DepartmentDao> implements DepartmentService{

    @Inject
    private DepartmentDao dao;


    @Override
    protected DepartmentDao getDao() {
        return dao;
    }
}
