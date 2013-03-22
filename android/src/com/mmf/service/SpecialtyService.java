package com.mmf.service;

import com.mmf.db.dao.impl.SpecialtyDao;
import com.mmf.db.model.Specialty;
import com.mmf.util.EntityRegistry;

/**
 * svetlana.voyteh
 * 22.03.13
 */
public class SpecialtyService {

    private final SpecialtyDao specialtyDao = (SpecialtyDao) EntityRegistry.get().getEntityDao(Specialty.class);

    public Specialty getSpecialtyByGroupNumber(int groupNumber){
        return specialtyDao.getSpecialtyByGroupNumber(groupNumber);
    }
}
