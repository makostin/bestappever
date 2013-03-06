package com.mmf.db.dao.jpa;

import com.mmf.db.dao.LecturerDao;
import com.mmf.db.model.LecturerEntity;

import javax.inject.Named;

/**
 * svetlana.voyteh
 * 05.03.13
 */

@Named
public class LecturerDaoImpl extends GenericJpaDao<Long, LecturerEntity> implements LecturerDao {
    @Override
    protected Class<LecturerEntity> getEntityClass() {
        return LecturerEntity.class;
    }
}
