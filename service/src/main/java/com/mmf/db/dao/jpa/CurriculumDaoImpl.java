package com.mmf.db.dao.jpa;

import com.mmf.db.dao.CurriculumDao;
import com.mmf.db.model.CurriculumEntity;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class CurriculumDaoImpl extends GenericJpaDao<Long, CurriculumEntity> implements CurriculumDao {

    @Override
    protected Class<CurriculumEntity> getEntityClass() {
        return CurriculumEntity.class;
    }
}
