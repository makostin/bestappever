package com.mmf.business.domain.utils;

import com.mmf.business.domain.DisciplineTime;
import com.mmf.db.model.DisciplineTimeEntity;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
public class DisciplineTimeHelper {

    /**
     * Convert DisciplineTime to DisciplineTime entity.
     *
     * @param domain DisciplineTime
     * @param entity DisciplineTimeEntity
     */
    public static void convertToEntity(DisciplineTime domain, DisciplineTimeEntity entity) {
        if (entity != null) {
            entity.setBreakTime(domain.getBreakTime());
            entity.setNumber(domain.getNumber());
            entity.setStartTime(domain.getStartTime());
            entity.setEndTime(domain.getEndTime());
        }
    }

    /**
     * Convert DisciplineTime entity to DisciplineTime.
     *
     * @param entity DisciplineTimeEntity
     * @return DisciplineTime
     */
    public static DisciplineTime convertToDomain(DisciplineTimeEntity entity) {
        DisciplineTime domain = new DisciplineTime();
        domain.setId(entity.getId());
        domain.setNumber(entity.getNumber());
        domain.setBreakTime(entity.getBreakTime());
        domain.setStartTime(entity.getStartTime());
        domain.setEndTime(entity.getEndTime());
        return domain;
    }
}
