package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DisciplineService;
import com.mmf.business.DisciplineTypeService;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.DisciplineType;
import com.mmf.business.domain.utils.DisciplineHelper;
import com.mmf.business.domain.utils.DisciplineTypeHelper;
import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.dao.DisciplineTypeDao;
import com.mmf.db.model.DisciplineEntity;
import com.mmf.db.model.DisciplineTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class DisciplineTypeServiceImpl extends AbstractCrudService<Long, DisciplineType, DisciplineTypeEntity, DisciplineTypeDao> implements DisciplineTypeService {

    @Autowired
    private DisciplineTypeDao disciplineTypeDao;

    @Override
    protected DisciplineTypeDao getDao() {
        return disciplineTypeDao;
    }

    @Override
    public void convertToEntity(DisciplineType domain, DisciplineTypeEntity entity) throws BusinessServiceException {
        if (domain != null){
            // todo
        }
    }

    @Override
    public DisciplineType convertToDomain(DisciplineTypeEntity entity) throws BusinessServiceException {
        if(entity == null){
            return null;
        }

        return DisciplineTypeHelper.convertToDomain(entity);
    }
}
