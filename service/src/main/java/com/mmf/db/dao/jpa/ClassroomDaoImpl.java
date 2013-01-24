package com.mmf.db.dao.jpa;

import com.mmf.db.dao.ClassroomDao;
import com.mmf.db.model.ClassroomEntity;

import javax.inject.Named;

/**
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
@Named
public class ClassroomDaoImpl extends GenericJpaDao<Long, ClassroomEntity> implements ClassroomDao{

    @Override
    protected Class<ClassroomEntity> getEntityClass() {
        return ClassroomEntity.class;
    }
}
