package com.mmf.business.domain.utils;

import com.mmf.business.domain.Group;
import com.mmf.db.model.GroupEntity;

import java.util.Calendar;

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
            entity.setName(String.valueOf(domain.getNumber()) + domain.getSubgroup());
        }
    }

    /**
     * Convert group entity to group.
     *
     * @param entity GroupEntity
     * @return Group
     */
    public static Group convertToDomain(GroupEntity entity) {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        Group domain = new Group();
        domain.setId(entity.getId());
//        domain.setName(entity.getName());
        domain.setYear(entity.getYear());

        if (entity.getName().length() > 1){
            domain.setSubgroup(entity.getName().substring(entity.getName().length()-1));
            domain.setNumber(Integer.parseInt(entity.getName().substring(0, entity.getName().length()-1)));
        } else {
            domain.setNumber(Integer.parseInt(entity.getName()));
        }

        if (currentMonth < Calendar.JULY){
            domain.setCourse(currentYear - entity.getYear());
        } else {
            domain.setCourse(currentYear - entity.getYear()+1);
        }
        return domain;
    }
}
