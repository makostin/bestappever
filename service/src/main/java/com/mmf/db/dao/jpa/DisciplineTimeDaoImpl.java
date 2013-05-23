package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DisciplineTimeDao;
import com.mmf.db.model.DisciplineTimeEntity;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class DisciplineTimeDaoImpl extends GenericJpaDao<Long, DisciplineTimeEntity> implements DisciplineTimeDao {

    @Override
    protected Class<DisciplineTimeEntity> getEntityClass() {
        return DisciplineTimeEntity.class;
    }
}
