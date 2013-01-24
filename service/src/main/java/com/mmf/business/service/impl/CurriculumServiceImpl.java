package com.mmf.business.service.impl;

import com.mmf.business.service.CurriculumService;
import com.mmf.db.dao.CurriculumDao;
import com.mmf.db.model.CurriculumEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: admin
 * Date: 21.10.12
 */
@Named
public class CurriculumServiceImpl extends AbstractCrudService<Long, CurriculumEntity, CurriculumDao> implements CurriculumService{

    @Inject
    private CurriculumDao dao;

    @Override
    protected CurriculumDao getDao() {
        return dao;
    }
}
