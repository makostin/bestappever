package com.mmf.db.dao.jpa;

import com.mmf.db.dao.UserDao;
import com.mmf.db.model.UserEntity;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public class UserDaoImpl extends GenericJpaDao<Long, UserEntity> implements UserDao{


    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }
}
