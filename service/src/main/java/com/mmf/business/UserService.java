package com.mmf.business;

import com.mmf.business.domain.User;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
public interface UserService extends CrudService<Long, User> {
    
    User getUser(String login) throws BusinessServiceException;
}
