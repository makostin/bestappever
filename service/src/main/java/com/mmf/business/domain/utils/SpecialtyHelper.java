package com.mmf.business.domain.utils;

import com.mmf.business.domain.Specialty;
import com.mmf.db.model.SpecialtyEntity;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public class SpecialtyHelper {

    /**
     * Convert specialty to specialty entity.
     *
     * @param domain Specialty
     * @param entity SpecialtyEntity
     */
    public static void convertToEntity(Specialty domain, SpecialtyEntity entity) {
        if (entity != null) {
            entity.setName(domain.getName());
            entity.setDescription(domain.getDescription());
        }
    }

    /**
     * Convert specialty entity to specialty.
     *
     * @param entity SpecialtyEntity
     * @return Specialty
     */
    public static Specialty convertToDomain(SpecialtyEntity entity) {
        Specialty domain = new Specialty();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        return domain;
    }
}
