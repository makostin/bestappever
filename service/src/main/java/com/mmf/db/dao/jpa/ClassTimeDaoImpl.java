package com.mmf.db.dao.jpa;

import com.mmf.db.dao.ClassTimeDao;
import com.mmf.db.model.ClassTimeEntity;

import javax.inject.Named;

/**
 * User: admin
 * Date: 20.10.12
 */
@Named
public class ClassTimeDaoImpl extends GenericJpaDao<Long, ClassTimeEntity> implements ClassTimeDao{
    @Override
    protected Class<ClassTimeEntity> getEntityClass() {
        return ClassTimeEntity.class;
    }
}
