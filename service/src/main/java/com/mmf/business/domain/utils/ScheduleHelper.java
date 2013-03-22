package com.mmf.business.domain.utils;

import com.mmf.business.domain.Schedule;
import com.mmf.db.model.ScheduleEntity;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class ScheduleHelper {

    /**
     * Convert Schedule to Schedule entity.
     *
     * @param domain Schedule
     * @param entity ScheduleEntity
     */
    public static void convertToEntity(Schedule domain, ScheduleEntity entity) {
        if (entity != null) {
            entity.setDayOfWeek(domain.getDayOfWeek());
            entity.setEveryNWeek(domain.getEveryNWeek());
            entity.setWeek(domain.getWeek());
        }
    }

    /**
     * Convert Schedule entity to Schedule.
     *
     * @param entity ScheduleEntity
     * @return Schedule
     */
    public static Schedule convertToDomain(ScheduleEntity entity) {
        Schedule domain = new Schedule();
        domain.setId(entity.getId());
        domain.setDayOfWeek(entity.getDayOfWeek());
        domain.setEveryNWeek(entity.getEveryNWeek());
        domain.setWeek(entity.getWeek());
        return domain;
    }
}
