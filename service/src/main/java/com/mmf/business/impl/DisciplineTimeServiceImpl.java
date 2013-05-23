package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DisciplineTimeService;
import com.mmf.business.DisciplineTypeService;
import com.mmf.business.domain.DisciplineTime;
import com.mmf.business.domain.DisciplineType;
import com.mmf.business.domain.utils.DisciplineTimeHelper;
import com.mmf.business.domain.utils.DisciplineTypeHelper;
import com.mmf.db.dao.DisciplineTimeDao;
import com.mmf.db.dao.DisciplineTypeDao;
import com.mmf.db.model.DisciplineTimeEntity;
import com.mmf.db.model.DisciplineTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class DisciplineTimeServiceImpl extends AbstractCrudService<Long, DisciplineTime, DisciplineTimeEntity, DisciplineTimeDao> implements DisciplineTimeService {

    @Autowired
    private DisciplineTimeDao disciplineTimeDao;

    @Override
    protected DisciplineTimeDao getDao() {
        return disciplineTimeDao;
    }

    @Override
    public void convertToEntity(DisciplineTime domain, DisciplineTimeEntity entity) throws BusinessServiceException {
        if (domain != null){
            DisciplineTimeHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public DisciplineTime convertToDomain(DisciplineTimeEntity entity) throws BusinessServiceException {
        if(entity == null){
            return null;
        }

        return DisciplineTimeHelper.convertToDomain(entity);
    }
}
