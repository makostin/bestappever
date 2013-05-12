package com.mmf.business.domain.utils;


import com.mmf.business.domain.User;
import com.mmf.db.model.UserEntity;

/**
 * svetlana.voyteh
 * 13.03.13
 */
public class UserHelper {

    /**
     * Convert User to User entity.
     *
     * @param domain User
     * @param entity UserEntity
     */
    public static void convertToEntity(User domain, UserEntity entity) {
        if (entity != null) {
            entity.setLogin(domain.getLogin());
            entity.setPassword(domain.getPassword());
            entity.setPasswordFormat(domain.getPasswordFormat());
            entity.setPasswordSalt(domain.getPasswordSalt());
            entity.setName(domain.getName());
            entity.setSurname(domain.getSurname());
            entity.setPatronymic(domain.getPatronymic());
            entity.setAdmin(domain.getAdmin());
        }
    }

    /**
     * Convert User entity to User.
     *
     * @param entity UserEntity
     * @return User
     */
    public static User convertToDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setLogin(entity.getLogin());
        user.setPassword(entity.getPassword());
        user.setPasswordFormat(entity.getPasswordFormat());
        user.setPasswordSalt(entity.getPasswordSalt());
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setPatronymic(entity.getPatronymic());
        user.setAdmin(entity.getAdmin());
        user.setFullName(entity.getSurname() + " " + entity.getName() + " " + entity.getPatronymic());
        return user;
    }
}
