package com.mmf.business.domain.utils;

import com.mmf.business.domain.Group;
import com.mmf.db.model.GroupEntity;

/**
 * svetlana.voyteh
 * 13.03.13
 */
public class GroupHelper {

    /**
     * Convert group to group entity.
     *
     * @param domain Group
     * @param entity GroupEntity
     */
    public static void convertToEntity(Group domain, GroupEntity entity) {
        if (entity != null) {
            entity.setYear(domain.getYear());
            entity.setName(domain.getName());
        }
    }

    /**
     * Convert group entity to group.
     *
     * @param entity GroupEntity
     * @return Group
     */
    public static Group convertToDomain(GroupEntity entity) {
        Group domain = new Group();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setYear(entity.getYear());
        return domain;
    }
}
