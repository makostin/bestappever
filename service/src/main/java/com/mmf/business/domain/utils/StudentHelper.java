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
        student.setPraepostor(entity.getPraepostor());
        student.setYearOfEntrance(entity.getYearOfEntrance());
        return student;
    }
}
