package com.mmf.business.domain.utils;

import com.mmf.business.domain.Group;
import com.mmf.db.model.GroupEntity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            String subGroup = domain.getSubgroup() != null ? domain.getSubgroup() : "";
            entity.setName(String.valueOf(domain.getNumber()) + subGroup);
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
        domain.setYear(entity.getYear());
        domain.setSpecialtyId(entity.getSpecialty().getId());

        Pattern pattern = Pattern.compile("([0-9]{1,2})(a|b)*");
        Matcher matcher = pattern.matcher(entity.getName());
        if (matcher.find()){
            domain.setNumber(Integer.parseInt(matcher.group(1)));
            domain.setSubgroup(matcher.group(2));
        }

        if (currentMonth < Calendar.JULY){
            domain.setCourse(currentYear - entity.getYear());
        } else {
            domain.setCourse(currentYear - entity.getYear()+1);
        }
        return domain;
    }
}
