package com.mmf.business.service.impl;

import com.mmf.business.service.ClassroomService;
import com.mmf.db.dao.ClassroomDao;
import com.mmf.db.dao.jpa.ClassroomDaoImpl;
import com.mmf.db.model.ClassroomEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@Named
public class ClassroomServiceImpl extends AbstractCrudService<Long, ClassroomEntity, ClassroomDao> implements ClassroomService{

    @Inject
    private ClassroomDao dao;

    @Override
    protected ClassroomDao getDao() {
        return dao;
    }
}
