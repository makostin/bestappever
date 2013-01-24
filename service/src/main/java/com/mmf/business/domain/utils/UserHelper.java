package com.mmf.business.domain.utils;

import com.mmf.business.domain.User;
import com.mmf.db.model.UserEntity;

import java.util.Calendar;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public final class UserHelper {

    /**
     * Hided constructor
     */
    private UserHelper() {
    }

    /**
     * Converts user to user entity.
     * @param domain User
     * @param entity UserEntity
     */
    public static void convertToEntity(User domain, UserEntity entity) {
        if (domain != null && entity != null) {
            entity.setName(domain.getName());
        }
    }

    /**
     * Converts user entity to user.
     * @param entity UserEntity
     * @return User
     */
    public static User convertToDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        User domain = new User();
        domain.setId(entity.getId());
        domain.setDepartment(entity.getDepartment().getName());

        Calendar dateOfEntrance = Calendar.getInstance();
        dateOfEntrance.setTime(entity.getDateOfEntrance());
        int course = Calendar.getInstance().get(Calendar.YEAR)-dateOfEntrance.get(Calendar.YEAR);
        domain.setCourse(course);


        domain.setSubGroup(entity.getGroup().getSubGroup());
        domain.setGroup(entity.getGroup().getNumber());
        domain.setName(entity.getName());
        domain.setSurname(entity.getSurname());

        return domain;
    }
}
