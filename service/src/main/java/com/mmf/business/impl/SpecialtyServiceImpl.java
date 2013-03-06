package com.mmf.business.impl;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.SpecialtyService;
import com.mmf.business.domain.Specialty;
import com.mmf.business.domain.utils.SpecialtyHelper;
import com.mmf.db.dao.SpecialtyDao;
import com.mmf.db.model.SpecialtyEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * svetlana.voyteh
 * 05.03.13
 */
@Named
public class SpecialtyServiceImpl extends AbstractCrudService<Long, Specialty, SpecialtyEntity, SpecialtyDao> implements SpecialtyService{

    @Inject
    private SpecialtyDao specialtyDao;

    @Override
    protected SpecialtyDao getDao() {
        return specialtyDao;
    }

    @Override
    public void convertToEntity(Specialty domain, SpecialtyEntity entity) throws BusinessServiceException {
        if (domain != null){
            SpecialtyHelper.convertToEntity(domain, entity);
        }
    }

    @Override
    public Specialty convertToDomain(SpecialtyEntity entity) throws BusinessServiceException {
        if (entity == null) {
            return null;
        }

        Specialty specialty = SpecialtyHelper.convertToDomain(entity);
        return specialty;
    }
}
