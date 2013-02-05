package com.mmf.business.service.impl;

import com.mmf.business.service.TimetableCellService;
import com.mmf.db.dao.TimetableCellDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: admin
 * Date: 21.10.12
 */
@Named
public class TimetableCellServiceImpl extends AbstractCrudService<Long, TimetableCellEntity, TimetableCellDao> implements TimetableCellService{

    @Inject
    private TimetableCellDao dao;

    @Override
    protected TimetableCellDao getDao() {
        return dao;
    }
}
