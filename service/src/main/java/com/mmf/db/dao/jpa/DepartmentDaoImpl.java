package com.mmf.db.dao.jpa;


import com.mmf.db.dao.DepartmentDao;
import com.mmf.db.model.DepartmentEntity;

import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 3/30/12
 */
@Named
public class DepartmentDaoImpl extends GenericJpaDao<Long, DepartmentEntity> implements DepartmentDao{


    @Override
    protected Class<DepartmentEntity> getEntityClass() {
        return DepartmentEntity.class;
    }
}
