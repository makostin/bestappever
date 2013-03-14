package com.mmf.business;

import com.mmf.business.domain.Specialty;
import com.mmf.business.domain.SpecialtyInfo;

import java.util.List;

/**
 * svetlana.voyteh
 * 05.03.13
 */
public interface SpecialtyService extends CrudService<Long, Specialty>{

    List<SpecialtyInfo> getSpecialtyInfos() throws BusinessServiceException;
}
