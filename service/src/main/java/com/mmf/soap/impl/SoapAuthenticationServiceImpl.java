package com.mmf.soap.impl;

import com.mmf.business.domain.User;
import com.mmf.db.dao.jpa.UserDaoImpl;
import com.mmf.db.model.UserEntity;
import com.mmf.soap.SoapAuthenticationService;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Calendar;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@WebService(targetNamespace = "http://authenticate")
public class SoapAuthenticationServiceImpl implements SoapAuthenticationService{

    private UserDaoImpl dao;

    public SoapAuthenticationServiceImpl() {
        this.dao = new UserDaoImpl();
    }

    @Override
    public User authenticate(@WebParam(name = "login") String login,
                             @WebParam(name = "password") String pass) {
        UserEntity entity = dao.getUser(login, pass);
        User user = null;
        if (entity != null){
            user = new User();
            user.setName(entity.getName());
            user.setSubGroup(entity.getGroup().getNumber());
            user.setCourse(Calendar.getInstance().get(Calendar.YEAR) - entity.getGroup().getYear());
        }
        return user;
    }
}
