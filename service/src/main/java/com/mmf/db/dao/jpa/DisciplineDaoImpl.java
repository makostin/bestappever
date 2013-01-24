package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.model.DisciplineEntity;

import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
@Named
public class DisciplineDaoImpl extends GenericJpaDao<Long, DisciplineEntity> implements DisciplineDao {

    @Override
    protected Class<DisciplineEntity> getEntityClass() {
        return DisciplineEntity.class;
    }
}
