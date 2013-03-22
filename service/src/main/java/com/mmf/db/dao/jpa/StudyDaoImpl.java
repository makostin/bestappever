package com.mmf.db.dao.jpa;

import com.mmf.db.dao.StudyDao;
import com.mmf.db.model.StudyEntity;

import javax.inject.Named;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class StudyDaoImpl extends GenericJpaDao<Long, StudyEntity> implements StudyDao{


    @Override
    protected Class<StudyEntity> getEntityClass() {
        return StudyEntity.class;
    }
}
