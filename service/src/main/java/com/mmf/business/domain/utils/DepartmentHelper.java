package com.mmf.business.domain.utils;

import com.mmf.business.domain.Department;
import com.mmf.db.model.DepartmentEntity;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public class DepartmentHelper {

    /**
     * Convert department to department entity.
     *
     * @param domain Department
     * @param entity DepartmentEntity
     */
    public static void convertToEntity(Department domain, DepartmentEntity entity) {
        if (entity != null) {
            entity.setName(domain.getName());
            entity.setDescription(domain.getDescription());
        }
    }

    /**
     * Convert department entity to department.
     *
     * @param entity DepartmentEntity
     * @return Department
     */
    public static Department convertToDomain(DepartmentEntity entity) {
        Department domain = new Department();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        return domain;
    }
}
