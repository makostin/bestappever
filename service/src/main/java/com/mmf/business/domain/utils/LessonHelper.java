package com.mmf.business.domain.utils;

import com.mmf.business.domain.Lesson;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public final class LessonHelper {

    /**
     * Hided constructor
     */
    private LessonHelper() {
    }

    /**
     * Converts lesson to lesson entity.
     * @param domain Lesson
     * @param entity LessonEntity
     */
    public static void convertToEntity(Lesson domain, LessonEntity entity) {
        if (domain != null && entity != null) {
            entity.setDay(domain.getDay());
            entity.setTime(domain.getTime());
        }
    }

    /**
     * Converts lesson entity to lesson.
     * @param entity LessonEntity
     * @return Lesson
     */
    public static Lesson convertToDomain(LessonEntity entity) {
        if (entity == null) {
            return null;
        }
        Lesson domain = new Lesson();
        domain.setId(entity.getId());
        domain.setDay(entity.getDay());
        domain.setClassroom(entity.getClassroom().getNumber());
//        domain.setCourse(Calendar.getInstance().get(Calendar.YEAR)-((GroupEntity)entity.getGroups().toArray()[0]).getYear());
//        domain.setGroup(((GroupEntity)entity.getGroups().toArray()[0]).getNumber());
        domain.setLecturer(entity.getLecturer().getName());
        domain.setSubject(entity.getDiscipline().getName());
        domain.setTime(entity.getTime());

        return domain;
    }
}
