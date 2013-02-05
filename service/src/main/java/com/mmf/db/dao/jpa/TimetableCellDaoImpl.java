package com.mmf.db.dao.jpa;

import com.mmf.db.dao.TimetableCellDao;

import javax.inject.Named;

/**
 * User: admin
 * Date: 20.10.12
 */
@Named
public class TimetableCellDaoImpl extends GenericJpaDao<Long, TimetableCellEntity> implements TimetableCellDao{
    @Override
    protected Class<TimetableCellEntity> getEntityClass() {
        return TimetableCellEntity.class;
    }
}
