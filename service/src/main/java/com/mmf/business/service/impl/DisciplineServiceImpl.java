package com.mmf.business.service.impl;

import com.mmf.business.service.DisciplineService;
import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.model.DisciplineEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@Named
public class DisciplineServiceImpl extends AbstractCrudService<Long, DisciplineEntity, DisciplineDao> implements DisciplineService {

    @Inject
    private DisciplineDao dao;


    @Override
    protected DisciplineDao getDao() {
        return dao;
    }
}
