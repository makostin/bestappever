package com.mmf.db.dao.jpa;

import com.mmf.db.dao.ClassroomDao;
import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.model.ClassroomEntity;
import com.mmf.db.model.DisciplineEntity;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class ClassroomDaoImpl extends GenericJpaDao<Long, ClassroomEntity> implements ClassroomDao{

    @Override
    protected Class<ClassroomEntity> getEntityClass() {
        return ClassroomEntity.class;
    }
}
