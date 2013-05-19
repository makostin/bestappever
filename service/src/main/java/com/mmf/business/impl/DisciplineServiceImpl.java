package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.DisciplineService;
import com.mmf.business.domain.Discipline;
import com.mmf.business.domain.utils.DisciplineHelper;
import com.mmf.business.domain.utils.DisciplineTypeHelper;
import com.mmf.db.dao.DataAccessException;
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
public class DisciplineServiceImpl extends AbstractCrudService<Long, Discipline, DisciplineEntity, DisciplineDao> implements DisciplineService {

    @Autowired
    private DisciplineDao disciplineDao;

    @Autowired
    private DisciplineTypeDao disciplineTypeDao;

    @Override
    protected DisciplineDao getDao() {
        return disciplineDao;
    }

    @Override
    public void convertToEntity(Discipline domain, DisciplineEntity entity) throws BusinessServiceException {
        if (domain != null) {
            try {
                DisciplineHelper.convertToEntity(domain, entity);
                DisciplineTypeEntity disciplineTypeEntity = disciplineTypeDao.getEntityInstance(domain.getDisciplineTypeId());
                if(disciplineTypeEntity == null){
                    throw new BusinessServiceException("Such discipline type doesn't exist.");
                }

                if (entity != null){
                    entity.setDisciplineType(disciplineTypeEntity);
                }
            } catch (DataAccessException e) {
                throw new BusinessServiceException("Conversion to discipline entity error.", e);
            }
        }
    }

    @Override
    public Discipline convertToDomain(DisciplineEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Discipline discipline = DisciplineHelper.convertToDomain(entity);
        discipline.setDisciplineType(DisciplineTypeHelper.convertToDomain(entity.getDisciplineType()));

        return discipline;
    }
}
