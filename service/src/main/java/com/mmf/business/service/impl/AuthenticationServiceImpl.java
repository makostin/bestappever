package com.mmf.business.service.impl;

import com.mmf.business.service.AuthenticationService;
import com.mmf.business.domain.User;
import com.mmf.db.dao.jpa.UserDaoImpl;
import com.mmf.db.model.UserEntity;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Calendar;

/**
 * @author svetlana.voyteh
 * @date: 3/7/12
 */
//@WebService(targetNamespace = "http://authenticate")
public class AuthenticationServiceImpl implements AuthenticationService {

//    private UserDaoImpl dao;
//
//    public AuthenticationServiceImpl() {
//        this.dao = new UserDaoImpl();
//    }
//
//    @Override
//    public User authenticate(@WebParam(name = "login") String login,
//                             @WebParam(name = "password") String pass) {
//        UserEntity entity = dao.getUser(login, pass);
//        User user = null;
//        if (entity != null){
//            user = new User();
//            user.setName(entity.getName());
//            user.setGroup(entity.getGroup().getNumber());
//            user.setCourse(Calendar.getInstance().get(Calendar.YEAR) - entity.getGroup().getYear());
//        }
//        return user;
//    }
}
