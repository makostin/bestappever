package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DisciplineService;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.utils.DisciplineHelper;
import com.mmf.business.domain.utils.DisciplineTypeHelper;
import com.mmf.db.dao.DisciplineDao;
import com.mmf.db.model.DisciplineEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * User: svetlana.voyteh
 * Date: 20.03.13
 */
@Named
public class DisciplineServiceImpl extends AbstractCrudService<Long, Discipline, DisciplineEntity, DisciplineDao> implements DisciplineService{

    @Autowired
    private DisciplineDao disciplineDao;

    @Override
    protected DisciplineDao getDao() {
        return disciplineDao;
    }

    @Override
    public void convertToEntity(Discipline domain, DisciplineEntity entity) throws BusinessServiceException {
        if (domain != null){
            // todo
        }
    }

    @Override
    public Discipline convertToDomain(DisciplineEntity entity) throws BusinessServiceException {
        if(entity == null){
            return null;
        }

        Discipline discipline = DisciplineHelper.convertToDomain(entity);
        discipline.setDisciplineType(DisciplineTypeHelper.convertToDomain(entity.getDisciplineType()));

        return discipline;
    }
}
