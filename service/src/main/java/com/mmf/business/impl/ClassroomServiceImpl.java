package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.ClassroomService;
import com.mmf.business.DisciplineService;
import com.mmf.business.domain.Classroom;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.utils.ClassroomHelper;
import com.mmf.business.domain.utils.DisciplineHelper;
import com.mmf.business.domain.utils.DisciplineTypeHelper;
import com.mmf.db.dao.ClassroomDao;
import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.model.ClassroomEntity;
import com.mmf.db.model.DisciplineEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class ClassroomServiceImpl extends AbstractCrudService<Long, Classroom, ClassroomEntity, ClassroomDao> implements ClassroomService{

    @Autowired
    private ClassroomDao classroomDao;

    @Override
    protected ClassroomDao getDao() {
        return classroomDao;
    }

    @Override
    public void convertToEntity(Classroom domain, ClassroomEntity entity) throws BusinessServiceException {
        if (domain != null){
            ClassroomHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public Classroom convertToDomain(ClassroomEntity entity) throws BusinessServiceException {
        if(entity == null){
            return null;
        }

        return ClassroomHelper.convertToDomain(entity);
    }
}
