package com.mmf.soap.impl;

import com.mmf.business.domain.User;
import com.mmf.db.dao.UserDao;
import com.mmf.db.dao.jpa.UserDaoImpl;
import com.mmf.db.model.UserEntity;
import com.mmf.soap.SoapUserService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@WebService(targetNamespace = "http://lecturers")
public class SoapUserServiceImpl implements SoapUserService{

    private UserDao dao;

    public SoapUserServiceImpl() {
        dao = new UserDaoImpl();
    }

    @Override
    public List<User> getAllLecturers() {
        List<User> lecturers = new ArrayList<User>();
        User lecturer;
        for (UserEntity entity: dao.getAllLecturers()){
            lecturer = new User();
            lecturer.setName(entity.getName());
            lecturer.setDepartment(entity.getDepartment().getName());
            lecturer.setId(entity.getId());
            lecturers.add(lecturer);

        }
        return lecturers;
    }
}
