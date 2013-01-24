package com.mmf.business.service.impl;

import com.mmf.business.service.SpecialtyService;
import com.mmf.db.dao.SpecialtyDao;
import com.mmf.db.model.SpecialtyEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: admin
 * Date: 21.10.12
 */
@Named
public class SpecialtyServiceImpl extends AbstractCrudService<Long, SpecialtyEntity, SpecialtyDao> implements SpecialtyService{

    @Inject
    private SpecialtyDao dao;

    @Override
    protected SpecialtyDao getDao() {
        return dao;
    }
}
