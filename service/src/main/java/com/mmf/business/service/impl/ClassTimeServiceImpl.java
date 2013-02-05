package com.mmf.business.service.impl;

import com.mmf.business.service.ClassTimeService;
import com.mmf.db.dao.ClassTimeDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: admin
 * Date: 21.10.12
 */
@Named
public class ClassTimeServiceImpl extends AbstractCrudService<Long, ClassTimeEntity, ClassTimeDao> implements ClassTimeService{

    @Inject
    private ClassTimeDao dao;

    @Override
    protected ClassTimeDao getDao() {
        return dao;
    }
}
