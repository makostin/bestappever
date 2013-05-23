package com.mmf.business.domain.utils;


import com.mmf.business.domain.Student;
import com.mmf.db.model.StudentEntity;

/**
 * svetlana.voyteh
 * 13.03.13
 */
public class StudentHelper {

    /**
     * Convert Student to Student entity.
     *
     * @param domain Student
     * @param entity StudentEntity
     */
    public static void convertToEntity(Student domain, StudentEntity entity) {
        if (entity != null) {
            entity.setAdmin(domain.getAdmin());
            entity.setLogin(domain.getLogin());
            entity.setPassword(domain.getPassword());
            entity.setPasswordFormat(domain.getPasswordFormat());
            entity.setPatronymic(domain.getPatronymic());
            entity.setName(domain.getName());
            entity.setSurname(domain.getSurname());
            entity.setPasswordSalt(domain.getPasswordSalt());
            entity.setPraepostor(domain.getPraepostor());
            entity.setYearOfEntrance(domain.getYearOfEntrance());
        }
    }

    /**
     * Convert Student entity to Student.
     *
     * @param entity StudentEntity
     * @return Student
     */
    public static Student convertToDomain(StudentEntity entity) {
        Student student = new Student();
        student.setId(entity.getId());
        student.setName(entity.getName());
        student.setSurname(entity.getSurname());
        student.setPatronymic(entity.getPatronymic());
        student.setAdmin(entity.getAdmin());
        student.setLogin(entity.getLogin());
        student.setPassword(entity.getPassword());
        student.setPasswordFormat(entity.getPasswordFormat());
        student.setPasswordSalt(entity.getPasswordSalt());
        student.setFullName(entity.getSurname() + " " + entity.getName() + " " + entity.getPatronymic());
        student.setPraepostor(entity.getPraepostor());
        student.setYearOfEntrance(entity.getYearOfEntrance());
        student.setGroupId(entity.getGroup().getId());
        return student;
    }
}
