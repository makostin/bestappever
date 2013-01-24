package com.mmf.db.dao;

import com.mmf.db.model.UserEntity;

import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public interface UserDao extends GenericDao<Long, UserEntity>{

    List<UserEntity> getAllLecturers();
}
