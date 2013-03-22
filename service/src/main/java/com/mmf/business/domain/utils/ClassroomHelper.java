package com.mmf.business.domain.utils;


import com.mmf.business.domain.Classroom;
import com.mmf.db.model.ClassroomEntity;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
public class ClassroomHelper {

    /**
     * Convert Classroom to Classroom entity.
     *
     * @param domain Classroom
     * @param entity ClassroomEntity
     */
    public static void convertToEntity(Classroom domain, ClassroomEntity entity) {
        if (entity != null) {
            entity.setNumber(domain.getNumber());
            entity.setCapacity(domain.getCapacity());
        }
    }

    /**
     * Convert Classroom entity to Classroom.
     *
     * @param entity ClassroomEntity
     * @return Classroom
     */
    public static Classroom convertToDomain(ClassroomEntity entity) {
        Classroom domain = new Classroom();
        domain.setId(entity.getId());
        domain.setCapacity(entity.getCapacity());
        domain.setNumber(entity.getNumber());
        return domain;
    }
}
