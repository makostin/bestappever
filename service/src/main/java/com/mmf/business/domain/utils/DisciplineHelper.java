package com.mmf.business.domain.utils;

import com.mmf.business.domain.Discipline;
import com.mmf.db.model.DisciplineEntity;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
public class DisciplineHelper {

    /**
     * Convert Discipline to Discipline entity.
     *
     * @param domain Discipline
     * @param entity DisciplineEntity
     */
    public static void convertToEntity(Discipline domain, DisciplineEntity entity) {
        if (entity != null) {
            entity.setName(domain.getName());
        }
    }

    /**
     * Convert Discipline entity to Discipline.
     *
     * @param entity DisciplineEntity
     * @return Discipline
     */
    public static Discipline convertToDomain(DisciplineEntity entity) {
        Discipline domain = new Discipline();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDisciplineTypeId(entity.getDisciplineType().getId());
        return domain;
    }
}
