package com.mmf.db.dao.jpa;

import com.mmf.db.dao.DepartmentDao;
import com.mmf.db.model.DepartmentEntity;

import javax.inject.Named;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class DepartmentDaoImpl extends GenericJpaDao<Long, DepartmentEntity> implements DepartmentDao {

    @Override
    protected Class<DepartmentEntity> getEntityClass() {
        return DepartmentEntity.class;
    }
}
