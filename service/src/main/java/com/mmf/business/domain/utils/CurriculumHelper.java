package com.mmf.business.domain.utils;

import com.mmf.business.domain.Curriculum;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.Specialty;
import com.mmf.db.model.CurriculumEntity;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
public class CurriculumHelper {

    /**
     * Convert Curriculum to Curriculum entity.
     *
     * @param domain Curriculum
     * @param entity CurriculumEntity
     */
    public static void convertToEntity(Curriculum domain, CurriculumEntity entity) {
        if (entity != null) {
            entity.setExam(domain.getExam());
            entity.setHours(domain.getHours());
            entity.setSemester(domain.getSemester());
            entity.setSetoff(domain.getSetoff());
        }
    }

    /**
     * Convert Curriculum entity to Curriculum.
     *
     * @param entity CurriculumEntity
     * @return Curriculum
     */
    public static Curriculum convertToDomain(CurriculumEntity entity) {
        Curriculum domain = new Curriculum();
        domain.setId(entity.getId());
        domain.setExam(entity.getExam());
        domain.setHours(entity.getHours());
        domain.setSemester(entity.getSemester());
        domain.setSetoff(entity.getSetoff());
        domain.setDiscipline(new Discipline(entity.getDiscipline().getId()));
        domain.setSpecialty(new Specialty(entity.getSpecialty().getId()));
        return domain;
    }

}
