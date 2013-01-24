package com.mmf.db.dao.jpa;

import com.mmf.db.dao.UserRoleDao;
import com.mmf.db.model.UserRoleEntity;

import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 3/30/12
 */
@Named
public class UserRoleDaoImpl extends GenericJpaDao<Long, UserRoleEntity> implements UserRoleDao{

    @Override
    protected Class<UserRoleEntity> getEntityClass() {
        return UserRoleEntity.class;
    }
}
