package com.mmf.business.domain.utils;

import com.mmf.business.domain.Curriculum;
import com.mmf.business.domain.Group;
import com.mmf.business.domain.Lecturer;
import com.mmf.business.domain.Study;
import com.mmf.db.model.StudyEntity;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
public class StudyHelper {

    /**
     * Convert Study to Study entity.
     *
     * @param domain Study
     * @param entity StudyEntity
     */
    public static void convertToEntity(Study domain, StudyEntity entity) {
        if (entity != null) {
        }
    }

    /**
     * Convert Study entity to Study.
     *
     * @param entity StudyEntity
     * @return Study
     */
    public static Study convertToDomain(StudyEntity entity) {
        Study domain = new Study();
        domain.setId(entity.getId());
        domain.setGroup(new Group(entity.getGroup().getId()));
        domain.setLecturer(new Lecturer(entity.getLecturer().getId()));
        domain.setCurriculum(new Curriculum(entity.getCurriculum().getId()));
        return domain;
    }
}
