package com.mmf.business.service.impl;

import com.mmf.business.domain.User;
import com.mmf.business.service.BusinessServiceException;
import com.mmf.business.service.UserService;
import com.mmf.db.dao.UserDao;
import com.mmf.db.dao.jpa.UserDaoImpl;
import com.mmf.db.model.UserEntity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 3/30/12
 */
//@WebService(targetNamespace = "http://lecturers")
@Named
public class UserServiceImpl extends AbstractCrudService<Long, UserEntity, UserDao> implements UserService{

    @Inject
    private UserDao dao;


//    @Override
//    public List<User> getAllLecturers() {
//        List<User> lecturers = new ArrayList<User>();
//        User lecturer;
//        for (UserEntity entity: dao.getAllLecturers()){
//            lecturer = new User();
//            lecturer.setName(entity.getName());
//            lecturer.setDepartment(entity.getDepartment().getName());
//            lecturer.setId(entity.getId());
//            lecturers.add(lecturer);
//
//        }
//        return lecturers;
//    }

    @Override
    protected UserDao getDao() {
        return dao;
    }
}
