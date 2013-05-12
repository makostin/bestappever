package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.UserService;
import com.mmf.business.domain.User;
import com.mmf.business.domain.utils.UserHelper;
import com.mmf.db.dao.UserDao;
import com.mmf.db.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
@Named
public class UserServiceImpl extends AbstractCrudService<Long, User, UserEntity, UserDao> implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    protected UserDao getDao() {
        return userDao;
    }



    @Override
    public void convertToEntity(User domain, UserEntity entity) throws BusinessServiceException {
        if (domain != null){
            UserHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public User convertToDomain(UserEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        return UserHelper.convertToDomain(entity);
    }

    @Override
    @Transactional(rollbackFor = BusinessServiceException.class)
    public User getUser(String login) throws BusinessServiceException {
        return convertToDomain(userDao.getUser(login));
    }
}
