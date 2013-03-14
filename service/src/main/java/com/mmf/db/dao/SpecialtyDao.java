package com.mmf.db.dao;

import com.mmf.db.model.SpecialtyEntity;

import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public interface SpecialtyDao extends GenericDao<Long, SpecialtyEntity>{
    List<SpecialtyEntity> getSpecialtiesWithGroups();
}
