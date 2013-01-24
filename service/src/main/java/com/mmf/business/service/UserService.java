package com.mmf.business.service;

import com.mmf.business.domain.User;
import com.mmf.db.model.UserEntity;

import javax.jws.WebService;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 3/30/12
 */
//@WebService
public interface UserService extends CrudService<Long, UserEntity>{

//    List<User> getAllLecturers();
}
