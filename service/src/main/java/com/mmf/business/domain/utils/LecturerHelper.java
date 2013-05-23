package com.mmf.business.domain.utils;

import com.mmf.business.domain.Lecturer;
import com.mmf.db.model.LecturerEntity;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public class LecturerHelper {

    /**
     * Convert lecturer to lecturer entity.
     *
     * @param domain Lecturer
     * @param entity LecturerEntity
     */
    public static void convertToEntity(Lecturer domain, LecturerEntity entity) {
        if (entity != null) {
            entity.setAdmin(domain.getAdmin());
            entity.setLogin(domain.getLogin());
            entity.setPassword(domain.getPassword());
            entity.setPasswordFormat(domain.getPasswordFormat());
            entity.setPasswordSalt(domain.getPasswordSalt());
            entity.setName(domain.getName());
            entity.setSurname(domain.getSurname());
            entity.setPatronymic(domain.getPatronymic());
        }
    }

    /**
     * Convert lecturer entity to lecturer.
     *
     * @param entity LecturerEntity
     * @return Lecturer
     */
    public static Lecturer convertToDomain(LecturerEntity entity) {
        Lecturer domain = new Lecturer();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setSurname(entity.getSurname());
        domain.setPatronymic(entity.getPatronymic());
        domain.setAdmin(entity.getAdmin());
        domain.setLogin(entity.getLogin());
        domain.setPassword(entity.getPassword());
        domain.setFullName(entity.getSurname() + " " + entity.getName() + " " + entity.getPatronymic());
        domain.setDepartmentId(entity.getDepartment().getId());
        return domain;
    }
}
