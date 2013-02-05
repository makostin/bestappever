package com.mmf.business.service.impl;

import com.mmf.business.service.UserRoleService;
import com.mmf.db.dao.UserRoleDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: admin
 * Date: 21.10.12
 */
@Named
public class UserRoleServiceImpl extends AbstractCrudService<Long, UserRoleEntity, UserRoleDao> implements UserRoleService{

    @Inject
    private UserRoleDao dao;

    @Override
    protected UserRoleDao getDao() {
        return dao;
    }
}
