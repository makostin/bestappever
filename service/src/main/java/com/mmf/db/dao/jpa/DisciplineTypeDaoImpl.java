package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.dao.DisciplineTypeDao;
import com.mmf.db.model.DisciplineEntity;
import com.mmf.db.model.DisciplineTypeEntity;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class DisciplineTypeDaoImpl extends GenericJpaDao<Long, DisciplineTypeEntity> implements DisciplineTypeDao {

    @Override
    protected Class<DisciplineTypeEntity> getEntityClass() {
        return DisciplineTypeEntity.class;
    }
}
