package com.mmf.business.service.impl;

import com.mmf.business.service.GroupService;
import com.mmf.db.dao.GroupDao;
import com.mmf.db.model.GroupEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: admin
 * Date: 21.10.12
 */
@Named
public class GroupServiceImpl extends AbstractCrudService<Long, GroupEntity, GroupDao> implements GroupService{

    @Inject
    private GroupDao dao;

    @Override
    protected GroupDao getDao() {
        return dao;
    }
}
