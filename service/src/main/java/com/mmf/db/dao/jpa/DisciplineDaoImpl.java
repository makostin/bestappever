package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.model.DisciplineEntity;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class DisciplineDaoImpl extends GenericJpaDao<Long, DisciplineEntity> implements DisciplineDao{

    @Override
    protected Class<DisciplineEntity> getEntityClass() {
        return DisciplineEntity.class;
    }
}
