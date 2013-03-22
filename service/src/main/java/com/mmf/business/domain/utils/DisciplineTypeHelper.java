package com.mmf.business.domain.utils;

import com.mmf.business.domain.DisciplineType;
import com.mmf.db.model.DisciplineTypeEntity;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
public class DisciplineTypeHelper {

    /**
     * Convert DisciplineType to DisciplineType entity.
     *
     * @param domain DisciplineType
     * @param entity DisciplineTypeEntity
     */
    public static void convertToEntity(DisciplineType domain, DisciplineTypeEntity entity) {
        if (entity != null) {
            entity.setType(domain.getType());
        }
    }

    /**
     * Convert DisciplineType entity to DisciplineType.
     *
     * @param entity DisciplineTypeEntity
     * @return DisciplineType
     */
    public static DisciplineType convertToDomain(DisciplineTypeEntity entity) {
        DisciplineType domain = new DisciplineType();
        domain.setId(entity.getId());
        domain.setType(entity.getType());
        return domain;
    }
}
