package com.mmf.business.service.impl;

import com.mmf.business.service.SettingsService;
import com.mmf.db.dao.SettingsDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: admin
 * Date: 21.10.12
 */
@Named
public class SettingsServiceImpl extends AbstractCrudService<Long, SettingsEntity, SettingsDao> implements SettingsService{

    @Inject
    private SettingsDao dao;

    @Override
    protected SettingsDao getDao() {
        return dao;
    }
}
