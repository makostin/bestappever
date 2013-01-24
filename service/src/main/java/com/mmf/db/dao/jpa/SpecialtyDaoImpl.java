package com.mmf.db.dao.jpa;

import com.mmf.db.dao.SpecialtyDao;
import com.mmf.db.model.SpecialtyEntity;

import javax.inject.Named;

/**
 * User: admin
 * Date: 20.10.12
 */
@Named
public class SpecialtyDaoImpl extends GenericJpaDao<Long, SpecialtyEntity> implements SpecialtyDao{
    @Override
    protected Class<SpecialtyEntity> getEntityClass() {
        return SpecialtyEntity.class;
    }
}
